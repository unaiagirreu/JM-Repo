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
		return "home";
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
	
}
	