package mondragon.edu.dbThread;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import mondragon.edu.clases.Order;
import mondragon.edu.clases.Product;
import mondragon.edu.clases.Segment;
import mondragon.edu.clases.Workstation;
import mondragon.edu.control.ControlOrders;
import mondragon.edu.service.OrderService;
import mondragon.edu.service.ProductService;

/**
 * Class hearing to the database looking for new orders
 * 
 * @author unaiagirre
 *
 */
public class HearDB implements Runnable{
	
	AnnotationConfigApplicationContext context;
	ProductService productService;
	OrderService orderService;
	ControlOrders controlOrders;
	List<Segment> segmentList;
	List<Product> productList;
	List<Order> orderList;
	
	
	public HearDB(AnnotationConfigApplicationContext context, ControlOrders control) {
		this.context=context;
		orderService = context.getBean(OrderService.class);
		productService = context.getBean(ProductService.class);
		controlOrders=control;
		orderList=new ArrayList<>();
		productList=new ArrayList<>();
		segmentList = new ArrayList<>();
	}
	
	public void run() {
		System.out.println("escuchando base de datos");
		while(true) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		readFromDB();
		}
	}

	/**
	 * This function looks for new orders. If a new orders is finded, it will be introduced to the simulation. We alse set
	 * the time each product consumes when generating in the workstations
	 * 
	 */
	private void readFromDB() {

		segmentList=controlOrders.getSegmentList();
		orderList=orderService.searchReadyOrder();
		if(orderList!=null) {
			for(int i=0;i<orderList.size();i++) {
				productList = productService.searchProductsByOrder(orderList.get(i).getId());
				for(int j=0;j<productList.size();j++) {
					System.out.println(productList.get(j).getName());
					productList.get(j).setSegmentDestination((Workstation) segmentList.get(productList.get(j).getSegmentDestination().getId()-1));
					productList.get(j).setSegmentOrigin((Workstation) segmentList.get(productList.get(j).getSegmentOrigin().getId()-1));
					productService.setStatus(productList.get(j), "making");
					switch (productList.get(j).getName()) {
					case "Screen":
						productList.get(j).setTime(6);
						break;
					case "Desktop Computer":
						productList.get(j).setTime(8);
						break;
					case "Workstation":
						productList.get(j).setTime(15);
						break;
					case "Keyboard":
						productList.get(j).setTime(18);
						break;
					case "Mouse":
						productList.get(j).setTime(4);
						break;
					case "Laptop 17":
						productList.get(j).setTime(12);
						break;
					case "Touchscreen Laptop":
						productList.get(j).setTime(14);
						break;
					case "Epson Printer":
						productList.get(j).setTime(25);
						break;
					case "Canon Printer":
						productList.get(j).setTime(30);
						break;
					default:
						productList.get(j).setTime(10);
						break;
					}
				}
				Order order=new Order(orderList.get(i).getId(), productList);
				controlOrders.addOrder(order);
				orderService.setStatus(order, "making");
				
			}
		}
	}

}
