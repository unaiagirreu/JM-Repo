package edu.mondragon.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mondragon.springmvc.dao.UserDao;
import edu.mondragon.springmvc.entity.User;



/**
 * @author imssbora
 *
 */
@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public boolean add(User user) {
     return userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

	@Override
	public User loadUser(String username, String password) {
		return userDao.loadUser(username, password);
	}
   
   

}
