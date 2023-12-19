package com203j11.example.book_store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
  
  @RequestMapping("/")
  public static String homePage() {
return "index";
  }
}
