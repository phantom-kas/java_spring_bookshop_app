package com203j11.example.book_store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import com203j11.example.book_store.dao.UserDao;
import com203j11.example.book_store.jwt.JWTV;
import com203j11.example.book_store.jwt.JWTcreator;
import com203j11.example.book_store.model.User;

import java.io.StringWriter;

import org.json.JSONObject;
import org.json.JSONArray;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class UserController {
  @Autowired
private UserDao dao;

  @RequestMapping("/login")
  public String login(){
    return "login";
  }


    @RequestMapping("/register")
  public String register(){
    return "register";
  }


@GetMapping("/testjson")
  public @ResponseBody String testJson(){
    JSONObject outerObject = new JSONObject();
    // Creating an array of JSON objects
    JSONArray jsonArray = new JSONArray();
    // Creating JSON objects to add to the array
    JSONObject innerObject1 = new JSONObject();
    innerObject1.put("key1", "value1");
    innerObject1.put("key2", "value2");
    JSONObject innerObject2 = new JSONObject();
    innerObject2.put("key3", "value3");
    innerObject2.put("key4", "value4");
    // Adding JSON objects to the array
    jsonArray.put(innerObject1);
    jsonArray.put(innerObject2);
    // Adding the array to the outer object
    outerObject.put("arrayOfObjects", jsonArray);
        // Convert JSON object to a string
      

        // Get the JSON string
        String jsonString = outerObject.toString();
        return jsonString;
  }


  @PostMapping("/handellogin")
  public @ResponseBody String deallogin(User u , Model model){
    Integer result = dao.deallogin(u);
    if(result != 0) {
    JWTcreator jj = new JWTcreator();
    String token = jj.creatJWT(Integer.toString(result),"dadsa");
      JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "success");
        jsonObject.put("message", "login successful");
         JSONObject alert = new JSONObject();
        alert.put("status", "success");
        alert.put("message", "login successful");
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(alert);
        jsonObject.put("alerts", jsonArray);
        jsonObject.put("data", result);
        JSONObject tkn = new JSONObject();
        tkn.put("token", token);
jsonObject.put("data", tkn);
        // jsonObject.put("city", "New York");
        String jsonString = jsonObject.toString();
   return jsonString;
   }
    else{
JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "error");
        jsonObject.put("message", "login error");
         JSONObject alert = new JSONObject();
        alert.put("status", "error");
        alert.put("message", "login error");
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(alert);
        
        jsonObject.put("alerts", jsonArray);
        // jsonObject.put("city", "New York");
        String jsonString = jsonObject.toString();

   return jsonString;
   
  }
}



}
