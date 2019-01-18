package edu.mondragon.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springmvc.beans.Login;
import springmvc.beans.Register;



@Controller
@RequestMapping("/")
public class AppController {

	@RequestMapping(value = { "/"}, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("login", new Login());
		//model.addAttribute("user", userService.loadUser("kepau", "kepa"));
		return "login";
	}

	@RequestMapping(value = { "/login"}, method = RequestMethod.GET)
	public String loginPage(ModelMap model) {
		model.addAttribute("login", new Login());
		return "login";
	}

	@RequestMapping(value = { "/register"}, method = RequestMethod.GET)
	public String registerPage(ModelMap model) {
		model.addAttribute("message", "");
		model.addAttribute("register", new Register());
		return "register";
	}
	
	@RequestMapping(value = { "/profile"}, method = RequestMethod.GET)
	public String profilePage(ModelMap model) {
		return "profile";
	}
	
	@RequestMapping(value = { "/confirmOrder"}, method = RequestMethod.GET)
	public String confirmOrderPage(ModelMap model) {
		return "confirmOrder";
	}
	
	@RequestMapping(value = { "/pendingOrders"}, method = RequestMethod.GET)
	public String pendingOrdersPage(ModelMap model) {
		return "pendingOrders";
	}
	
	@RequestMapping(value = { "/contact"}, method = RequestMethod.GET)
	public String contactPage(ModelMap model) {
		return "contact";
	}
	
	@RequestMapping(value = { "/warehouse"}, method = RequestMethod.GET)
	public String cwarehousePage(ModelMap model) {
		return "warehouse";
	}
	
}
	