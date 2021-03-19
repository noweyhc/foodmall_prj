package com.mall.controller;

import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.dao.mypage.MypageDao;
import com.mall.smswebservice.BanchanSms;
import com.mall.vo.mypage.MypageVo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MypageController {

	private final MypageDao dao;
	private final JavaMailSender javaMailSender;
	
	@GetMapping("/mypage.do")
	public ModelAndView Mypage() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("mypage");
		
		return mav;
	}//Mypage
	
	@GetMapping("/userInfoUpdate.do")
	public ModelAndView updateForm(String admin) {
		ModelAndView mav = new ModelAndView();
		
		admin = "leewooo";
		
		MypageVo mVo = dao.getMemberinfo(admin);
		
		mav.addObject("mVo",mVo);
		mav.setViewName("userInfoUpdate");
		
 		return mav;
	}//ModelAndView
	
	@GetMapping("/sendEmailCode.do")
	@ResponseBody
	public String sendEmailCode(String email) {
		
		System.out.println("email" + email);
		
		Random random = new Random();
		
		int a = random.nextInt(10);
		int b = random.nextInt(10);
		int c = random.nextInt(10);
		int d = random.nextInt(10);
		
		String code = a + "" + b + "" + c + "" + d; 

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setSubject("반찬가게 인증코드 전송");
		mailMessage.setFrom("jewelrye6");
		mailMessage.setText(code);
		mailMessage.setTo(email);
		javaMailSender.send(mailMessage);
		return code;
	}//sendEmailCode
	
	@GetMapping("/updateEmail.do")
	@ResponseBody
	public String EmailUpdate(String email,String admin) {
		
		admin = "leewooo";
		
		int re = dao.updateEmail(email,admin);
		
		if(re == 1) {
			return email;
		}else {
			System.out.println("실패");
		}
		
		return "";

	}//EmailUpdate
	
	@GetMapping("/sendPhoneCode.do")
	@ResponseBody
	public String sendPhoneCode(String phone) {
		
		System.out.println("phone" + phone);
		
		Random random = new Random();
		
		int a = random.nextInt(10);
		int b = random.nextInt(10);
		int c = random.nextInt(10);
		int d = random.nextInt(10);
		
		String text = "더반찬 인증번호\n";
		String code = a + "" + b + "" + c + "" + d; 
		
		BanchanSms sms = new BanchanSms();
		
		System.out.println(code);
		
		sms.sendMsg("01086469577", phone, text+code);
		
		return code;
	}//sendPhoneCode
	
	@GetMapping("/updatePhone.do")
	@ResponseBody
	public String phoneUpdate(String phone,String admin) {
		
		System.out.println("ㅇㅇㅇ" + phone);
		
		admin = "leewooo";
	
		int re = dao.updatePhone(phone,admin);
		
		if(re == 1) {
			System.out.println(re);
			return phone;
		}else {
			System.out.println("실패");
		}
		
		return "";
	}
	
	@GetMapping("/shippingList.do")
	public String moveShipping(Model model) {
		return "shippingList";
	}
	
	@GetMapping("/deleteAccount.do")
	public String deleteAccountForm(Model model) {

		String mem_id = "bakbakbak";
		
		MypageVo mVo = dao.getMemberid(mem_id);
		
		model.addAttribute("mVo",mVo);
		
		return "deleteAccount";
	}
	
	@PostMapping("/deleteAccount.do")
	public String deleteAccountSubmit(Model model) {
		
			return "/";
	}
	
	@GetMapping("/resetPassword.do")
	public String resetPassword(Model model) {
		
		return "resetPassword";
	}
	
	@PostMapping("/resetPassword.do")
	public String resetPassword(Model model,String newPassword) {
		
		return "resetPassword";
	}
	
	@PostMapping("/getPassword.do")
	@ResponseBody
	public String getPassword(@RequestBody String currPassword) {

		String mem_id = "bakbakbak";
		
		System.out.println(currPassword);
		
		String pwd = dao.getPwd(currPassword,mem_id);
		
		return pwd;
	}
	
}//class
