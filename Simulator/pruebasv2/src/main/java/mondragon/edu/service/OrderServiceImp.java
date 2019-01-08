package mondragon.edu.service;

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

}
