package com.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mall.dao.user.UserDAO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final UserDAO dao;
	
	@GetMapping("/login.do")
	public String loginForm() {
		
		return "/login";
	}
	
	@PostMapping("/login.do")
	public String loginSubmit(Model model,String id, String password) {

		String validPwd = dao.getLogin(id,password); 	

		return "/login";
	}
	

}
