package com.edu.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.entity.OrderDetail;
import com.edu.entity.Product;
import com.edu.service.OrderDetailService;
import com.edu.service.ProductService;
import com.fasterxml.jackson.databind.JsonNode;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orderdetail")
public class OrderDetailRestController {
	@Autowired
	OrderDetailService orderdetailService;
//	@Autowired
//	ProductService productService;

//	@PostMapping()
//	public OrderDetail create(@RequestBody JsonNode orderData) {
//		return orderdetailService.crate(orderData);
//	}

	@GetMapping()
	public List<OrderDetail> findAll() {
		return orderdetailService.findAll();
	}
	
//	@GetMapping()
//	public List<Product> findAlls() {
//		return productService.findAll();
//	}

//	@DeleteMapping("{id}")
//	public void delete(@PathVariable("id") Long id) {
//		orderService.delete(id);
//	}
}
