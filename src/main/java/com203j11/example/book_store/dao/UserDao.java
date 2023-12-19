package com203j11.example.book_store.dao;
import com203j11.example.book_store.mapper.UserMapper;
import com203j11.example.book_store.model.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;



import java.util.List;

@Component
public class UserDao {
  @Autowired
  private JdbcTemplate jdbcTemplate;
  public int deallogin(User u){
    String sql =  "SELECT * FROM users WHERE username = ? and password = ?";
    Object params[] = {u.getUsername() , u.getPassword()};
    List<Integer> result= jdbcTemplate.query(sql ,params,  (rs, rowNum) ->rs.getInt("id"));

    return result.isEmpty() ? 0 : result.get(0);
  }
}
