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
	
	public ControlVehicles(AnnotationConfigApplicationContext context) {
		this.context=context;
		initVehicles();
		mutEx = new Semaphore(1);	
		sem = new Semaphore(1);
		rutas=new HashMap<>();
		segmentService = context.getBean(SegmentService.class);
	}
	
	public void setSegmentList(List<Segment> SegmentList) {
		segmentList=SegmentList;
		vehicle0.setCurrentSegment(segmentList.get(22));
		segmentList.get(22).askForPriority();
		vehicle1.setCurrentSegment(segmentList.get(23));
		segmentList.get(23).askForPriority();
		vehicle2.setCurrentSegment(segmentList.get(24));
		segmentList.get(24).askForPriority();
		vehicle3.setCurrentSegment(segmentList.get(25));
		segmentList.get(25).askForPriority();
		vehicle4.setCurrentSegment(segmentList.get(16));
		segmentList.get(16).askForPriority();
	}
	
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

	public void addVehicle(Vehicle vehicle) throws InterruptedException {
		mutEx.acquire();
		listAvailableVehicles.add(vehicle);
		mutEx.release();
		
	}

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
	
	public void definirRutaRecogida(Vehicle vehicle, Product product) {
		
		
		Segment origin=vehicle.getCurrentSegment();
		Segment desti= vehicle.getDestinationSegment();
		calcularRuta(segmentService.findCorrespondentLine(origin.getLineId()), segmentService.findCorrespondentLine(desti.getLineId()), product, true);
		vehicle.setRuta(rutaMasCorta());
		rutas.clear();
	}
	
	public void definirRutaSalida(Vehicle vehicle, Product product) {
		
	//	System.out.println(segmentService.findCorrespondentLine(product.getSegmentOrigin().getCorrespondientLineId()).getId()+ " " + product.getSegmentOrigin().getCorrespondientLineId());
		calcularRuta(segmentService.findCorrespondentLine(product.getSegmentOrigin().getCorrespondientLineId()), segmentService.findCorrespondentLine(product.getSegmentDestination().getCorrespondientLineId()), product, false);
		vehicle.setRuta(rutaMasCorta());
		rutas.clear();
	}

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

	private Parking searchAvailableParking() {
		Parking parking=null;
		for (int i=22;i<segmentList.size();i++) {//los parkines estan del 22 al 25
			parking=(Parking) segmentList.get(i);
			if(!parking.getState().equals("occupied")) break;
		}
		parking.setState("occupied");
		return parking;
	}

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
	
	public void setNextSegment(int id, Vehicle vehicle) throws InterruptedException {
 
		mutEx.acquire();	
		vehicle.setCurrentSegment(segmentService.findSegment(id));
		mutEx.release();
	}
	
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
