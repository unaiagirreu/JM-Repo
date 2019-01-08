package mondragon.edu.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import mondragon.edu.clases.Line;
import mondragon.edu.clases.Order;
import mondragon.edu.clases.Parking;
import mondragon.edu.clases.Product;
import mondragon.edu.clases.Segment;
import mondragon.edu.clases.Workstation;
import mondragon.edu.config.AppConfig;
import mondragon.edu.control.ControlOrders;
import mondragon.edu.control.ControlVehicles;
import mondragon.edu.segment.dao.DaoSegmentItemMySQL;
import mondragon.edu.service.SegmentService;

public class Main {
	
	List<Segment> SegmentList;
	List<Product> order1;
	Order order;
	ControlOrders controlOrders;
	ControlVehicles controlVehicles;
	
	public Main() {
		
		AnnotationConfigApplicationContext context = 
	            new AnnotationConfigApplicationContext(AppConfig.class);
		
		controlVehicles=new ControlVehicles(context);
		inicializar();
		controlVehicles.setSegmentList(SegmentList);
		addOrder();
		
		
	}
	
    private void addSegmentsToDatabase() {
    	//SegmentItemFacade database= new SegmentItemFacade();
		for (Segment s: SegmentList) {
			//database.saveSegment(s);
		}
	}

	private void addOrder() {
		controlOrders=new ControlOrders();
		controlOrders.addOrder(order);
		
	}

	public static void main(String[] args) throws Exception {

    	@SuppressWarnings("unused")
		Main main= new Main();
 
    	
    }


	public void inicializar() {
    	inicializarSegmentos();
    	inicializarOrders();
    }
    

    private void inicializarOrders() {
    	order1=new ArrayList<Product>();
    	order1.add(new Product(0,"alfajores 1", (Workstation)SegmentList.get(17), (Workstation)SegmentList.get(16), 5));
    	order1.add(new Product(1,"alfajores 2", (Workstation)SegmentList.get(17), (Workstation)SegmentList.get(16), 5));
    	order1.add(new Product(2,"alfajores 3", (Workstation)SegmentList.get(17), (Workstation)SegmentList.get(16), 5));
    	order1.add(new Product(3,"perrunillas 1", (Workstation)SegmentList.get(18), (Workstation)SegmentList.get(16), 7));
    	order1.add(new Product(4,"perrunillas 2", (Workstation)SegmentList.get(18), (Workstation)SegmentList.get(16), 7));
		order=new Order(1, order1);
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
     	
     	SegmentList.add(new Parking(31,"parking 1", SegmentList.get(1).getId(), controlVehicles)); 
     	SegmentList.add(new Parking(32,"parking 2", SegmentList.get(3).getId(), controlVehicles)); 
     	SegmentList.add(new Parking(33,"parking 3", SegmentList.get(7).getId(), controlVehicles)); 
     	SegmentList.add(new Parking(34,"parking 4", SegmentList.get(9).getId(), controlVehicles));    	
    }

}
