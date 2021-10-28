package com.edu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.entity.Product;
import com.edu.service.ProductService;



@Controller
public class HomeController {
	@Autowired
	ProductService productService;


	@RequestMapping("/home")
	public String index(Model model, @RequestParam("cid")Optional<String> cid) {
		List<Product> list = productService.findAll();
		model.addAttribute("items", list);
//		if(cid.isPresent()) {
//			List<Product> list = productService.findByCategoryId(cid.get());
//			model.addAttribute("items",list);
//		}
//		else {
//			List<Product> list = productService.findAll();
//			model.addAttribute("items",list);
//		}
		return "layout/homegiua";
	}
	
	@RequestMapping("/favorite")
	public String shopingcart() {
		return "layout/favorite";
	}
	
	@RequestMapping("test")
	public String testString () {
		return "security/Change_ps";
	}

}
