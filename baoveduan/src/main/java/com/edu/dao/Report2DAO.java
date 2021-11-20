package com.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.entity.Product;
import com.edu.entity.Report2DTO;

public interface Report2DAO extends JpaRepository<Product, String>{

	@Query("SELECT new Report2DTO(month(p.order.createDate) , sum(p.price*p.quantity)) FROM OrderDetail p "
			+ "GROUP BY month(p.order.createDate)")
	List<Report2DTO> findAllByCount();
}
