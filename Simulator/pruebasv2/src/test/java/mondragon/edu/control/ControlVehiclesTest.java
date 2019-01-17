package mondragon.edu.control;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import mondragon.edu.clases.Line;
import mondragon.edu.clases.Parking;
import mondragon.edu.clases.Product;
import mondragon.edu.clases.Segment;
import mondragon.edu.clases.Vehicle;
import mondragon.edu.clases.Workstation;
import mondragon.edu.config.AppConfig;
import mondragon.edu.service.ProductService;
import mondragon.edu.service.SegmentService;
import mondragon.edu.service.VehicleService;

public class ControlVehiclesTest{
	
	ControlVehicles controlVehicles;
	List<Segment> SegmentList;
	SegmentService segmentService;
	ProductService productService;
	VehicleService vehicleService;
	Vehicle vehicle0;
	Vehicle vehicle1;
	Vehicle vehicle2;
	Vehicle vehicle3;
	Vehicle vehicle4;
	AnnotationConfigApplicationContext context;
	
	@Before
	public void init() {
		context = 
	            new AnnotationConfigApplicationContext(AppConfig.class);
		controlVehicles=new ControlVehicles(context);
		inicializarSegmentos();
		segmentService = createNiceMock(SegmentService.class);
		productService = createNiceMock(ProductService.class);
		vehicleService = createNiceMock(VehicleService.class);
		createMockSegment();
	}
	
	private void createMockSegment() {//creacion de mock para el segmentService (conexion a la base de datos)
		expect(segmentService.findCorrespondentLine(1)).andReturn((Line) SegmentList.get(0));
		expect(segmentService.findCorrespondentLine(2)).andReturn((Line) SegmentList.get(1));
		expect(segmentService.findCorrespondentLine(3)).andReturn((Line) SegmentList.get(2));
		expect(segmentService.findCorrespondentLine(4)).andReturn((Line) SegmentList.get(3));
		expect(segmentService.findCorrespondentLine(5)).andReturn((Line) SegmentList.get(4));
		expect(segmentService.findCorrespondentLine(6)).andReturn((Line) SegmentList.get(5));
		expect(segmentService.findCorrespondentLine(7)).andReturn((Line) SegmentList.get(6));
		expect(segmentService.findCorrespondentLine(8)).andReturn((Line) SegmentList.get(7));
		expect(segmentService.findCorrespondentLine(9)).andReturn((Line) SegmentList.get(8));
		expect(segmentService.findCorrespondentLine(10)).andReturn((Line) SegmentList.get(9));
		expect(segmentService.findCorrespondentLine(11)).andReturn((Line) SegmentList.get(10));
		expect(segmentService.findCorrespondentLine(12)).andReturn((Line) SegmentList.get(11));
		expect(segmentService.findCorrespondentLine(13)).andReturn((Line) SegmentList.get(12));
		expect(segmentService.findCorrespondentLine(14)).andReturn((Line) SegmentList.get(13));
		expect(segmentService.findCorrespondentLine(15)).andReturn((Line) SegmentList.get(14));
		expect(segmentService.findCorrespondentLine(16)).andReturn((Line) SegmentList.get(15));
		for(int i=0;i<16;i++) {
			expect(segmentService.findSegment(i)).andReturn((Line) SegmentList.get(i));
		}
		for(int i=16;i<22;i++) {
			expect(segmentService.findSegment(i)).andReturn((Workstation) SegmentList.get(i));
		}
		for(int i=22;i<26;i++) {
			expect(segmentService.findSegment(i)).andReturn((Parking) SegmentList.get(i));
		}
		replay(segmentService);
		controlVehicles.setSegmentService(segmentService);
		controlVehicles.setSegmentList(SegmentList);
		
	}

