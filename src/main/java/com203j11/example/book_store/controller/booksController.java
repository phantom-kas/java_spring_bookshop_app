package com203j11.example.book_store.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com203j11.example.book_store.model.Book;
import com203j11.example.book_store.model.Category;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.ui.Model;

import com203j11.example.book_store.dao.CategoryDao;
import com203j11.example.book_store.dao.BookDao;
import com203j11.example.book_store.dao.BooksCategoryDao;


@Controller
public class booksController {
  @Autowired
  private CategoryDao CategoryDao;

  @Autowired
  private BookDao BookDao;

   @Autowired
  private BooksCategoryDao BcDao;

  @Value("${upload.dir}") 
  private String uploadDir;

  private String saveImage(MultipartFile file) throws IOException {
        // Generate a unique filename or use the original filename if needed
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // Resolve the file path based on the upload directory
        Path filePath = Paths.get(uploadDir, fileName);

        // Save the file to the server
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
  };


  @RequestMapping("/create_book")
  public String createBook(Model model) throws JsonProcessingException{
    List<Category> Categories = CategoryDao.getCategories();
		model.addAttribute("categories",Categories);
    return "create_book";
  };


  @PostMapping("/handel_add_book")
  public @ResponseBody String addBook(
            @RequestParam("image") MultipartFile file, 
            @RequestParam("book_name") String bookName,
            @RequestParam("boot_author") String bookAuthor, 
            @RequestParam("dis") String description,
            @RequestParam("price") BigDecimal price,
            @RequestParam("category[]") List<Integer> categories
           // @RequestParam("category") List<Integer> categories
        )
            {
String imageName = "";
try {
   imageName = saveImage(file);
} catch (IOException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
 
}

Book book = new Book();
book.setBookName(bookName);
book.setAuthor(bookAuthor);
book.setSummary(description);
book.setPrice(price);
book.setPicture(imageName);

int bookId = BookDao.saveBook(book);



String message =  BcDao.saveBookIdCategoryId(bookId, categories);



            //   for (Integer categoryId : categories) {
            //     // Process each category ID
            //     System.out.println("Category ID: " + categoryId);
            // }
             JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "success");
        jsonObject.put("message", message);


         JSONObject alert = new JSONObject();
        alert.put("status", "success");
        alert.put("message", message);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(alert);
        
        jsonObject.put("alerts", jsonArray);

       

        


        // jsonObject.put("city", "New York");
        String jsonString = jsonObject.toString();

   return jsonString;
             // return "adasdsa "+bookId + " "+message;

  }



  @GetMapping("/books")
  public String  getBooks(Model model ,
   @RequestParam(value = "category" , required = false  ) Integer category){
    List<Category> Categories = CategoryDao.getCategories();
 List<Book> Books;
    if(category != null){
     Books = BookDao.getDataBasedOnQueryParam(category);
    }
    else {
      Books = BookDao.getBooks();
    }
		model.addAttribute("categories",Categories);
    model.addAttribute("books",Books);
    return "books";
  }

