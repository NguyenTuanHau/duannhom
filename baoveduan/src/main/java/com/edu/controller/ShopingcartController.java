package com.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShopingcartController {
	@RequestMapping("/shopingcart")
	public String shopingcart() {
		return "cart/Shoping-cart";
	}
	
	@RequestMapping("/profile")
	public String profile(Model model) {
		return "security/profile";
    }
	
	@RequestMapping("/blog")
	public String blog(Model model) {
		return "layout/Blog";
    }
}
