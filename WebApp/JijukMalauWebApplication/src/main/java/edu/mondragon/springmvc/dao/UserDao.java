package edu.mondragon.springmvc.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import edu.mondragon.springmvc.entity.User;


public interface UserDao {
   boolean add(User user);
   List<User> listUsers();
   User loadUser(String username, String password);
   List<BigInteger> getProductsQuantityPerOrder();
   List<BigDecimal> getTotalPricePerUser();
   List<Integer> getUsersId();
}
