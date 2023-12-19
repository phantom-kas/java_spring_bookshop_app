package com203j11.example.book_store.dao;
import com203j11.example.book_store.mapper.CategoryMapper;
import com203j11.example.book_store.model.Category;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;



import java.util.List;

@Component
public class CategoryDao {
  @Autowired
  private JdbcTemplate jdbcTemplate;
  public List<Category> getCategories(){
    String sql =  "SELECT * FROM categories";
    
    List<Category> result=jdbcTemplate.query(sql, new CategoryMapper());

    return result;
  }
}
