package edu.mondragon.springmvc.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.mondragon.springmvc.configuration.AppConfig;
import edu.mondragon.springmvc.entity.User;
import edu.mondragon.springmvc.service.ItemService;
import edu.mondragon.springmvc.service.UserService;
import springmvc.beans.ItemList;
import springmvc.beans.Login;
import springmvc.beans.QuantityList;


@Controller
@SessionAttributes("user")
@RequestMapping(value="/Login", method = RequestMethod.GET)
public class LoginController {
	
	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	public String logOut(Model model) {
		model.addAttribute("login", new Login());
		return "login";
	}
	
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String logIn(Model model,
            @ModelAttribute("login") @Validated Login login) {
		User user=null;
		String username=login.getUsername();
		String password=login.getPassword();
		
		AnnotationConfigApplicationContext context = 
	            new AnnotationConfigApplicationContext(AppConfig.class);
		UserService userService = context.getBean(UserService.class);
		if(username!=null && password!=null){
			user = userService.loadUser(username, password);
		}
		if(user==null) {
			//model.addAttribute("message", "Incorrect user or password");
			model.addAttribute("login", new Login());
			context.close();
			return "login";
		}else {
			System.out.println(user.getId());
		}
		model.addAttribute("user", user);
		if(user.getRole().equals("0")) {
			ItemService itemService = context.getBean(ItemService.class);
			model.addAttribute("items", new ItemList(itemService.getAllItems()));
			model.addAttribute("quantity", new QuantityList());
			context.close();
			return "home";
		}else if(user.getRole().equals("1")) {
			context.close();
			return "warehouse";
		}else {
			context.close();
			return "dashboard";
		}
	}

}
