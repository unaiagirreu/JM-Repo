package edu.mondragon.springmvc.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;




/**
 * @author Usuario
 *
 */
/**
 * @author Usuario
 *
 */
@Entity
@Table(name = "user")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(name = "pass")
   private String pass;

   @Column(name = "username")
   private String username;

   @Column(name = "mail")
   private String mail;
   
   @Column(name = "address")
   private String address;
   
   @Column(name = "role")
   private String role;
   
   @Column(name = "name")
   private String name;
   
   @Column(name = "surname")
   private String surname;
   
   @Transient
   List<Order> orders;

   public User() {}
   
   public User(String pass, String username, String mail, String address, String role, String name, String surname) {
      this.pass=pass;
      this.username=username;
      this.mail=mail;
      this.address=address;
      this.role=role;
      this.name=name;
      this.surname=surname;
   }


   
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
	  
}
