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

public class HearDB implements Runnable{
	
	AnnotationConfigApplicationContext context;
	ProductService productService;
	OrderService orderService;
	ControlOrders controlOrders;
	
	
	public HearDB(AnnotationConfigApplicationContext context, ControlOrders control) {
		this.context=context;
		orderService = context.getBean(OrderService.class);
		productService = context.getBean(ProductService.class);
		controlOrders=control;
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

	private void readFromDB() {
		List<Order> orderList=new ArrayList<>();
		List<Product> productList=new ArrayList<>();
		List<Segment> segmentList = new ArrayList<>();
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
				}
				Order order=new Order(orderList.get(i).getId(), productList);
				controlOrders.addOrder(order);
				orderService.setStatus(order, "making");
				
			}
		}
	}

}
