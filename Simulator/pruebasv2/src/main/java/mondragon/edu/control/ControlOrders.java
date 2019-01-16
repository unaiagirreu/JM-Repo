package mondragon.edu.control;

import java.util.List;

import mondragon.edu.clases.Order;
import mondragon.edu.clases.Product;
import mondragon.edu.clases.Segment;
import mondragon.edu.clases.Workstation;

public class ControlOrders {
	
	List<Segment> segmentList;
	public ControlOrders(List<Segment> list) {
		segmentList=list;
	}
	
	
	public void addOrder(Order order) {
		List<Product> productList;
		productList=order.getProductList();
		for(int i=0;i<productList.size();i++) {
			addToWorkspace(productList.get(i), productList.get(i).getSegmentOrigin());			
		}		
		//initThreads(order);
	}
	

	private void addToWorkspace(Product product, Workstation workstation) {
		if(workstation.getListaProductos().size()==0) {
			workstation.addProduct(product);
			synchronized(workstation) {
				System.out.println(workstation.getName()+" notifying");
				workstation.notify();
			}
		}
		else workstation.addProduct(product);
	}


	public List<Segment> getSegmentList() {
		return segmentList;
	}
	
	

}
