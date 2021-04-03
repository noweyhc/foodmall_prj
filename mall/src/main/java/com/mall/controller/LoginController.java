package com.mall.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
	public String loginSubmit(Model model,HttpSession session,HttpServletRequest request,HttpServletResponse response,String mem_id, String mem_pwd) throws IOException {

		//아이디 또는 비밀번호가 null이거나 공백인 경우
		if("".equals(mem_id) || mem_id == null || "".equals(mem_pwd)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('아이디 또는 패스워드를 입력하여 주십시오.'); location.href='/login.do';</script>");
            out.close();
            return null;
		}//end if
		
		// 만약 아이디 조회 시 일치하는 아이디가 없으면
		if(dao.validId(mem_id)== 0) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('아이디가 일치하지 않습니다.'); location.href='/login.do';</script>");
            return null;
		}//end if
		
		// 만약 비밀번호 조회 시 일치하는 비밀번호가 없으면
		if(dao.validPwd(mem_id,mem_pwd)== 0) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('비밀번호가 일치하지 않습니다.'); location.href='/login.do';</script>");
            return null;
		}//end if
		
		// 만약 세션값이 존재 한다면 
		if(session.getAttribute("login") != null) { 
		// 기존 세션 값을제거해준다. 
		session.removeAttribute("login"); 
		}//end if
		  
		String login = "";
		//만약 id가 비어있지않으면 
		if(mem_id != null) {
			System.out.println("로그인하여 세션 저장함" + mem_id);
			session.setAttribute("login", mem_id);
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
