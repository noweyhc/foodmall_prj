package com.mall.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.dao.user.UserDAO;
import com.mall.interceptor.LoginSessionListener;
import com.mall.smswebservice.BanchanSms;
import com.mall.vo.mypage.IdInquiryVo;
import com.mall.vo.mypage.PwdInquiryVo;
import com.mall.vo.mypage.PwdPhoneAuthVo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("login")
public class LoginController {
	
	private final UserDAO dao;
	private final JavaMailSender javaMailSender;
	private final LoginSessionListener lsl;
	
	@GetMapping("userLogin")
	public String loginForm() {
		return "login/userLogin";
	}
	
	@PostMapping("userLogin")
	public String loginSubmit(Model model,HttpSession session,HttpServletRequest request,HttpServletResponse response,String mem_id, String mem_pwd) throws IOException {

		//아이디 또는 비밀번호가 null이거나 공백인 경우
		if("".equals(mem_id) || mem_id == null || "".equals(mem_pwd)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('아이디 또는 패스워드를 입력하여 주십시오.'); location.href='/login/userLogin';</script>");
            out.close();
            return null;
		}//end if
		
		// 만약 아이디 조회 시 일치하는 아이디가 없으면
		if(dao.validId(mem_id)== 0) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('아이디가 일치하지 않습니다.'); location.href='/login/userLogin';</script>");
            return null;
		}//end if
		
