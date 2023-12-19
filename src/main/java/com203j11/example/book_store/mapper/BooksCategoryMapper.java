package com203j11.example.book_store.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com203j11.example.book_store.model.BooksCategories;

public class BooksCategoryMapper implements RowMapper<BooksCategories>{

	@Override
	public BooksCategories mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
	
		BooksCategories BC=new BooksCategories();
		BC.setId(rs.getInt("id"));
		BC.setBookId(rs.getInt("book_id"));
		BC.setCategoryId(rs.getInt("category_id"));
		return BC;
	}
}
