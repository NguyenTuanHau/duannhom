package com.edu.service;

import java.util.List;

import com.edu.entity.OrderDetail;
import com.edu.entity.Product;
import com.fasterxml.jackson.databind.JsonNode;



public interface OrderDetailService {

	
	OrderDetail findById(Long id);

//	List<OrderDetail> findByUsername(String username);

	List<OrderDetail> findAll();

	
//	List<Product> findAlls();
//	
//	Product findById(Integer productid);
}
