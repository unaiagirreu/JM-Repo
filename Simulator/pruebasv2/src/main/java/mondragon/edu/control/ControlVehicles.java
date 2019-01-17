package mondragon.edu.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import mondragon.edu.clases.Line;
import mondragon.edu.clases.Parking;
import mondragon.edu.clases.Product;
import mondragon.edu.clases.Segment;
import mondragon.edu.clases.Vehicle;
import mondragon.edu.clases.Workstation;
import mondragon.edu.service.SegmentService;
import mondragon.edu.service.VehicleService;

/**
 * Class for controlling vehicles and movements
 * 
 * @author unaiagirre
 *
 */
public class ControlVehicles {

	static Semaphore sem;
	static Semaphore mutEx;
	List<Vehicle> listVehicles;
	List<Vehicle> listAvailableVehicles;
	List<Segment> segmentList;
	Map<Integer, List<Integer>> rutas;
	AnnotationConfigApplicationContext context;
	Vehicle vehicle0;
	Vehicle vehicle1;
	Vehicle vehicle2;
	Vehicle vehicle3;
	Vehicle vehicle4;
	SegmentService segmentService;
	VehicleService vehicleService;
	
	public ControlVehicles(AnnotationConfigApplicationContext context) {
		this.context=context;
		initVehicles();
		mutEx = new Semaphore(1);	
		sem = new Semaphore(1);
		rutas=new HashMap<>();
		segmentService = context.getBean(SegmentService.class);
		vehicleService = context.getBean(VehicleService.class);
	}
	
	/**
	 * This function sets the segment list, the current segment of each vehicle, and the status of the vehicles
	 * 
	 * @param SegmentList the segment list we want to set
	 */
	public void setSegmentList(List<Segment> SegmentList) {
		segmentList=SegmentList;
		vehicle0.setCurrentSegment(segmentList.get(22));
		vehicleService.setCurrentSegment(vehicle0, segmentList.get(22));
		vehicleService.setStatus(vehicle0, "stoped");
		segmentList.get(22).askForPriority();
		vehicle1.setCurrentSegment(segmentList.get(23));
		vehicleService.setCurrentSegment(vehicle1, segmentList.get(23));
		vehicleService.setStatus(vehicle1, "stoped");
		segmentList.get(23).askForPriority();
		vehicle2.setCurrentSegment(segmentList.get(24));
		vehicleService.setCurrentSegment(vehicle2, segmentList.get(24));
		vehicleService.setStatus(vehicle2, "stoped");
		segmentList.get(24).askForPriority();
		vehicle3.setCurrentSegment(segmentList.get(25));
		vehicleService.setCurrentSegment(vehicle3, segmentList.get(25));
		vehicleService.setStatus(vehicle3, "stoped");
		segmentList.get(25).askForPriority();
		vehicle4.setCurrentSegment(segmentList.get(16));
		vehicleService.setCurrentSegment(vehicle4, segmentList.get(16));
		vehicleService.setStatus(vehicle4, "stoped");
		segmentList.get(16).askForPriority();
	}
	
	/**
	 * This function initialize all the vehicle threads. We also add the vehicles to two different lists. One to have all the vehicles
	 * and another one to have just the available ones
	 */
	private void initVehicles() {
		listVehicles= new ArrayList<Vehicle>();
		listAvailableVehicles= new ArrayList<Vehicle>();
		vehicle0=new Vehicle(0, this, context);
		vehicle1=new Vehicle(1, this, context);
		vehicle2=new Vehicle(2, this, context);
		vehicle3=new Vehicle(3, this, context);
		vehicle4=new Vehicle(4, this, context);

		Thread thread0= new Thread(vehicle0);
		Thread thread1= new Thread(vehicle1);
		Thread thread2= new Thread(vehicle2);
		Thread thread3= new Thread(vehicle3);
		Thread thread4= new Thread(vehicle4);
		thread0.setName("cocheVolador");
		thread1.setName("el astrica");
		thread2.setName("el demonio blanco");
		thread3.setName("el passat abollao");
		thread4.setName("el patinete de jf");
		thread0.start();
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		listVehicles.add(vehicle0);
		listVehicles.add(vehicle1);
		listVehicles.add(vehicle2);
		listVehicles.add(vehicle3);	
		listVehicles.add(vehicle4);	
		listAvailableVehicles=listVehicles;
	}

	/**
	 * This function adds a vehicle to the available vehicle list
	 * 
	 * @param vehicle the vehicle you want to add to the list
	 * @throws InterruptedException we adquire a semaphore. If is not available the thread will be waiting to someone to release it. Trow
	 * exception if interrupted
	 */
	public void addVehicle(Vehicle vehicle) throws InterruptedException {
		mutEx.acquire();
		listAvailableVehicles.add(vehicle);
		mutEx.release();
		
	}

