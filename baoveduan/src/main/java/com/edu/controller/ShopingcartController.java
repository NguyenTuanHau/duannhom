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
	
}
