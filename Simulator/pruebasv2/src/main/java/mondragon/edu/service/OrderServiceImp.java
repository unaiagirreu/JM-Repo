package mondragon.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mondragon.edu.clases.Order;
import mondragon.edu.orders.dao.DaoOrdersItem;

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
	public List<Order> searchReadyOrder() {
		// TODO Auto-generated method stub
		return ordersDao.searchReadyOrder();
	}

	@Override
	public boolean setStatus(Order order, String string) {
		return ordersDao.setStatus(order, string);
		
	}

	@Override
	public boolean lookForProductStatus(Order order) {
		return ordersDao.lookForProductStatus(order);
	}

}
