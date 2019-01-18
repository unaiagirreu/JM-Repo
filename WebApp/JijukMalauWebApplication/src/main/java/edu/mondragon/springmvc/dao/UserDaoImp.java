package edu.mondragon.springmvc.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import edu.mondragon.springmvc.configuration.AppConfig;
import edu.mondragon.springmvc.entity.User;
import edu.mondragon.springmvc.service.UserService;



/**
 * @author imssbora
 *
 */
@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public boolean add(User user) {
	  System.out.println(user.getId()+user.getUsername()+user.getPass());
      sessionFactory.getCurrentSession().save(user);
      return true;
   }

   @Override
   public List<User> listUsers() {
      @SuppressWarnings("unchecked")
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

	@Override
	public User loadUser(String username, String password) {
		String q="from User where username='"+username+"'and pass='"+password+"'";
		@SuppressWarnings("unchecked")
	    Query query=sessionFactory.openSession().createQuery(q);
		System.out.println(query.getResultList());
		User user = null;
		if(query.getResultList().size()>0) user=(User) query.getResultList().get(0);
		return user;
	}
	
	@Override
	public List<BigInteger> getProductsQuantityPerOrder() {
		@SuppressWarnings("unchecked")
	    TypedQuery<BigInteger> query=sessionFactory.getCurrentSession().createNativeQuery("select user,count(*) as userOrders from orders group by user;");
	    return query.getResultList();		
	}
	
	@Override
	 public List<BigDecimal> getTotalPricePerUser(){
		AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(AppConfig.class);

		UserService userService = context.getBean(UserService.class);
		List <Integer> usersIdList = new ArrayList<Integer> (userService.getUsersId());
		List<BigDecimal> list = new ArrayList<BigDecimal>();

		for(int i = 0; i < userService.getProductsQuantityPerOrder().size();i++){
			@SuppressWarnings("unchecked")
			TypedQuery<BigDecimal> query = sessionFactory.getCurrentSession().createNativeQuery("select sum(price) as precioTotal from product join orders on order_id = orders.id where user = "+usersIdList.get(i)+";");
			list.add(query.getSingleResult());
		}
		context.close();
		return list;		
	}
	
	@Override
	public List<Integer> getUsersId() {
		@SuppressWarnings("unchecked")
	    TypedQuery<Integer> query=sessionFactory.getCurrentSession().createNativeQuery("select distinct user from orders;");
	    return query.getResultList();		
	}

}
