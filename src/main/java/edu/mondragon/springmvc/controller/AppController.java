package edu.mondragon.springmvc.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mondragon.springmvc.configuration.AppConfig;
import edu.mondragon.springmvc.service.UserService;
import springmvc.beans.Login;
import springmvc.beans.Register;



@Controller
@RequestMapping("/")
public class AppController {

	@RequestMapping(value = { "/"}, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		AnnotationConfigApplicationContext context = 
	            new AnnotationConfigApplicationContext(AppConfig.class);
		UserService userService = context.getBean(UserService.class);
		model.addAttribute("login", new Login());
		//model.addAttribute("user", userService.loadUser("kepau", "kepa"));
		return "login";
	}

	@RequestMapping(value = { "/login"}, method = RequestMethod.GET)
	public String productsPage(ModelMap model) {
		model.addAttribute("login", new Login());
		return "login";
	}

	@RequestMapping(value = { "/register"}, method = RequestMethod.GET)
	public String contactUsPage(ModelMap model) {
		model.addAttribute("message", "");
		model.addAttribute("register", new Register());
		return "register";
	}
	
	@RequestMapping(value = { "/profile"}, method = RequestMethod.GET)
	public String profilePage(ModelMap model) {
		return "profile";
	}
	
}
	