package edu.mondragon.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springmvc.beans.OrderEntry;

@Controller
@RequestMapping(value="Shop", method = RequestMethod.POST)
public class ShopController {
	List<OrderEntry> carrito=null;
	
	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public void addItem(Model model,
            @ModelAttribute("orderEntry") @Validated OrderEntry entry) {
		System.out.println("nherhgnreghi ");
		if(carrito==null) carrito=new ArrayList<>();
		carrito.add(entry);
		System.out.println(entry.getId()+"ehbfivvvvvvvvvvvvvvvvvvvvveiufivfeiuvfei");
	}

}
