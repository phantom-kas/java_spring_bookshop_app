package com203j11.example.book_store.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com203j11.example.book_store.model.Category;


public class CategoryMapper implements RowMapper<Category>{

	@Override
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
	
		Category Category=new Category();
		Category.setId(rs.getInt("id"));
		Category.setCategory(rs.getString("category"));
		
		return Category;
		
	}
}
