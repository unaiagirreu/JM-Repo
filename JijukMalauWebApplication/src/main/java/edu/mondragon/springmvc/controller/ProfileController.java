package edu.mondragon.springmvc.controller;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import edu.mondragon.springmvc.configuration.AppConfig;
import edu.mondragon.springmvc.entity.Order;
import edu.mondragon.springmvc.entity.User;
import edu.mondragon.springmvc.service.OrderService;
import edu.mondragon.springmvc.service.ProductService;

@Controller
@RequestMapping(value="/Profile", method = RequestMethod.GET)
public class ProfileController {
	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public String load(Model model,
			@SessionAttribute("user") @Validated User user) {
		AnnotationConfigApplicationContext context = 
	            new AnnotationConfigApplicationContext(AppConfig.class);
		OrderService orderService = context.getBean(OrderService.class);
		ProductService productService = context.getBean(ProductService.class);
		
		List<Order> orders=orderService.getUserOrders(user.getId());
		for(Order order:orders) {
			order.setProductList(productService.getOrderProducts(order.getId()));
			order.setPrice(productService.getTotalPrice(order.getId()));
		}
		user.setOrders(orders);
		context.close();
		return "profile";
	}
	
	@RequestMapping(value = "/operator", method = RequestMethod.GET)
	public String operator(Model model,
			@SessionAttribute("user") @Validated User user) {
		return "operatorProfile";
	}
	
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String manager(Model model,
			@SessionAttribute("user") @Validated User user) {
		return "managerProfile";
	}

}