	/**
	 * This function is called when a product is finished and you want to call a vehicle to take it 
	 * and to carry it to the products destiny.
	 * First we take a available vehicle and we carry it to the products origin workstation.
	 * Then once the vehicle has the product we carry both to the first workstation where all the products are carried.
	 * 
	 * @param product the product calling to a vehicle
	 * @throws InterruptedException if the thread is interrupted
	 */
	public void callVehicle(Product product) throws InterruptedException{
		Vehicle vehicle;
		sem.acquire();		
		while(listAvailableVehicles.size()==0) {
			System.out.print("");
		}
		vehicle=listAvailableVehicles.get(0);
		listAvailableVehicles.remove(0);
		vehicle.moveProduct(product);
		vehicle.setDestinationSegment(product.getSegmentOrigin());
		definirRutaRecogida(vehicle,product);
		sem.release();
		vehicle.recogerProducto(product);
		sem.acquire();
		definirRutaSalida(vehicle,product);	
		vehicle.setCurrentSegment(product.getSegmentOrigin());				
		synchronized(vehicle) {
			vehicle.notify();
		}	
		sem.release();
	}
	
	/**
	 * Defines the shortest route from the vehicles current segment to the product origin.
	 * 
	 * @param vehicle the vehicle going for the product
	 * @param product the product asking for a vehicle
	 */
	public void definirRutaRecogida(Vehicle vehicle, Product product) {
		
		
		Segment origin=vehicle.getCurrentSegment();
		Segment desti= vehicle.getDestinationSegment();
		calcularRuta(segmentService.findCorrespondentLine(origin.getLineId()), segmentService.findCorrespondentLine(desti.getLineId()), product, true);
		vehicle.setRuta(rutaMasCorta());
		rutas.clear();
	}
	
	/**
	 * This function defines the shortest route from the product origin to the product destination
	 * 
	 * @param vehicle the vehicle carrying the product
	 * @param product the product carried
	 */
	public void definirRutaSalida(Vehicle vehicle, Product product) {
		
	//	System.out.println(segmentService.findCorrespondentLine(product.getSegmentOrigin().getCorrespondientLineId()).getId()+ " " + product.getSegmentOrigin().getCorrespondientLineId());
		calcularRuta(segmentService.findCorrespondentLine(product.getSegmentOrigin().getCorrespondientLineId()), segmentService.findCorrespondentLine(product.getSegmentDestination().getCorrespondientLineId()), product, false);
		vehicle.setRuta(rutaMasCorta());
		rutas.clear();
	}

	/**
	 * This function definesthe shortest route for the vehicle going to a parking
	 * 
	 * @param vehicle the vehicle going to the parking
	 * @return return the parking available
	 */
	public Parking definirRutaParking(Vehicle vehicle) {
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Parking parking = searchAvailableParking();
		Segment origin=vehicle.getCurrentSegment();
		Segment desti= parking;
		calcularRuta(segmentService.findCorrespondentLine(origin.getLineId()), segmentService.findCorrespondentLine(desti.getLineId()), null, true);
		vehicle.setRuta(rutaMasCorta());
		rutas.clear();
		sem.release();
		return parking;
	}

	/**
	 * Search for a available parking getting all the parkings from the segment list and looking if their state its occupied or not
	 * 
	 * @return returns the available parking
	 */
	private Parking searchAvailableParking() {
		Parking parking=null;
		for (int i=22;i<segmentList.size();i++) {//los parkines estan del 22 al 25
			parking=(Parking) segmentList.get(i);
			if(!parking.getState().equals("occupied")) break;
		}
		parking.setState("occupied");
		return parking;
	}

	/**
	 * This function calculates the route from a line to another line. Each line has a next segment and sometimes two possible next
	 * segments. We make a route with the default next segments and when a line has two next segments, we call to calcularRutaAlternativa
	 * to calculate the route taking the second posible next segment.
	 * 
	 * @param origin from where
	 * @param destination to where
	 * @param product the product moving
	 * @param goToProduct if the route is for going to a product or no, to add the product destination to the route or not.
	 */
	public void calcularRuta(Line origin, Line destination, Product product, boolean goToProduct) {//if goToProduct is true he is going for product
		List<Integer> ruta=new ArrayList<>();
		Line actual=origin;
		ruta.add(actual.getId());
		
		while(!actual.getId().equals(destination.getId())) {

	//		System.out.println(actual.nextSegment + ", "+actual.nextSegment2);
			if (actual.nextSegment2!=-1) calcularRutaAlternativa(actual.nextSegment2, ruta.toArray(new Integer[0]), destination, product, goToProduct);
			ruta.add(actual.getNextSegment());
			actual=(Line) segmentList.get(actual.getNextSegment()-1);
		}
//		System.out.println(ruta);
		
		if(product!=null) {
			if(!goToProduct) ruta.add(product.getSegmentDestination().getId());
			else ruta.add(product.getSegmentOrigin().getId());
		}
		rutas.put(ruta.size(), ruta);
	}
	
