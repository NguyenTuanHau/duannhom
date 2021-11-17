package com.edu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.entity.Product;
import com.edu.service.CategoryService;
import com.edu.service.ProductService;

@Controller
public class CategoryController {
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
//	@RequestMapping("/shop/pagecate")
//	public String listByPage(Model model, @RequestParam("cid") Optional<String> cid) {
//		if(cid.isPresent()) {
//			int currentPage = 1;
//			
//			
//			Page<Product> page = productService.findAllPage(currentPage, cid.get());
//
//			List<Product> listProducts = page.getContent();
//			
//			model.addAttribute("currentPage", currentPage);
//			model.addAttribute("totalItems", page.getTotalElements());
//			model.addAttribute("totalPages", page.getTotalPages());
//			model.addAttribute("listProducts", listProducts);
//			model.addAttribute("cid", cid);
//			
//		}
//		else {
//			return listByPage(model, 1, null);
//		}
//		return "product/shopcate";
//	}
	
//	@GetMapping("/shop/pagecate/{pageNumber}")
//	public String listByPage(Model model, 
//			@PathVariable("pageNumber") int currentPage,
//			@RequestParam("cid") String cid) {
//
//		Page<Product> page = productService.findAllPage(currentPage, cid);	
//		
//		List<Product> listProducts = page.getContent();
//		
//		model.addAttribute("currentPage", currentPage);
//		model.addAttribute("totalItems", page.getTotalElements());
//		model.addAttribute("totalPages", page.getTotalPages());
//		model.addAttribute("listProducts", listProducts);
//		model.addAttribute("cid", cid);
//
//		return "product/shopcate";
//	}
	
	@RequestMapping("/shop/pagecate")
	public String index(Model model, @RequestParam("cid") Optional<String> cid) {
		if(cid.isPresent()) {
			List<Product> listProducts = productService.findByCategoryId(cid.get());
			model.addAttribute("listProducts",listProducts);
		}
		else {
			return "redirect:/shop/page";
		}
		return "product/shopcate";
	}
	
}
