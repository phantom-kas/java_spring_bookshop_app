package com203j11.example.book_store.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com203j11.example.book_store.mapper.BookMapper;
import com203j11.example.book_store.model.Book;

@Component
public class BookDao {
  @Autowired
  private JdbcTemplate jdbcTemplate;

 public Integer saveBook(@RequestBody Book book) {
        // Insert data into the database using JDBC
        String sql = "INSERT INTO books (title, author, summary, picture, price , time_created) " +
                     "VALUES (?, ?, ?, ? ,? ,CURRENT_TIMESTAMP)";

PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
             // Assuming "id" is your auto-generated key column

             ps.setString(1, book.getBookName());
            ps.setString(2, book.getAuthor());
             ps.setString(3, book.getSummary());
            ps.setString(4, book.getPicture());
              ps.setBigDecimal(5, book.getPrice());
            return ps;
        };
                
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Execute the insert operation
        jdbcTemplate.update(preparedStatementCreator, keyHolder);

        // Retrieve the generated keys
        Number generatedId = keyHolder.getKey();
        return (generatedId != null) ? generatedId.intValue() : -1;

        // jdbcTemplate.update(sql, book.getBookName(), book.getAuthor(), 
        //                     book.getSummary(), book.getPicture(), book.getPrice()
        //                     );
        
    }


    public List<Book> getDataBasedOnQueryParam(int category) {

      String sql = "SELECT b.id , b.title , b.author ,b.category  ,b.summary , b.picture , b.price , b.time_created FROM books as b  left outer join book_categories as bc  on b.id = bc.book_id  WHERE bc.category_id = ? order by b.id DESC";

      // Use queryForObject for a single result or query for multiple results
      // In this example, query is used for multiple results
      List<Book> result = jdbcTemplate.query(sql, new Object[]{category}, new BookMapper());

      return result;
  }



  public List<Book> getBooks() {
      //String sql = "SELECT b.id , b.title , b.author ,b.category  ,b.summary , b.picture , b.price , b.time_created FROM books as b  left join book_categories as bc  on b.id = bc.book_id  order by b.id DESC";
String sql = "SELECT\r\n" + //
    "    MAX(b.id) AS id,\r\n" + //
    "    MAX(b.title) AS title,\r\n" + //
    "    MAX(b.author) AS author,\r\n" + //
    "    MAX(b.summary) AS summary,\r\n" + //
    "    MAX(b.picture) AS picture,\r\n" + //
    "    MAX(b.price) AS price,\r\n" + //
    "    MAX(b.category) AS category,\r\n" + //
    "    MAX(b.time_created) AS time_created\r\n" + //
    "FROM\r\n" + //
    "    books AS b\r\n" + //
    "INNER JOIN\r\n" + //
    "    book_categories AS bc ON b.id = bc.book_id\r\n" + //
    // "WHERE\r\n" + //
    // "    bc.category_id = ? -- Specify the category ID you want\r\n" + //
    "GROUP BY\r\n" + //
    "    b.title, b.author, b.summary, b.picture, b.price    ORDER BY\r\n" + //
        "    MAX(b.id) DESC;";
      // Use queryForObject for a single result or query for multiple results
      // In this example, query is used for multiple results
      List<Book> result = jdbcTemplate.query(sql,  new BookMapper());
      return result;
  }


  public List<Book> getBookById(int id) {

      String sql = "SELECT b.id , b.title , b.author ,b.category  ,b.summary , \n"+
     " b.picture , b.price , b.time_created FROM books as b    WHERE b.id = ? Limit 1";

      // Use queryForObject for a single result or query for multiple results
      // In this example, query is used for multiple results
      List<Book> result = jdbcTemplate.query(sql, new Object[]{id}, new BookMapper());

      return result;
  }

  public  List<Map<String, Object>>  getBookCategories(int id){
   

String sql = "SELECT  bc.id , bc.book_id as book_id ,c.category  FROM book_categories as bc inner join categories as c on bc.category_id = c.id WHERE bc.book_id = ?";
    return jdbcTemplate.queryForList(sql,id);
   
    
  }



      public Boolean editBook(@PathVariable Integer bookId,Book book) 
      {
        jdbcTemplate.update(
          "UPDATE books SET title=?, author=?, summary=?,  price=? WHERE id=?",
          book.getBookName(),
          book.getAuthor(),
          book.getSummary(),
          book.getPrice(), 
          bookId
  );

        return true;
      }


      public boolean deleteBookCategoryById(Integer id) {
        String sql = "DELETE FROM book_categories WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return true;
    }



    public boolean addCategoryTobook(Integer bid , Integer cid){

      jdbcTemplate.update("INSERT INTO book_categories (book_id , category_id) VALUE (?,?) ", bid, cid);
       return true;
    }
    
    public boolean updateBookPicture(Integer id , String url){

 jdbcTemplate.update(
          "UPDATE books SET picture = ? WHERE id=?",
          url ,id);
          
      return true;
    }



    public boolean deleteBook(Integer id) {
        String sql = "DELETE FROM books WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return true;
    }





}