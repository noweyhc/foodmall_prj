package com.mall.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mall.dao.user.UserDAO;
import com.mall.interceptor.LoginSessionListener;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final UserDAO dao;
	private final LoginSessionListener lsl;
	
	@GetMapping("/login.do")
	public String loginForm() {
		return "/login";
	}
	
	@PostMapping("/login.do")
	public String loginSubmit(Model model,HttpSession session,String id, String password,HttpServletRequest request,HttpServletResponse response) {

		// 비밀번호 유효검사		
		String validPwd = dao.getLogin(id,password); 	

		if(!validPwd.equals("password")) {

		}//end if
		
		
		  // 만약 세션값이 존재 한다면 
		  if(session.getAttribute("login") != null) { 
		  // 기존 세션 값을제거해준다. 
		  session.removeAttribute("login"); 
		  }//end if
		  
		  String login = "";
		   //만약 id가 비어있지않으면 
		  if(id != null) {
			  session.setAttribute("login", id);
		  }
		  
		  login = (String)session.getAttribute("login");

		  System.out.println(login);
		  
		return "forward:/";
	}//loginSubmit
	
	/*
	@GetMapping("/kakaoLoginOK.do")
	@ResponseBody
	public String kakaoLoginOK(String email) {
		return email + "카카오 로그인 완료";
	}
	*/
}
