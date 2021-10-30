package com.edu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.entity.Blog;
import com.edu.entity.Product;
import com.edu.service.BlogService;

@Controller
public class BlogController {
	@Autowired
	BlogService blogService;
	@RequestMapping("/blog")
	public String blog(Model model, @RequestParam("cid")Optional<String> cid)
	{
		List<Blog> list = blogService.findAll();
		model.addAttribute("items",list);
		return "blog/Blog";
	}
	
	@GetMapping("/blogdetails/{id}")
	public String blogdetail(Model model, @PathVariable("id") Integer id) {
		Blog item = blogService.findById(id);
		model.addAttribute("item", item);
		return "blog/blog_details";
	}
	
}
