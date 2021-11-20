package com.edu.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.edu.dao.OrderDAO;
import com.edu.dao.OrderDetailDAO;
import com.edu.dao.ProductDAO;
import com.edu.entity.Order;
import com.edu.entity.OrderDetail;
import com.edu.entity.Product;
import com.edu.service.OrderDetailService;
import com.edu.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;



@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	@Autowired
	OrderDAO dao;
	
	@Autowired
	ProductDAO pdao;
	
	@Autowired
	OrderDetailDAO ddao;

//	@Override
//	public Order crate(JsonNode orderData) {
//		ObjectMapper mapper = new ObjectMapper();
//		
//		Order order = mapper.convertValue(orderData, Order.class);
//		dao.save(order);
//		
//		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
//		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
//			.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
//		ddao.saveAll(details);
//		
//		return order;
//	}

	@Override
	public OrderDetail findById(Long id) {
		// TODO Auto-generated method stub
		return ddao.findById(id).get();
	}

//	@Override
//	public List<OrderDetail> findByUsername(String username) {
//		// TODO Auto-generated method stub
//		return ddao.findByUsername(username);
//	}
	@Override
	public List<OrderDetail> findAll() {
		// TODO Auto-generated method stub
		return ddao.findAll();
	}

//	@Override
//	public List<OrderDetail> findByUsername(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public void delete(Long id) {
//		dao.deleteById(id);
//	}
	
//	@Override
//	public List<Product> findAlls() {
//		return pdao.findAll();
//	}
//
//	@Override
//	public Product findById(Integer productid) {
//		return pdao.findById(productid).get();
//	}
}
