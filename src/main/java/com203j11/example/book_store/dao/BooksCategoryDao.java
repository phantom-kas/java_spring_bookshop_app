package com203j11.example.book_store.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;



@Component
public class BooksCategoryDao {
   @Autowired
  private JdbcTemplate jdbcTemplate;
  public String saveBookIdCategoryId(Integer bookId ,List<Integer> categoryIds) {
        // Insert data into the database using JDBC

        

        String sql = "INSERT INTO book_categories (book_id, category_id) " +
                     "VALUES (?, ?)";
        
        // jdbcTemplate.update(sql, bookId, BC.getCategoryId());

        for (Integer categoryId : categoryIds) {
          jdbcTemplate.update(sql, bookId, categoryId);
      }

        return "Book saved successfully!";
    }

    
}
