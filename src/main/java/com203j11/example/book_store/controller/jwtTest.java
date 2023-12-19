package com203j11.example.book_store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com203j11.example.book_store.jwt.JWTV;
import com203j11.example.book_store.jwt.JWTcreator;

import org.springframework.ui.Model;

@Controller
public class jwtTest 
{

  // @Autowired
  // public JWTcreator jj;
  
  @RequestMapping("/getJwt")
	public static String listallstudent(Model model) {
    JWTcreator jj = new JWTcreator();
    JWTV jv = new JWTV();
    String token = jj.creatJWT("1","dadsa");
    String isVal = jv.validate(token);
		model.addAttribute("jwt",token);
    model.addAttribute("jwtv", isVal);
		return "token";
	}
}
