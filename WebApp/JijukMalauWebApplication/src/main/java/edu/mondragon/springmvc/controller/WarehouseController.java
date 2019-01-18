package edu.mondragon.springmvc.controller;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.mondragon.springmvc.configuration.AppConfig;
import edu.mondragon.springmvc.entity.Vehicle;
import edu.mondragon.springmvc.service.VehicleService;
import springmvc.beans.VehicleList;

@Controller
@RequestMapping(value="/Warehouse", method = RequestMethod.GET)
public class WarehouseController {
	
	@RequestMapping(value = "/Warehouse", method = RequestMethod.GET)
	public @ResponseBody List<Vehicle> logIn(Model model) {
	AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);
	
	VehicleService vehicleService = context.getBean(VehicleService.class);
	List <Vehicle> list = vehicleService.getAllVehicles();
	context.close();
	System.out.println(list);
	return list;

	}

}
