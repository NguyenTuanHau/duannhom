package com.edu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
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
	
//	@RequestMapping("/shop/page")
//	public String listByPage(Model model, @RequestParam("cid") Optional<String> cid, 
//			@PathVariable("pageNumber") int currentPage,
//			@Param("sortField") String sortField,
//			@Param("sortDir") String sortDir,
//			@Param("keyword") String keyword) {
//		if(cid.isPresent()) {
////			List<Product> list = productService.findByCategoryId(cid.get());
////			model.addAttribute("items",list);
//			
//			Page<Product> page = productService.findAllPage(currentPage, sortField, sortDir, keyword, cid.get());
//			long totalItems = page.getTotalElements();
//			int totalPages = page.getTotalPages();
//			
//			List<Product> listProducts = page.getContent();
//			
//			model.addAttribute("currentPage", currentPage);
//			model.addAttribute("totalItems", totalItems);
//			model.addAttribute("totalPages", totalPages);
//			model.addAttribute("listProducts", listProducts);
//			model.addAttribute("sortField", sortField);
//			model.addAttribute("sortDir", sortDir);
//			model.addAttribute("keyword", keyword);
//			
//			String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
//			model.addAttribute("reverseSortDir", reverseSortDir);		
//		}
//		else {
//			
//			return listByPage(model, 1, "price", "asc", null);
//		}
//		return "product/shop";
//	}
//	
//	@RequestMapping("/shop/page")
//	public String viewHomePage(Model model) {
//		String 	keyword = null;
//		return listByPage(model, 1, "price", "asc", keyword);
//	}
//	
//	@GetMapping("/shop/page/{pageNumber}")
//	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage,
//			@Param("sortField") String sortField,
//			@Param("sortDir") String sortDir,
//			@Param("keyword") String keyword) {
////		 int currentPage = 1; 
//		Page<Product> page = productService.findAllPage(currentPage, sortField, sortDir, keyword);
//		long totalItems = page.getTotalElements();
//		int totalPages = page.getTotalPages();
//		
//		List<Product> listProducts = page.getContent();
//		
//		model.addAttribute("currentPage", currentPage);
//		model.addAttribute("totalItems", totalItems);
//		model.addAttribute("totalPages", totalPages);
//		model.addAttribute("listProducts", listProducts);
//		model.addAttribute("sortField", sortField);
//		model.addAttribute("sortDir", sortDir);
//		model.addAttribute("keyword", keyword);
//		
//		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
//		model.addAttribute("reverseSortDir", reverseSortDir);	
//		return "product/shop";
//	}
}