		// 만약 비밀번호 조회 시 일치하는 비밀번호가 없으면
		if(dao.validPwd(mem_id,mem_pwd)== 0) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('비밀번호가 일치하지 않습니다.'); location.href='/login/userLogin';</script>");
            return null;
		}//end if
		
		// 만약 세션값이 존재 한다면 
		if(session.getAttribute("login") != null) { 
		// 기존 세션 값을제거해준다. 
		session.removeAttribute("login"); 
		}//end if
		  
		//만약 id가 비어있지않으면 
		if(mem_id != null) {
			session.setAttribute("login", mem_id);
		}
		  
		return "redirect:/";
	}//loginSubmit
	
	@GetMapping("logout")
	public String logout(HttpSession session){
		
		String mem_id = (String) session.getAttribute("login");

		if(mem_id != null) {
			session.invalidate();
		}

		return "redirect:/";
	}
	
	// 아이디 찾기 뷰페이지
	@GetMapping("/idInquiry")
	public String idInquiry(Model model) {

		return "/login/idInquiry";
	}
	
	// 아이디 찾기 - 회원 정보에 등록한 휴대전화로 인증
	@PostMapping("/phoneAuth")
	@ResponseBody
	public IdInquiryVo phoneAuth(Model model, @RequestBody HashMap<String, Object> map) {

		int cnt = dao.phoneAuth(map);
		String mem_id = dao.phoneAuthGetId(map);

		IdInquiryVo iiVo = new IdInquiryVo();

			String mem_phone = (String)map.get("mem_phone");
			// 랜덤객체 생성
			Random random = new Random();
			// 인증을 위한 랜덤 값 추출
			int chkCode = random.nextInt(888888) + 111111;
			String code = String.valueOf(chkCode);
			// 클라이언트에게 전송할 문자 내용
			String content = "[밥도둑]을 항상 이용해주셔서 항상 감사합니다\n\n"
					+ "인증 번호는[" + code + "]입니다." ;
			// 핸드폰 문자를 보내기 위한 객체
			BanchanSms sms = new BanchanSms();
			// 발신자 / 인증을 요청한 번호 / 문자 내용
			sms.sendMsg("01086469577", mem_phone, content);

			iiVo.setMem_id(mem_id);
			iiVo.setResult(cnt);
			
			iiVo.setCode(code);
		return iiVo;
	}//phoneAuth
	
	@PostMapping("/viewIdList")
	public String viewIdList(Model model,String mem_id) {
		System.out.println("a");
		model.addAttribute("mem_id", mem_id);
		
		return "/login/viewIdList";
	}//viewIdList
	
	
	@PostMapping("/emailAuth")
	@ResponseBody
	public IdInquiryVo emailAuth(Model model, @RequestBody HashMap<String, Object> map) {
		
		// 회원 이메일이 존재하는지 확인
		// 존재하면 1 존재하지 않으면 0
		int cnt = dao.emailAuth(map);
		
		System.out.println(map);
		
		// 이름과 이메일주소로 회원 아이디를 가져옴
		String mem_id = dao.EmailAuthGetId(map);
		
		IdInquiryVo iiVo = new IdInquiryVo();
		
		String email = (String) map.get("mem_email");

			// 랜덤함수 객체
			Random random = new Random();
			// 인증을 위한 랜덤 값 추출
			int chkCode = random.nextInt(888888) + 111111;
			String code = String.valueOf(chkCode);
	
			// 인증 코드 메일 제목
			String subject = "[더 반찬] 본인 확인 인증 메시지입니다.";
			// 인증 코드 메일 내용
			String content = "더 반찬을 항상 이용해주셔서 항상 감사합니다\n\n"
							+ "인증 번호는[" + code + "]입니다.\n" 
							+ "해당 인증번호를 인증번호 확인란에 기입해주시면 감사하겠습니다.\n"
							+ "오늘도 즐거운 쇼핑되세요.";
			// 인증 코드 메일 보내기
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			
			mailMessage.setSubject(subject); // 제목
			mailMessage.setFrom("jewelrye6"); // 보내는 계정
			mailMessage.setText(content); // 내용
			mailMessage.setTo(email); // 보낼 메일 계정
			
			// 메일 전송
			javaMailSender.send(mailMessage);
			
			iiVo.setMem_id(mem_id);
			iiVo.setResult(cnt);
			iiVo.setCode(code);
			
			return iiVo;
	}//emailAuth
	
	// 비밀번호 찾기
	// 비밀번호 찾기 view
	@GetMapping("/pwdInquiry")
	public String pwdInquiry() {
		return "login/pwdInquiry";
	}//pwdInquiry
	
	// 비밀번호 찾기 Process
	@PostMapping("/pwdInquiry")
	public String pwdInquiryPro(Model model,String mem_id) {

		PwdInquiryVo pwdInquiry = dao.findPwdUserId(mem_id);
		
		if(mem_id.equals(pwdInquiry.getMem_id())) {
			model.addAttribute("pVo",pwdInquiry);
			return "login/viewPwdAuth";
		}
		
		return "login";
	}
	
	// 비밀번호 찾기 ajax으로 회원정보에 등록한 휴대전화번호로 인증
	@PostMapping("/pwdPhoneAuth")
	@ResponseBody
	public PwdPhoneAuthVo pwdPhoneAuth(Model model,@RequestBody HashMap<String, Object> map) {
		
		String mem_name = (String)map.get("mem_name");
		String mem_phone = (String)map.get("mem_phone");

		int re = dao.pwdPhoneAuth(mem_name,mem_phone);
		String mem_id = dao.getMemId(mem_name,mem_phone);
		String code = "";
		
		PwdPhoneAuthVo pVo = new PwdPhoneAuthVo();
		
		if(re == 1) {
			// 랜덤객체 생성
			Random random = new Random();
			// 인증을 위한 랜덤 값 추출
			int chkCode = random.nextInt(888888) + 111111;
			code = String.valueOf(chkCode);
			// 클라이언트에게 전송할 문자 내용
			String content = "[밥도둑]을 항상 이용해주셔서 항상 감사합니다\n\n"
					+ "인증 번호는[" + code + "]입니다." ;
			// 핸드폰 문자를 보내기 위한 객체
			BanchanSms sms = new BanchanSms();
			// 발신자 / 인증을 요청한 번호 / 문자 내용
			sms.sendMsg("01086469577", mem_phone, content);

			pVo.setCode(code);
			pVo.setResult(re);
			pVo.setMem_id(mem_id);
		}else {
			pVo.setResult(0);
		}
		
		return pVo;
	}//pwdPhoneAuth
	
	@PostMapping("/viewInputPasswd")
	public String viewInputPasswd(Model model, String mem_id) {
		
		model.addAttribute("mem_id",mem_id);
		
		return "/login/viewInputPasswd";
	}
	
	@PostMapping("/viewInputProPwd")
	public String viewInputProPwd(Model model,String mem_id,String currPwd,HttpServletResponse response) throws IOException {

		int re = dao.updateInqPwd(mem_id,currPwd);
		
		System.out.println(re);
		
		if(re == 1) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('성공적으로 비밀번호가 변경되었습니다.'); location.href='/';</script>");
            out.close();
            return null;			
		}
		
		return "";
	}
	
	/*
	@GetMapping("/kakaoLoginOK.do")
	@ResponseBody
	public String kakaoLoginOK(String email) {
		return email + "카카오 로그인 완료";
	}
	*/
}
