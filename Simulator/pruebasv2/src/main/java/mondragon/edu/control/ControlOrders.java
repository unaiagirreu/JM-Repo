package mondragon.edu.control;

import java.util.List;

import mondragon.edu.clases.Order;
import mondragon.edu.clases.Product;
import mondragon.edu.clases.Segment;
import mondragon.edu.clases.Workstation;

/**
 * Class for controlling orders
 * 
 * @author unaiagirre
 * @param segmentList
 *
 */
public class ControlOrders {
	
	List<Segment> segmentList;
	public ControlOrders(List<Segment> list) {
		segmentList=list;
	}
	
	/**
	 * This function add orders to his respective workstation. We send a order to the function and one by one we introduce the products
	 * to their respective workstations.
	 * 
	 * @param order the order that is going to be added
	 */
	public void addOrder(Order order) {
		List<Product> productList;
		productList=order.getProductList();
		for(int i=0;i<productList.size();i++) {
			addToWorkspace(productList.get(i), productList.get(i).getSegmentOrigin());			
		}		
		//initThreads(order);
	}
	

	/**
	 * This function adds each product of the order to a workstation and then we notify the workstation to start making the product 
	 * in case it was stopped.
	 * 
	 * @param product the product we want to add to the workstation
	 * @param workstation the workstation where the product is going to go
	 */
	private void addToWorkspace(Product product, Workstation workstation) {
		if(workstation.getListaProductos().size()==0) {
			workstation.addProduct(product);
			synchronized(workstation) {
				workstation.notify();
			}
		}
		else workstation.addProduct(product);
	}


	public List<Segment> getSegmentList() {
		return segmentList;
	}
	
	

}
