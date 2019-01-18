package edu.mondragon.springmvc.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import edu.mondragon.springmvc.configuration.AppConfig;
import edu.mondragon.springmvc.entity.Order;
import edu.mondragon.springmvc.service.UserService;

@Repository
public class DaoOrdersItemMySQL implements DaoOrdersItem{
	
	@Autowired
	private SessionFactory sessionFactory;

	public boolean add(Order order) {
		sessionFactory.getCurrentSession().save(order);
		return true;
	}

	@Override
	public List<Order> getUserOrders(int id) {
		TypedQuery<Order> query=sessionFactory.getCurrentSession().createQuery("from Order where userId='"+id+"'");
		return query.getResultList();
	}

	@Override
	public int getId(Order order) {
		
		int i=(int) sessionFactory.createEntityManager().getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(order);
		return i;
	}
	
	  @Override
	  public boolean setStatus(Order order, String string) {
	    @SuppressWarnings("unchecked")
	    TypedQuery<Order> query=sessionFactory.getCurrentSession().createQuery("update Order set status = '"+string + "' where id='"+order.getId()+"'");
	    query.executeUpdate();
	    return true;
	  }

	@Override
	public List<Order> getPendingOrders() {
		TypedQuery<Order> query=sessionFactory.getCurrentSession().createQuery("from Order where status='pending' or status='making'");
		return query.getResultList();
	}
	
	@Override
	public List <BigDecimal> getProductsPerOrder() {
		AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(AppConfig.class);
		UserService userService = context.getBean(UserService.class);
		
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		List <Integer> usersIdList = new ArrayList<Integer> (userService.getUsersId());
		for(int i = 0; i < userService.getProductsQuantityPerOrder().size();i++){
			TypedQuery<BigDecimal> query=sessionFactory.getCurrentSession().createNativeQuery("select avg(y.tabla) as productsPerOrder from (" + 
				"select count(*) as Tabla from product join orders on order_id = orders.id where user = "+usersIdList.get(i)+" group by order_id) y;");
			list.add(query.getSingleResult());
		}
		context.close();
		return list;
	}
	
	@Override
	public List <BigDecimal> getPricePerOrder() {
		AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(AppConfig.class);
		UserService userService = context.getBean(UserService.class);
		
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		List <Integer> usersIdList = new ArrayList<Integer> (userService.getUsersId());
		for(int i = 0; i < userService.getProductsQuantityPerOrder().size();i++){
			TypedQuery<BigDecimal> query=sessionFactory.getCurrentSession().createNativeQuery("select sum(price)/(select count(*) from orders where user = "+usersIdList.get(i)+") as pricePerOrder from product join orders on order_id = orders.id where user = "+usersIdList.get(i)+";");
			list.add(query.getSingleResult());
		}
		context.close();
		return list;
	}


	
}
