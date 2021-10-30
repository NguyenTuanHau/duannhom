package com.edu.service;

import java.util.List;

import com.edu.entity.Blog;
import com.edu.entity.Product;

public interface BlogService {
	List<Blog> findAll();
	Blog findById(Integer blogid);
}
