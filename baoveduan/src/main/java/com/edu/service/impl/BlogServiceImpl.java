package com.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.BlogDAO;
import com.edu.entity.Blog;
import com.edu.service.BlogService;
@Service
public class BlogServiceImpl implements BlogService{
	@Autowired
	BlogDAO blgDAO;

	@Override
	public List<Blog> findAll() {
		// TODO Auto-generated method stub
		return blgDAO.findAll();
	}

	@Override
	public Blog findById(Integer blogid) {
		// TODO Auto-generated method stub
		return blgDAO.findById(blogid).get();
	}
	
}