	private void inicializarSegmentos() {
		SegmentList=new ArrayList<Segment>();
		
    	SegmentList.add(new Line(1, 2, 13, controlVehicles));//-1 equivale a null
    	SegmentList.add(new Line(2, 3, -1, controlVehicles));
    	SegmentList.add(new Line(3, 4, 15, controlVehicles));
    	SegmentList.add(new Line(4, 5, -1, controlVehicles));
    	SegmentList.add(new Line(5, 6, -1, controlVehicles));
    	SegmentList.add(new Line(6, 7, -1, controlVehicles));
    	SegmentList.add(new Line(7, 8, 16, controlVehicles));
    	SegmentList.add(new Line(8, 9, -1, controlVehicles));
    	SegmentList.add(new Line(9, 10, 14, controlVehicles));
    	SegmentList.add(new Line(10, 11, -1, controlVehicles));
    	SegmentList.add(new Line(11, 12, -1, controlVehicles));
    	SegmentList.add(new Line(12, 1, -1, controlVehicles));
    	SegmentList.add(new Line(13, 11, -1, controlVehicles));
    	SegmentList.add(new Line(14, 3, -1, controlVehicles));
    	SegmentList.add(new Line(15, 9, -1, controlVehicles));
    	SegmentList.add(new Line(16, 5, -1, controlVehicles));
    	
     	SegmentList.add(new Workstation(17,"workspace 1",  SegmentList.get(0).getId(), controlVehicles));
     	SegmentList.add(new Workstation(18,"workspace 2",  SegmentList.get(2).getId(), controlVehicles));
     	SegmentList.add(new Workstation(19,"workspace 3",  SegmentList.get(4).getId(), controlVehicles));
     	SegmentList.add(new Workstation(20,"workspace 4",  SegmentList.get(6).getId(), controlVehicles));
     	SegmentList.add(new Workstation(21,"workspace 5",  SegmentList.get(8).getId(), controlVehicles));
     	SegmentList.add(new Workstation(22,"workspace 6",  SegmentList.get(10).getId(), controlVehicles));
     	
     	SegmentList.add(new Parking(23,"parking 1", SegmentList.get(1).getId(), controlVehicles)); 
     	SegmentList.add(new Parking(24,"parking 2", SegmentList.get(3).getId(), controlVehicles)); 
     	SegmentList.add(new Parking(25,"parking 3", SegmentList.get(7).getId(), controlVehicles)); 
     	SegmentList.add(new Parking(26,"parking 4", SegmentList.get(9).getId(), controlVehicles));    
     	SegmentList.get(22).setState("free");
    }
	
	@Test
	public void testAddVehicle() throws InterruptedException {
		controlVehicles.addVehicle(new Vehicle());
	}
	
	@Test
	public void testRutaRecogida() {//esta funcion le asigna una ruta al vehiculo desde donde esta hasta el product. comparamos la ruta que se le asigna con la que deberia de ser
		vehicle0=controlVehicles.getVehicle0();
		vehicle0.setCurrentSegment(SegmentList.get(23));
		vehicle0.setDestinationSegment(SegmentList.get(21));
		controlVehicles.setVehicle0(vehicle0);
		Product product=new Product(0,"Monitor Asus 1", (Workstation)SegmentList.get(17), (Workstation)SegmentList.get(16), 5);
		controlVehicles.definirRutaRecogida(vehicle0, product);
		List<Integer> ruta=new ArrayList<>();
		for(int i=4;i<12;i++) {
			ruta.add(i);
		}
		ruta.add(18);
		assertEquals("failure - route are not equal", vehicle0.getRuta(),ruta );
	}
	
	@Test
	public void testRutaSalida() {//esta funcion le asigna una ruta al vehiculo desde donde recoge el producto hasta el destino del producto. comparamos la ruta que se le asigna con la que deberia de ser
		vehicle1=controlVehicles.getVehicle1();
		vehicle1.setCurrentSegment(SegmentList.get(17));
		vehicle1.setDestinationSegment(SegmentList.get(16));
		controlVehicles.setVehicle1(vehicle1);
		Product product=new Product(0,"Monitor Asus 1", (Workstation)SegmentList.get(17), (Workstation)SegmentList.get(16), 5);
		controlVehicles.definirRutaSalida(vehicle1, product);
		List<Integer> ruta=new ArrayList<>();
		ruta.add(3);ruta.add(15);ruta.add(9);ruta.add(10);ruta.add(11);ruta.add(12);ruta.add(1);ruta.add(17);
		assertEquals("failure - route are not equal", vehicle1.getRuta(),ruta );
	}
	
