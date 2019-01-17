package mondragon.edu.control;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mondragon.edu.clases.Line;
import mondragon.edu.clases.Order;
import mondragon.edu.clases.Parking;
import mondragon.edu.clases.Product;
import mondragon.edu.clases.Segment;
import mondragon.edu.clases.Workstation;

public class ControlOrdersTest {
	
	ControlOrders controlOrders;
	ControlVehicles controlVehicles;
	List<Segment> SegmentList;
	
	@Before
	public void init() {
		SegmentList=new ArrayList<>();
		inicializarSegmentos();
		controlOrders=new ControlOrders(SegmentList);
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
	public void testAddOrder() {
		List<Product> productList=new ArrayList<>();
		productList.add(new Product(0,"Monitor Asus 1", (Workstation)SegmentList.get(17), (Workstation)SegmentList.get(16), 5));
		Order order=new Order(1,productList);
		controlOrders.addOrder(order);
		Workstation w=(Workstation) SegmentList.get(17);
		assertEquals("failure - product in workstation are not equal", productList.get(0),w.getListaProductos().get(0) );
	}
}
