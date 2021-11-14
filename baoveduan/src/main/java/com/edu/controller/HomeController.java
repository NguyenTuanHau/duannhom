package com.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.entity.Blog;
import com.edu.entity.Product;
import com.edu.service.BlogService;
import com.edu.service.ProductService;



@Controller
public class HomeController {
	@Autowired
	ProductService productService;
	
	@Autowired
	BlogService blogService;

	@RequestMapping("/home")
	public String index(Model model) {
		List<Product> list = productService.findAll();
		model.addAttribute("items", list);
		
		List<Blog> blog = blogService.findAll();
		model.addAttribute("blog",blog);
		
		String sortField = "price";
		String sortDir = "asc";
		String keyword = null;
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("keyword", keyword);
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir", reverseSortDir);
		
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
