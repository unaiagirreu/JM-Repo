package mondragon.edu.service;

import java.util.List;

import mondragon.edu.clases.Order;

public interface OrderService {
	
	boolean add(Order order);
	
	List<Order> searchReadyOrder();

	boolean setStatus(Order order, String string);

	boolean lookForProductStatus(Order order);

}