@GetMapping("/book")
  public String  getBook(Model model , @RequestParam("book") int booId){
    List<Map<String, Object>> bc = BookDao.getBookCategories(booId);
    List<Book> book;
    book = BookDao.getBookById(booId);

    model.addAttribute("book",book);
     model.addAttribute("BC",bc);
    return "book";
  }


  @GetMapping("/edit_book")
  public String  getEditBook(Model model , @RequestParam("book") int booId){
    List<Map<String, Object>> bc = BookDao.getBookCategories(booId);
    List<Book> book;
    book = BookDao.getBookById(booId);
 List<Category> Categories = CategoryDao.getCategories();
    model.addAttribute("book",book);
     model.addAttribute("BC",bc);
    
		model.addAttribute("categories",Categories);
    return "edit_book";
  };




  @PostMapping("/handel_edit_book")
  public @ResponseBody String editBOok(
            @RequestParam("book_name") String bookName,
            @RequestParam("boot_author") String bookAuthor, 
            @RequestParam("dis") String description,
            @RequestParam("price") BigDecimal price,
             @RequestParam("id") Integer bookId
        ){
          Book book = new Book();
          book.setBookName(bookName);
          book.setAuthor(bookAuthor);
          book.setSummary(description);
          book.setPrice(price);
          String jsonString;
         if( BookDao.editBook(bookId,book)){
             JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "success");
        jsonObject.put("message", "Book successfuly updated");


         JSONObject alert = new JSONObject();
        alert.put("status", "success");
        alert.put("message", "Book successfuly updated");

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(alert);
        
        jsonObject.put("alerts", jsonArray);
        // jsonObject.put("city", "New York");
         jsonString = jsonObject.toString();
           return jsonString;
         }
         else{
     JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "error");
        jsonObject.put("message", "Unknown error");


         JSONObject alert = new JSONObject();
        alert.put("status", "error");
        alert.put("message", "Unknown error");

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(alert);
        
        jsonObject.put("alerts", jsonArray);

       

        


        // jsonObject.put("city", "New York");
         jsonString = jsonObject.toString();
           return jsonString;
         }
        }



         @DeleteMapping("/handel_delete_book_category")
  public @ResponseBody String deleteBc(
    @RequestBody Map<String, Integer> requestBody
  ){
    
    Integer id = requestBody.get("id");
     
if(BookDao.deleteBookCategoryById(id)){
JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "success");
        jsonObject.put("message", "Book category deleted successfully");


         JSONObject alert = new JSONObject();
        alert.put("status", "success");
        alert.put("message",  "Book category deleted successfully");

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(alert);
        
        jsonObject.put("alerts", jsonArray);

       

        


        // jsonObject.put("city", "New York");
        String jsonString = jsonObject.toString();

   return jsonString;
}
else{
  JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "error");
        jsonObject.put("message", "Delete Error");


         JSONObject alert = new JSONObject();
        alert.put("status", "error");
        alert.put("message",  "Delete Error");

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(alert);
        
        jsonObject.put("alerts", jsonArray);

       

        


        // jsonObject.put("city", "New York");
        String jsonString = jsonObject.toString();

   return jsonString;
}


   
  }

  
   @PostMapping("/handel_add_category_to_book")
  public @ResponseBody  String addCategoryTobook(
     @RequestBody Map<String, Integer> requestBody
           
            ){
Integer bookId  = requestBody.get("book_id");
Integer category_id  = requestBody.get("category_id");
if(BookDao.addCategoryTobook(bookId, category_id)){
JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "success");
        jsonObject.put("message", "New category added for book");
         JSONObject alert = new JSONObject();
        alert.put("status", "success");
        alert.put("message",  "New category added for book");
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(alert);
        jsonObject.put("alerts", jsonArray);
        String jsonString = jsonObject.toString();

   return jsonString;
}
else{
 JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "error");
        jsonObject.put("message", "Unknown Error");
         JSONObject alert = new JSONObject();
        alert.put("status", "error");
        alert.put("message",  "Unknown Error");
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(alert);
        jsonObject.put("alerts", jsonArray);
        String jsonString = jsonObject.toString();

   return jsonString;
}

             
            }

 @PostMapping("/handel_change_book_picture")
            public @ResponseBody String changeBookPicter(
            @RequestParam("image") MultipartFile file,
            @RequestParam("book_id") Integer id)
             {

                String imageName = "";
try {
   imageName = saveImage(file);
} catch (IOException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
 
}

if(BookDao.updateBookPicture(id, imageName)){
JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "success");
        jsonObject.put("message", "Book picture updated successfully");
         JSONObject alert = new JSONObject();
        alert.put("status", "success");
        alert.put("message",  "Book picture updated successfully");
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(alert);
        jsonObject.put("alerts", jsonArray);
        String jsonString = jsonObject.toString();

              return jsonString;
}
else{
  JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "error");
        jsonObject.put("message", "Unknown Error");
         JSONObject alert = new JSONObject();
        alert.put("status", "error");
        alert.put("message",  "Unknown Error");
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(alert);
        jsonObject.put("alerts", jsonArray);
        String jsonString = jsonObject.toString();

   return jsonString;
}

             }

@DeleteMapping("/delete_book")
  public @ResponseBody String deleteBook(
    @RequestBody Map<String, Integer> requestBody
  ){
    Integer id = requestBody.get("id");
    if(BookDao.deleteBook(id)){
JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "success");
        jsonObject.put("message", "Book deleted successfully");
         JSONObject alert = new JSONObject();
        alert.put("status", "success");
        alert.put("message",  "Book deleted successfully");
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(alert);
        jsonObject.put("alerts", jsonArray);
        String jsonString = jsonObject.toString();
              return jsonString;
    }
    else{
      JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "error");
        jsonObject.put("message", "Unknown Error");
         JSONObject alert = new JSONObject();
        alert.put("status", "error");
        alert.put("message",  "Unknown Error");
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(alert);
        jsonObject.put("alerts", jsonArray);
        String jsonString = jsonObject.toString();

   return jsonString;
    }




   
  }

  
}
