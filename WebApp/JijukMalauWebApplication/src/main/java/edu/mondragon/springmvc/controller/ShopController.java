package edu.mondragon.springmvc.controller;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import edu.mondragon.springmvc.configuration.AppConfig;
import edu.mondragon.springmvc.entity.Item;
import edu.mondragon.springmvc.entity.Order;
import edu.mondragon.springmvc.entity.Product;
import edu.mondragon.springmvc.entity.User;
import edu.mondragon.springmvc.service.ItemService;
import edu.mondragon.springmvc.service.OrderService;
import edu.mondragon.springmvc.service.ProductService;
import edu.mondragon.springmvc.service.SegmentService;
import springmvc.beans.ItemList;
import springmvc.beans.OrderEntry;
import springmvc.beans.QuantityList;

@Controller
@RequestMapping(value="Shop", method = RequestMethod.GET)
public class ShopController {
	List<Item> carrito=null;
	Order order;
	
	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public String load(Model model,
            @ModelAttribute("orderEntry") @Validated OrderEntry entry) {
		AnnotationConfigApplicationContext context = 
	            new AnnotationConfigApplicationContext(AppConfig.class);
		ItemService itemService = context.getBean(ItemService.class);
		model.addAttribute("items", new ItemList(itemService.getAllItems()));
		model.addAttribute("quantity", new QuantityList());
		context.close();
		return "home";
	}
	
	@RequestMapping(value = "/buy", method = RequestMethod.GET)
	public String buy(Model model,
            @ModelAttribute("quantity") @Validated QuantityList quantity,
            @SessionAttribute("user") @Validated User user) {
		AnnotationConfigApplicationContext context = 
	            new AnnotationConfigApplicationContext(AppConfig.class);
		ItemService itemService = context.getBean(ItemService.class);
		OrderService orderService = context.getBean(OrderService.class);
		
		LocalDateTime now = LocalDateTime.now();
		
		List<Item> items=itemService.getAllItems();
		List<String> lista=quantity.getLista();
		long totalPrice=0;
		
		int cont=0;
		for(int i=0; i<items.size();i++) {
			items.get(i).setQuantity(Integer.valueOf(lista.get(i)));
			items.get(i).setTotalPrice(items.get(i).getPrice()*items.get(i).getQuantity());
			cont+=Integer.valueOf(lista.get(i));
		}
		if(cont>0) {
			carrito=new ArrayList<>();
			for(Item i:items) {
				if (i.getQuantity()>0) {
					carrito.add(i);
					totalPrice+=i.getQuantity()*i.getPrice();
				}
			}
			System.out.println(totalPrice);
			order=new Order();
			order.setStatus("Introducing");
			order.setUserId(user);
			order.setDate(""+now.getDayOfMonth()+"-"+now.getMonthValue()+"-"+now.getYear());
			order.setPrice(totalPrice);
			orderService.add(order);
			int orderId=orderService.getId(order);
			order.setId(orderId);
			model.addAttribute("items", new ItemList(carrito));
			model.addAttribute("order", order);
			context.close();
			return "confirmOrder";
		}
		context.close();
			
		return "shop";
	}
	
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String confirm(Model model) {
		
		AnnotationConfigApplicationContext context = 
	            new AnnotationConfigApplicationContext(AppConfig.class);
		SegmentService segmentService = context.getBean(SegmentService.class);
		ProductService productService = context.getBean(ProductService.class);
		OrderService orderService = context.getBean(OrderService.class);
		
		for(Item item:carrito) {
			int segmentId=getSegmentId(item.getCategory());
			for(int i=0;i<item.getQuantity();i++) {
				productService.add(new Product(item.getName(),segmentService.findWorkstation(segmentId),segmentService.findWorkstation(17),5,order,"pending",item.getDescription(),item.getPrice()));
			}
		}
		orderService.setStatus(order, "pending");
		ItemService itemService = context.getBean(ItemService.class);
		model.addAttribute("items", new ItemList(itemService.getAllItems()));
		model.addAttribute("quantity", new QuantityList());
		context.close();
		return "home";
	}

	private int getSegmentId(String name) {
		switch(name) {
		case "Screen": return 18;
		case "Laptop": return 19;
		case "Desktop Computer": return 20;
		case "Printer": return 21;
		case "Peripherals": return 22;
		default: return 17;
		}
	}
	

}
