package edu.mondragon.springmvc.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mondragon.springmvc.dao.DaoOrdersItem;
import edu.mondragon.springmvc.entity.Order;

@Service
public class OrderServiceImp implements OrderService{
	
	@Autowired
	private DaoOrdersItem ordersDao;

	@Transactional
	public boolean add(Order order) {
		return ordersDao.add(order);
	}

	@Transactional
	@Override
	public List<Order> getUserOrders(int id) {
		return ordersDao.getUserOrders(id);
	}

	@Transactional
	@Override
	public int getId(Order order) {
		return ordersDao.getId(order);
	}

	@Transactional
	@Override
	public boolean setStatus(Order order, String string) {
		// TODO Auto-generated method stub
		return ordersDao.setStatus(order, string);
	}

	@Transactional
	@Override
	public List<Order> getPendingOrders() {
		return ordersDao.getPendingOrders();
	}
	
	@Transactional
	public List <BigDecimal> getProductsPerOrder() {
		return ordersDao.getProductsPerOrder();
	}
	
	@Transactional
	public List <BigDecimal> getPricePerOrder() {
		return ordersDao.getPricePerOrder();
	}


}
