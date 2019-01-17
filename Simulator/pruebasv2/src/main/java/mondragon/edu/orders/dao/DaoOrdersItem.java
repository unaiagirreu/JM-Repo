package mondragon.edu.orders.dao;

import java.util.List;


import mondragon.edu.clases.Order;
/**
 * Interface for order item
 * 
 * @author unaiagirre
 *
 */
public interface DaoOrdersItem {
	
	boolean add(Order order);

	List<Order> searchReadyOrder();

	boolean setStatus(Order order, String string);

	boolean lookForProductStatus(Order order);

}
