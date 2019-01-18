package mondragon.edu.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import mondragon.edu.clases.Line;
import mondragon.edu.clases.Product;
import mondragon.edu.clases.Segment;
import mondragon.edu.clases.Vehicle;
import mondragon.edu.config.AppConfig;
import mondragon.edu.service.SegmentService;

public class ControlVehicles {
	
	static Semaphore mutEx;
	List<Vehicle> listVehicles;
	List<Vehicle> listAvailableVehicles;
	List<Segment> segmentList;
	Map<Integer, List<Integer>> rutas;
	AnnotationConfigApplicationContext context;
	
	public ControlVehicles(AnnotationConfigApplicationContext context) {
		initVehicles();
		mutEx = new Semaphore(1);		
		rutas=new HashMap<>();
		this.context=context;
	}
	
	public void setSegmentList(List<Segment> SegmentList) {
		segmentList=SegmentList;
	}
	
	private void initVehicles() {
		listVehicles= new ArrayList<Vehicle>();
		listAvailableVehicles= new ArrayList<Vehicle>();
		Vehicle vehicle0=new Vehicle(0, this);
		Vehicle vehicle1=new Vehicle(1, this);
		Vehicle vehicle2=new Vehicle(2, this);
		Thread thread0= new Thread(vehicle0);
		Thread thread1= new Thread(vehicle1);
		Thread thread2= new Thread(vehicle2);
		thread0.setName("cocheVolador");
		thread0.setName("batmovil");
		thread0.setName("zafira");
		thread0.start();
		thread1.start();
		thread2.start();
		listVehicles.add(vehicle0);
		listVehicles.add(vehicle1);
		listVehicles.add(vehicle2);	
		listAvailableVehicles=listVehicles;
	}

	public void addVehicle(Vehicle vehicle) throws InterruptedException {
		mutEx.acquire();
		listAvailableVehicles.add(vehicle);
		mutEx.release();
		
	}

	public void callVehicle(Product product){
		try {
			mutEx.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Vehicle vehicle;
		while(listAvailableVehicles.size()==0) {			
		}
		vehicle=listAvailableVehicles.get(0);
		listAvailableVehicles.remove(0);
		vehicle.moveProduct(product);	
		definirRuta(vehicle,product);	
		vehicle.setCurrentSegment(product.getSegmentOrigin());				
		synchronized(vehicle) {
			vehicle.notify();
		}	
		mutEx.release();
	}
	
	private void definirRuta(Vehicle vehicle, Product product) {
		SegmentService segmentService = context.getBean(SegmentService.class);
		
	
		calcularRuta(segmentService.findCorrespondentLine(product.getSegmentOrigin().getCorrespondientLineId()), segmentService.findCorrespondentLine(product.getSegmentDestination().getCorrespondientLineId()));
		vehicle.setRuta(rutaMasCorta());
		rutas.clear();
	}

	public void calcularRuta(Line origin, Line destination) {
		List<Integer> ruta=new ArrayList<>();
		Line actual=origin;
		ruta.add(actual.getId());
		while(!actual.getId().equals(destination.getId())) {
	//		System.out.println(actual.nextSegment + ", "+actual.nextSegment2);
			if (actual.nextSegment2!=-1) calcularRutaAlternativa(actual.nextSegment2, ruta.toArray(new Integer[0]), destination);
			ruta.add(actual.getNextSegment());
			actual=(Line) segmentList.get(actual.getNextSegment()-1);
		}
//		System.out.println(ruta);
		rutas.put(ruta.size(), ruta);
	}
	
	public void calcularRutaAlternativa(int desvio, Integer[] ruta2, Line destination) {
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
		SegmentService segmentService = context.getBean(SegmentService.class);
		vehicle.setCurrentSegment(segmentService.findSegment(id));
		mutEx.release();
	}
	

}
