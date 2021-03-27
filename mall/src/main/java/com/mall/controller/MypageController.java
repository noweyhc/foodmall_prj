package com.mall.controller;

import java.util.List;
import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.dao.mypage.MypageDao;
import com.mall.smswebservice.BanchanSms;
import com.mall.vo.mypage.MyInqDetailVo;
import com.mall.vo.mypage.MyInqListVo;
import com.mall.vo.mypage.MypageVo;
import com.mall.vo.mypage.ShippingVo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MypageController {

	private final MypageDao dao;
	private final JavaMailSender javaMailSender;
	
	/**
	 * 마이페이지 리스트
	 * @return
	 */
	@GetMapping("/mypage.do")
	public ModelAndView Mypage() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("mypage");
		
		return mav;
	}//Mypage
	
	/**
	 * 회원의 Email & Phone 변경
	 * @param admin
	 * @return
	 */
	@GetMapping("/userInfoUpdate.do")
	public ModelAndView updateForm(String admin) {
		ModelAndView mav = new ModelAndView();
		
		admin = "leewooo";
		
		MypageVo mVo = dao.getMemberinfo(admin);
		
		mav.addObject("mVo",mVo);
		mav.setViewName("userInfoUpdate");
		
 		return mav;
	}//ModelAndView
	
	/**
	 * 이메일 인증번호 난수
	 * @param email
	 * @return
	 */
	@GetMapping("/sendEmailCode.do")
	@ResponseBody
	public String sendEmailCode(String email) {
		
		// 인증을 위한 랜덤 값 추출
		Random random = new Random();
		int chkCode = random.nextInt(888888) + 111111;
		String code = String.valueOf(chkCode);
		
		// 인증 메일 내용
		String subject = "[더 반찬] 이메일 변경 인증 메시지입니다.";
		String content = "더 반찬을 항상 이용해주셔서 항상 감사합니다\n\n"
						+ "인증 번호는[" + code + "]입니다.\n" 
						+ "해당 인증번호를 인증번호 확인란에 기입해주시면 감사하겠습니다.\n"
						+ "오늘도 즐거운 쇼핑되세요.";
		
		// 메일 보내기
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setSubject(subject);
		mailMessage.setFrom("jewelrye6");
		mailMessage.setText(content);
		mailMessage.setTo(email);
		javaMailSender.send(mailMessage);
		return code;
	}//sendEmailCode
	
	/**
	 * AJAX을 통한 인증코드가 맞을 시 해당 메소드로 호출 시 변경
	 * @param email
	 * @param admin
	 * @return 변경된 이메일 리턴 후 뷰단에 변경
	 */
	@GetMapping("/updateEmail.do")
	@ResponseBody
	public String EmailUpdate(String email,String admin) {
		
		admin = "leewooo";
		
		int re = dao.updateEmail(email,admin);
		System.out.println(re);
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
		
		// 인증을 위한 랜덤 값 추출
		Random random = new Random();
		int chkCode = random.nextInt(888888) + 111111;
		String code = String.valueOf(chkCode);

		// 문자 내용
		String content = "[더 반찬]을 항상 이용해주셔서 항상 감사합니다\n\n"
				+ "인증 번호는[" + code + "]입니다." ;
		
		// 핸드폰 문자를 보내기 위한 객체
		BanchanSms sms = new BanchanSms();
		
		sms.sendMsg("01086469577", phone, content);
		
		return code;
	}//sendPhoneCode
	
	/**
	 * 인증번호가 일치 할 시 핸드폰 번호를 변경
	 * @param phone
	 * @param admin
	 * @return 변경된 핸드폰 번호를 뷰단에 변경
	 */
	@GetMapping("/updatePhone.do")
	@ResponseBody
	public String phoneUpdate(String phone,String admin) {
		
		admin = "leewooo";
	
		int re = dao.updatePhone(phone,admin);
		
		if(re == 1) {
			System.out.println(re);
			return phone;
		}else {
			System.out.println("실패");
		}
		
		return "mypage.do";
	}
	
	/**
	 * 배송지 관리 페이지로 이동
	 * @param model
	 * @return 배송지관리 페이지
	 */
	@GetMapping("/updateShipping.do")
	public String updateShippingForm(Model model) {
		
		String mem_id = "leewooo";
		
		ShippingVo spVo = dao.getShipping(mem_id);
		
		model.addAttribute("spVo",spVo);
		
		return "updateShipping";
	}//updateShipping
	
	@PostMapping("/updateShipping.do")
	public String updateShippingSubmit(Model model) {
		
		
		return "";
	}
	
	
	/**
	 * 회원 탈퇴 화면 뷰
	 * @param model
	 * @return deleteAccount.do
	 */
	@GetMapping("/deleteAccount.do")
	public String deleteAccountForm(Model model) {

		String mem_id = "test1";
		
		MypageVo mVo = dao.getMemberid(mem_id);
		
		model.addAttribute("mVo",mVo);
		
		return "deleteAccount";
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@PostMapping("/deleteAccount.do")
	public String deleteAccountSubmit(Model model) {
		
			String mem_id = "test1";
		
			int re = dao.deleteId(mem_id);
			
			System.out.println(re);
		
			return "mypage";
	}
	
	@GetMapping("/resetPassword.do")
	public String resetPassword(Model model) {
		
		return "resetPassword";
	}//resetPassword
	
	/**
	 * 새로운 비밀번호 와 비밀번호 확인이 일치 시 비밀번호 변경
	 * @param model
	 * @param newPassword
	 * @return
	 */
	@PostMapping("/resetPassword.do")
	@ResponseBody
	public String resetSub(Model model, String newPassword) {
		
		System.out.println("a1" + newPassword);
		
		model.addAttribute(dao.updatePwd(newPassword));
		
		return "mypage.do";
	}
	
	/**
	 * 현재 비밀번호가 맞는지 확인하는 메소드
	 * @param currPassword
	 * @return
	 */
	@PostMapping("/getPassword.do")
	@ResponseBody
	public String getPassword(String currPassword) {

		String mem_id = "leewooo";
		
		String pwd = dao.getPwd(currPassword,mem_id);
		
		return pwd;
	}//getPassword
	
	@GetMapping("/myInquiry.do")
	public String getmyInquiry(Model model) {
		
		String mem_id = "leewooo";
		
		List<MyInqListVo> list = dao.findMyInq(mem_id);
		
		model.addAttribute("list",list);
		
		return "myInquiry";
	}//getmyInquiry

	@GetMapping("myInqDetail.do/{cs_no}")
	public String myInqDetail(@PathVariable int cs_no,Model model) {
		
		MyInqDetailVo detailVo = dao.findDetailInq(cs_no);
		
		model.addAttribute("detailVo",detailVo);
		
		
		return "myInqDetail";
	}
	
}//class
