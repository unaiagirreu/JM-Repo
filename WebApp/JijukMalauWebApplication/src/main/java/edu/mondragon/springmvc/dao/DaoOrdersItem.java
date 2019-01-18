package edu.mondragon.springmvc.dao;

import java.math.BigDecimal;
import java.util.List;

import edu.mondragon.springmvc.entity.Order;

public interface DaoOrdersItem {
	
	boolean add(Order order);
	List<Order> getUserOrders(int id);
	int getId(Order order);
	boolean setStatus(Order order, String string);
	List<Order> getPendingOrders();
	List<BigDecimal> getProductsPerOrder();
	List <BigDecimal> getPricePerOrder();

}
