package edu.mondragon.springmvc.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.mondragon.springmvc.entity.User;



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

}
