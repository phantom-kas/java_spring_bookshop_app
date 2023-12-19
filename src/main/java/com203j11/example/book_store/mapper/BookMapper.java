package com203j11.example.book_store.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com203j11.example.book_store.model.Book;

public class BookMapper implements RowMapper<Book>{

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
	
		Book Book=new Book();
		Book.setId(rs.getInt("id"));
		Book.setPrice(rs.getBigDecimal("price"));
		Book.setBookName(rs.getString("title"));
    Book.setSummary(rs.getString("summary"));
		Book.setCategory(rs.getInt("category"));
    Book.setPicture(rs.getString("picture"));
    Book.setAuthor(rs.getString("author"));
		return Book;
	}


}
