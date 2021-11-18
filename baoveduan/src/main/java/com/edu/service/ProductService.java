package com.edu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;


import com.edu.entity.Product;


public interface ProductService {

	List<Product> findAll();
	
	List<Product> findByCategoryId(String cid);

	Product findById(Integer productid);

//	Page<Product> findAllPage(int pageNumber);
	
	Page<Product> findAllPage(int currentPage, String sortField, String sortDir, String keyword);
	
//	Page<Product> findAllPage(int currentPage, String cid);

//	Page<Product> findAllPage(int currentPage, String sortField, String sortDir, String keyword, String cid);

	List<Product> filerBetweenPrice(Double priceMin, Double priceMax);

	Product create(Product product);

	Product update(Product product);
	
	Product delete(Product product);

//	void delete(Integer productid);

	
}
