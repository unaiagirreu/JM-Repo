package edu.mondragon.springmvc.service;

import java.util.List;

import edu.mondragon.springmvc.entity.User;


public interface UserService {
    boolean add(User user);
    List<User> listUsers();
    User loadUser(String username, String password);
}
