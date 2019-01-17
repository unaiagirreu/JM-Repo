package mondragon.edu.clases;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import mondragon.edu.config.AppConfig;
import mondragon.edu.control.ControlVehicles;
import mondragon.edu.service.OrderService;
import mondragon.edu.service.ProductService;
import mondragon.edu.service.VehicleService;

public class VehiclesTest {
	
	
	private static final int MIN_TIMEOUT = 15000;
	String whichAssert;
	Vehicle vehicle;
	ControlVehicles controlVehicles;
	AnnotationConfigApplicationContext context;
	VehicleService vehicleService;
	OrderService orderService;
	ProductService productService;
	List<Segment> SegmentList;
	Product product;
	Order order;
	
	
	@Before
	public void init() {
		context = 
	            new AnnotationConfigApplicationContext(AppConfig.class);
		controlVehicles=createNiceMock(ControlVehicles.class);
		vehicleService = createNiceMock(VehicleService.class);
		orderService = createNiceMock(OrderService.class);
		productService = createNiceMock(ProductService.class);
		vehicle = new Vehicle(0,controlVehicles,context);
		inicializarSegmentos();
		product=new Product(0,"Monitor Asus 1", (Workstation)SegmentList.get(17), (Workstation)SegmentList.get(16), 5);
		List<Product> lista=new ArrayList<>();
		lista.add(product);
		order = new Order(0,lista);
		product.setOrder(order);
		createMockVehicle();	
		vehicle=new Vehicle(0,controlVehicles,context);
		vehicle.setVehicleService(vehicleService);
	}


	private void createMockVehicle() {
		expect(vehicleService.setStatus(vehicle, "stoped")).andReturn(true);
		expect(vehicleService.setStatus(vehicle, "moving")).andReturn(true);
		for(int i=0;i<SegmentList.size();i++) {
			expect(vehicleService.setCurrentSegment(vehicle,SegmentList.get(i))).andReturn(true);
		}
		replay(vehicleService);
		expect(orderService.lookForProductStatus(order)).andReturn(false);
		replay(orderService);
		expect(productService.setVehicleId(product, 1)).andReturn(true);
		replay(productService);
		vehicle.setOrderService(orderService);
		vehicle.setVehicleService(vehicleService);
		vehicle.setProductService(productService);
	}
	
	 @Rule
	    public Timeout timeout = new Timeout(MIN_TIMEOUT) {
	        public Statement apply(Statement base, Description description) {
	            return new FailOnTimeout(base, MIN_TIMEOUT) {
	                @Override
	                public void evaluate() throws Throwable {
	                    try {
	                        super.evaluate();
	                        throw new TimeoutException();
	                    } catch (Exception e) {}
	                }
	            };
	        }
	    };
	    
	@Test(expected = TimeoutException.class)//es un bucle asique le ponemos un timeout pa que se salga una vez hayamos testeado la funcion que nosotros queremos testear
	public void testMove() throws InterruptedException {
		
		List<Integer> ruta=new ArrayList<>();
		ruta.add(19);
		vehicle.setRuta(ruta);
		vehicle.setCurrentSegment(SegmentList.get(18));
		whichAssert="testMove";
		vehicle.move(product);

	}
	
	@Test
	public void testGoToProduct() throws InterruptedException {
		
		List<Integer> ruta=new ArrayList<>();
		ruta.add(19);
		vehicle.setRuta(ruta);
		vehicle.setCurrentSegment(SegmentList.get(16));
		vehicle.recogerProducto(product);
	}
	
	@Test(expected = TimeoutException.class)//es un bucle asique le ponemos un timeout pa que se salga una vez hayamos testeado la funcion que nosotros queremos testear
	public void testGoToParking() throws InterruptedException {
		Parking parking=(Parking) SegmentList.get(25);
		List<Integer> ruta=new ArrayList<>();
		ruta.add(19);
		vehicle.setRuta(ruta);
		vehicle.setCurrentSegment(SegmentList.get(18));
		whichAssert="testParking";
		vehicle.goToParking(parking);
	}
	
	@After
	public void asserts() {//como los test se cortan por el timeout lo que hacemos es hacer los asserts aquí.
		if(whichAssert.equals("testMove")) {
			
			assertEquals("failure - vehicle has not moved",(Integer)19,vehicle.getCurrentSegment().getId());
			assertEquals("failure - vehicle is not stoped","stoped",vehicle.getStatus());
		}else {
			System.out.println(vehicle.getStatus());
			assertEquals("failure - vehicle has not moved",(Integer)19 , vehicle.getCurrentSegment().getId());
		}
		
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

}
