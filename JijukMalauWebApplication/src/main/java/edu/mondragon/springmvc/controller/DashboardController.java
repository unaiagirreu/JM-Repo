package edu.mondragon.springmvc.controller;

import java.math.BigInteger;
import java.util.ArrayList;
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
import edu.mondragon.springmvc.entity.Product;
import edu.mondragon.springmvc.entity.Vehicle;
import edu.mondragon.springmvc.service.OrderService;
import edu.mondragon.springmvc.service.ProductService;
import edu.mondragon.springmvc.service.UserService;
import edu.mondragon.springmvc.service.VehicleService;
import springmvc.beans.ProductList;
import springmvc.beans.VehicleList;

@Controller
@RequestMapping(value="/Dashboards", method = RequestMethod.GET)
public class DashboardController {
	
	@RequestMapping(value = "/First", method = RequestMethod.GET)
	public @ResponseBody List<BigInteger> dashboard1(Model model) {
	@SuppressWarnings("resource")
	AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);
	ProductService productService = context.getBean(ProductService.class);
	List<BigInteger>list  = new ArrayList<BigInteger>();
	list.add(productService.getProductsQuantityPerCategoryScreen());
	list.add(productService.getProductsQuantityPerCategoryComputers());
	list.add(productService.getProductsQuantityPerCategoryLaptops());
	list.add(productService.getProductsQuantityPerCategoryPeriferals());
	list.add(productService.getProductsQuantityPerCategoryPrinters());
	context.close();
	return list;

	}
	
	@RequestMapping(value = "/Second", method = RequestMethod.GET)
	public @ResponseBody ProductList dashboard2(Model model) {
	@SuppressWarnings("resource")
	AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);
	
	ProductList list = new ProductList();
	UserService userService = context.getBean(UserService.class);
	
	list.setList1(userService.getProductsQuantityPerOrder());
	System.out.println(list);
	list.setList2(userService.getTotalPricePerUser());
	System.out.println(list);
	OrderService orderService = context.getBean(OrderService.class);
	list.setList3(orderService.getProductsPerOrder());
	System.out.println(list);
	list.setList4(orderService.getPricePerOrder());
	System.out.println(list);
	context.close();

	return list;

	}
	
	@RequestMapping(value = "/Third", method = RequestMethod.GET)
	public @ResponseBody List<BigInteger> dashboard3(Model model) {
	@SuppressWarnings("resource")
	AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);
	
	VehicleService vehicleService = context.getBean(VehicleService.class);
	List<BigInteger> list=vehicleService.getVehicleActivity();
	return list;

	}
}
