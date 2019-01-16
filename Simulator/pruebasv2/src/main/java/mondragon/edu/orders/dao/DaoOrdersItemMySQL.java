package mondragon.edu.orders.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mondragon.edu.clases.Order;
import mondragon.edu.clases.Product;

@Repository
public class DaoOrdersItemMySQL implements DaoOrdersItem{
	
	@Autowired
	private SessionFactory sessionFactory;

	public boolean add(Order order) {
		sessionFactory.getCurrentSession().save(order);
		return true;
	}

	@Transactional
	@Override
	public List<Order> searchReadyOrder() {
		List<Order> orderList=new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Order> query=sessionFactory.getCurrentSession().createQuery("from Order where status='pendiente'");
		orderList=query.getResultList();		
		return orderList;
	}

	@Transactional
	@Override
	public boolean setStatus(Order order, String string) {
		@SuppressWarnings("unchecked")
		TypedQuery<Order> query=sessionFactory.getCurrentSession().createQuery("update Order set status = '"+string + "' where id='"+order.getId()+"'");
		query.executeUpdate();
		return true;
	}

	@Transactional
	@Override
	public boolean lookForProductStatus(Order order) {
		
		List<Product> listProduct;
		@SuppressWarnings("unchecked")
		TypedQuery<Product> query=sessionFactory.getCurrentSession().createQuery("from Product where order_id='"+order.getId() +"'");
		listProduct=query.getResultList();	
		for(int i=0;i<listProduct.size();i++)
		{
			if(!listProduct.get(i).getStatus().equals("finished")) return false;
		}
		
		return true;
	}

	
}