	@Test
	public void testRutaParking() {//esta funcion le asigna una ruta al vehiculo desde donde recoge el producto hasta el destino del producto. comparamos la ruta que se le asigna con la que deberia de ser
		vehicle2=controlVehicles.getVehicle2();
		vehicle2.setCurrentSegment(SegmentList.get(16));
		vehicle2.setDestinationSegment(SegmentList.get(25));
		controlVehicles.setVehicle2(vehicle2);
		controlVehicles.definirRutaParking(vehicle2);
		List<Integer> ruta=new ArrayList<>();
		ruta.add(1);ruta.add(2);
		assertEquals("failure - route are not equal", vehicle2.getRuta(),ruta );
	}
	
	@Test
	public void testSetNextSegment() {
		int id=0;
		vehicle3=controlVehicles.getVehicle3();
		vehicle3.setCurrentSegment(SegmentList.get(16));
		controlVehicles.setVehicle3(vehicle3);
		try {
			controlVehicles.setNextSegment(id,vehicle3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(vehicle3.getCurrentSegment().getId());
		assertEquals("failure - current segment are not equal", vehicle3.getCurrentSegment(),SegmentList.get(0));
	}

	@Test(timeout=10000)
	public void testTakeNextLine() {
		
		
		try {
			controlVehicles.takeNextLine(17, true, vehicle0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Workstation w = (Workstation) SegmentList.get(16);
		assertFalse("failure - it takes priority and it is suppose to not take it", w.askForPriority17());
	}
	
	/*
	@SuppressWarnings("null")
	@Test
	public void testCallVehicle() throws InterruptedException {
		Product product=new Product(0,"Monitor Asus 1", (Workstation)SegmentList.get(17), (Workstation)SegmentList.get(16), 5);

		for(int i=0;i<SegmentList.size();i++) {
		expect(vehicleService.setCurrentSegment(vehicle0, SegmentList.get(i))).andReturn(true);
		expect(vehicleService.setCurrentSegment(vehicle1, SegmentList.get(i))).andReturn(true);
		expect(vehicleService.setCurrentSegment(vehicle2, SegmentList.get(i))).andReturn(true);
		expect(vehicleService.setCurrentSegment(vehicle3, SegmentList.get(i))).andReturn(true);
		expect(vehicleService.setCurrentSegment(vehicle4, SegmentList.get(i))).andReturn(true);
		}
		
		expect(productService.setVehicleId(product, 1)).andReturn(true);
		expect(productService.setVehicleId(product, 2)).andReturn(true);
		expect(productService.setVehicleId(product, 3)).andReturn(true);
		expect(productService.setVehicleId(product, 4)).andReturn(true);
		expect(productService.setVehicleId(product, 5)).andReturn(true);
		replay(vehicleService);
		replay(productService);
		
		vehicle0=controlVehicles.getVehicle0();
		vehicle1=controlVehicles.getVehicle1();
		vehicle2=controlVehicles.getVehicle2();
		vehicle3=controlVehicles.getVehicle3();
		vehicle4=controlVehicles.getVehicle4();
		
		vehicle0.setProductService(productService);
		vehicle0.setVehicleService(vehicleService);
		
		vehicle1.setProductService(productService);
		vehicle1.setVehicleService(vehicleService);
		
		vehicle2.setProductService(productService);
		vehicle2.setVehicleService(vehicleService);
	
		vehicle3.setProductService(productService);
		vehicle3.setVehicleService(vehicleService);
	
		vehicle4.setProductService(productService);
		vehicle4.setVehicleService(vehicleService);
		
		
		controlVehicles.setVehicle0(vehicle0);
		controlVehicles.setVehicle1(vehicle1);
		controlVehicles.setVehicle2(vehicle2);
		controlVehicles.setVehicle4(vehicle4);
		
		controlVehicles.callVehicle(product);
	}*/

}
