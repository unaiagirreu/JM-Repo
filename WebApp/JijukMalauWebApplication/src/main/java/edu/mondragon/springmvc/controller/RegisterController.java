package edu.mondragon.springmvc.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mondragon.springmvc.configuration.AppConfig;
import edu.mondragon.springmvc.entity.User;
import edu.mondragon.springmvc.service.UserService;
import springmvc.beans.Register;

@Controller
@RequestMapping(value="Register", method = RequestMethod.POST)
public class RegisterController {
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String logIn(Model model,
            @ModelAttribute("register") @Validated Register register) {
		if(!register.getEmail().equals(register.getEmailConfirm())) {
			model.addAttribute("message", "The two emails are different");
			model.addAttribute("register", register);
			return "Register";
		}
		String address=register.getStreet()+" "+register.getPortalNumber()+" "+register.getFloor()+" "+
		               register.getLetter()+" "+register.getCity()+" "+register.getPostalCode()+" "+
				       register.getCountry();
		User user=new User(register.getPassword(), register.getUsername(), register.getEmail(), address, "0", register.getName(), register.getSurname());
		AnnotationConfigApplicationContext context = 
	            new AnnotationConfigApplicationContext(AppConfig.class);
	    UserService userService = context.getBean(UserService.class);
	    userService.add(user);
		
		return "home";
		
	}

}
