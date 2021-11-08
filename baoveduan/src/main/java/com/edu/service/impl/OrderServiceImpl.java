package com.edu.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.edu.dao.OrderDAO;
import com.edu.dao.OrderDetailDAO;
import com.edu.entity.Order;
import com.edu.entity.OrderDetail;
import com.edu.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;



@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDAO dao;
	
	@Autowired
	OrderDetailDAO ddao;

	@Override
	public Order crate(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		
		Order order = mapper.convertValue(orderData, Order.class);
		dao.save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
			.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
		ddao.saveAll(details);
		
		return order;
	}

	@Override
	public Order findById(Long id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public List<Order> findByUsername(String username) {
		// TODO Auto-generated method stub
		return dao.findByUsername(username);
	}
	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}
}
