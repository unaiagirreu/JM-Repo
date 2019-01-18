package edu.mondragon.springmvc.service;

import java.math.BigDecimal;
import java.util.List;

import edu.mondragon.springmvc.entity.Order;

public interface OrderService {
	
	boolean add(Order order);
	List<Order> getUserOrders(int id);
	List<Order> getPendingOrders();
	int getId(Order order);
	boolean setStatus(Order order, String string);
	List <BigDecimal> getProductsPerOrder();
	List <BigDecimal> getPricePerOrder();

}