	/**
	 * This function calculates an alternative route for the vehicle, for getting a shorter route if possible
	 * 
	 * @param desvio where the deviation happens
	 * @param ruta2 the route before the deviation
	 * @param destination the destination of the route
	 * @param product the product carrying
	 * @param goToProduct if we are going to a product or no
	 */
	public void calcularRutaAlternativa(int desvio, Integer[] ruta2, Line destination, Product product, boolean goToProduct) {
		List<Integer> rutaAlter=new ArrayList<>();
		for (int i = 0; i < ruta2.length; i++) {
			rutaAlter.add(ruta2[i]);
		}
		rutaAlter.add(desvio);
		Line actual=(Line) segmentList.get(desvio-1);
		while(!actual.getId().equals(destination.getId())) {
//			System.out.println(actual.nextSegment);
			rutaAlter.add(actual.getNextSegment());
			actual=(Line) segmentList.get(actual.getNextSegment()-1);
		}
//		System.out.println(rutaAlter);
		if(product!=null) {
			if(!goToProduct) rutaAlter.add(product.getSegmentDestination().getId());
			else rutaAlter.add(product.getSegmentOrigin().getId());
		}
		rutas.put(rutaAlter.size(), rutaAlter);
	}
	
	/**
	 * This function selects the shortest route from the generated ones
	 * 
	 * @return the shortest route
	 */
	public List<Integer> rutaMasCorta(){
		int smallestKey;
		Set<Integer> sizes=rutas.keySet();
		Integer[] sizesList=sizes.toArray(new Integer[0]);
		smallestKey=sizesList[0];
		for (int i = 1; i < sizesList.length; i++) {
			if(sizesList[i]<smallestKey) smallestKey=sizesList[i];
		}
		return rutas.get(smallestKey);	
	}

	public Map<Integer, List<Integer>> getRutas() {
		return rutas;
	}
	
	/**
	 * This function sets the next segment to a vehicle
	 * 
	 * @param id the id for searching the segment
	 * @param vehicle the vehicle moving
	 * @throws InterruptedException
	 */
	public void setNextSegment(int id, Vehicle vehicle) throws InterruptedException {
 
		mutEx.acquire();	
		vehicle.setCurrentSegment(segmentService.findSegment(id));
		mutEx.release();
	}
	
	/**
	 * This function takes and lets the priority of a segment when a vehicle moves from one to another
	 * 
	 * @param id the id of the segment we are taking or leting
	 * @param ask if true we are taking a segment priority. If false we are letting it
	 * @param vehicle the vehicle asking for the priority
	 * @throws InterruptedException we adquire a semaphore. If is not available the thread will be waiting to someone to release it. Trow
	 * exception if interrupted
	 */
	public void takeNextLine(int id, boolean ask, Vehicle vehicle) throws InterruptedException {
		if(ask) {
		//	if(vehicle.getId()==1)System.out.println(id);
			if(id==17) {
				Workstation w=(Workstation) segmentList.get(id-1);
				boolean bool=w.askForPriority17();
				while(!bool) {
					askForWorkstation();
					bool=w.askForPriority17();
				}
			}
			else segmentList.get(id-1).askForPriority();
		//	System.out.println("El vehicle :" +vehicle.getId() +"prioridad pillada:"+segmentList.get(id-1).getId());
		}else {
			segmentList.get(id-1).letPriority();
		//	System.out.println("El vehicle :" +vehicle.getId() +"prioridad dejada:"+segmentList.get(id-1).getId());
		}
	}

	/**
	 * When you are aproaching to the first workstation you ask if it is free or not
	 */
	public void askForWorkstation() {
		for(int i=0;i<listAvailableVehicles.size() ;i++) {
			while(listAvailableVehicles.size()==0) {
				System.out.print("");
			}
			if(listAvailableVehicles.get(i).getCurrentSegment().getId().equals(segmentList.get(16).getId())) {
				Vehicle vehicle = listAvailableVehicles.get(i);
				listAvailableVehicles.remove(i);
				vehicle.moveToParking();
				synchronized(vehicle) {
					vehicle.notify();
				}	
				break;
			}
		}
	}

	public int getPermits(int id) {//una prueba BORRAR
		Workstation w=(Workstation) segmentList.get(id-1);
		return w.getPermits();
	}

	public SegmentService getSegmentService() {
		return segmentService;
	}

	public void setSegmentService(SegmentService segmentService) {
		this.segmentService = segmentService;
	}

	public void setVehicle0(Vehicle vehicle0) {
		this.vehicle0 = vehicle0;
	}

	public void setVehicle1(Vehicle vehicle1) {
		this.vehicle1 = vehicle1;
	}

	public void setVehicle2(Vehicle vehicle2) {
		this.vehicle2 = vehicle2;
	}

	public void setVehicle3(Vehicle vehicle3) {
		this.vehicle3 = vehicle3;
	}

	public void setVehicle4(Vehicle vehicle4) {
		this.vehicle4 = vehicle4;
	}

	public Vehicle getVehicle0() {
		return vehicle0;
	}

	public Vehicle getVehicle1() {
		return vehicle1;
	}

	public Vehicle getVehicle2() {
		return vehicle2;
	}

	public Vehicle getVehicle3() {
		return vehicle3;
	}

	public Vehicle getVehicle4() {
		return vehicle4;
	}
	

}
