package com.mall.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.dao.mypage.MypageDao;
import com.mall.smswebservice.BanchanSms;
import com.mall.vo.mypage.IdInquiryVo;
import com.mall.vo.mypage.MypageVo;
import com.mall.vo.mypage.ShippingVo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MypageController {

	private final MypageDao dao;
	private final JavaMailSender javaMailSender;

	//페이징 처리 관련 변수
	public static int pageSIZE = 10; // 한 화면에 보여지는 레코드 수
	public static int totalRecord = 0; //전체 레코드 수
	public static int totalPage = 1;// 전체 페이지 수
	
	// 마이페이지 리스트
	@GetMapping("/mypage.do")
	public String Mypage(Model model, HttpSession session, HttpServletRequest request,  HttpServletResponse response ) throws IOException {
		
		session = request.getSession(true);
		String id = (String) session.getAttribute("login");
		
		if(id == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 후 후기 작성이 가능합니다.!'); location.href='/'; </script>");
			out.close();
			return null;	
			
		}
		return "mypage";
	}//Mypage
	
	// 회원의 Email & Phone 정보 변경 뷰 페이지
	@GetMapping("/userInfoUpdate.do")
	public String updateForm(Model model,String admin,HttpSession session,HttpServletRequest request) {
		
		session = request.getSession(true);
		String id = (String) session.getAttribute("login");
		
		// Session 아이디
		admin = "leewooo";
		// 회원 아이디와 이름을 가져온다.
		MypageVo mVo = dao.getMemberinfo(id);
		// 상태 유지 후 뷰 페이지로 이동
		model.addAttribute("mVo",mVo);
 		return "userInfoUpdate";
	}//ModelAndView
	
	// 이메일 인증번호 난수
	@GetMapping("/sendEmailCode.do")
	@ResponseBody
	public String sendEmailCode(String email) {
		// 랜덤함수 객체
		Random random = new Random();
		// 인증을 위한 랜덤 값 추출
		int chkCode = random.nextInt(888888) + 111111;
		String code = String.valueOf(chkCode);

		// 인증 코드 메일 제목
		String subject = "[더 반찬] 이메일 변경 인증 메시지입니다.";
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
		mailMessage.setTo(email); // 보낼 메일
		// 메일 전송
		javaMailSender.send(mailMessage);
		// 리턴 후 ajax 통해 클라이언트가 입력한 값과 code값 비교
		return code;
	}//sendEmailCode
	
	// AJAX을 통한 이메일 인증코드가 맞을 시 해당 메소드로 호출 시 변경
	@GetMapping("/updateEmail.do")
	@ResponseBody
	public String EmailUpdate(String email,String admin) {
		// session
		admin = "leewooo";
		// 변경할 이메일을 가지고 update
		int re = dao.updateEmail(email,admin);
		// 만약 성공적으로 변경이 완료되었다면 뷰페이지에 변경된 이메일 값으로 변경 
		if(re == 1) {
			return email;
		}
		return "";
	}//EmailUpdate
	
	// 이메일 인증번호 난수
	@GetMapping("/sendPhoneCode.do")
	@ResponseBody
	public String sendPhoneCode(String phone) {
		// 랜덤객체 생성
		Random random = new Random();
		// 인증을 위한 랜덤 값 추출
		int chkCode = random.nextInt(888888) + 111111;
		String code = String.valueOf(chkCode);
		// 클라이언트에게 전송할 문자 내용
		String content = "[더 반찬]을 항상 이용해주셔서 항상 감사합니다\n\n"
				+ "인증 번호는[" + code + "]입니다." ;
		// 핸드폰 문자를 보내기 위한 객체
		BanchanSms sms = new BanchanSms();
		// 발신자 / 인증을 요청한 번호 / 문자 내용
		sms.sendMsg("01086469577", phone, content);
		
		return code;
	}//sendPhoneCode
	
	 // 인증번호가 일치 할 시 핸드폰 번호를 변경
	@GetMapping("/updatePhone.do")
	@ResponseBody
	public String phoneUpdate(String phone,String admin) {
		// 세선
		admin = "leewooo";
		// 변경할 핸드폰 번호 dao로 전달
		int re = dao.updatePhone(phone,admin);
		// 핸드폰 번호가 성공적으로 변경이 완료 됐으면
		if(re == 1) {
			//변경된 핸드폰 번호를 뷰단에 변경
			return phone;
		}
		return "";
	}//phoneUpdate
	
	 // 배송지 관리 뷰 페이지로 이동
	@GetMapping("/updateShipping.do")
	public String updateShippingForm(Model model) {
		// 세션
		String mem_id = "leewooo";
		// ShippingVo에 회원에 대한 우편번호/주소/상세주소를 담는다.
		ShippingVo spVo = dao.getShipping(mem_id);
		// 상태유지 후 페이지에 현재주소 value에 값을 뿌려준다.
		model.addAttribute("spVo",spVo);
		
		return "updateShipping";
	}//updateShipping
	
	// 주소 변경 Process
	@PostMapping("/updateShipping.do")
	public String updateShippingSubmit(Model model,ShippingVo spVo,String mem_id,HttpServletResponse response) throws IOException {
		// 세션
		mem_id = "leewooo";
		// 변경할 우편번호/주소/상세주소를 담겨져 있는 spVo객체를 dao로 전달
		int re = dao.updateShipping(spVo,mem_id);
		// 성공적으로 주소가 변경이 되었다면
		if(re == 1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('주소 변경이 성공적으로 변경이 완료되었습니다.'); location.href='/updateShipping.do'; </script>");
			out.close();
		}
		return "";
	}//updateShippingSubmit
	
	// 회원 탈퇴 화면 뷰
	@GetMapping("/deleteAccount.do")
	public String deleteAccountForm(Model model) {
		// 세션
		String mem_id = "admin1234";
		// 고객 아이디를 얻어온다.
		MypageVo mVo = dao.getMemberid(mem_id);
		// 상태 유지 후 고객 아이디 회원 탈퇴 뷰에 뿌려준다.
		model.addAttribute("mVo",mVo);

		return "deleteAccount";
	}// deleteAccountForm
	
	// 회원탈퇴 Process
	@PostMapping("/deleteAccount.do")
	public String deleteAccountSubmit(Model model,HttpServletResponse response) throws IOException {
			// 세션
			String mem_id = "jangilkyu";
			// 아이디에 대한 정보를 찾아서 회원 탈퇴
			int re = dao.deleteId(mem_id);
			// 성공적으로 주소가 변경이 되었다면
			if(re == 1) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				// alert 안내 후 메인(초기)페이지로 이동
				out.println("<script>alert('밥도둑 회원탈퇴가 정상적으로 완료되었습니다.\n 그동안 이용해주셔서 감사합니다.'); location.href='/'; </script>");
				out.close();
			}
			return "mypage";
	}//deleteAccountSubmit
	
	// 비밀번호 변경 뷰 페이지로 이동
	@GetMapping("/resetPassword.do")
	public String resetPassword(Model model) {
		return "resetPassword";
	}//resetPassword
	
	 // 새로운 비밀번호 와 비밀번호 확인이 일치 시 비밀번호 변경
	@PostMapping("/resetPassword.do")
	@ResponseBody
	public String resetSub(Model model,@RequestBody String newChkPassword) {
		//새로운 비밀번호로 변경
		System.out.println(newChkPassword);
		model.addAttribute(dao.updatePwd(newChkPassword));
		System.out.println(newChkPassword);
		return "mypage.do";
	}//resetSub
	
	// 현재 비밀번호가 맞는지 확인하는 메소드
	@PostMapping("/getPassword.do")
	@ResponseBody
	public int getPassword(@RequestBody String currPassword) {
		// 세션
		String mem_id = "leewooo";
		System.out.println(currPassword);
		// 아이디와 현재 비밀번호가 일치하는지 확인
		int pwd = dao.getPwd(currPassword,mem_id);
		System.out.println(pwd);
		return pwd;
	}//getPassword
	
	
	// 아이디 찾기 뷰페이지
	@GetMapping("/idInquiry.do")
	public String idInquiry(Model model) {

		return "idInquiry";
	}
	
	// 아이디 찾기 - 회원 정보에 등록한 휴대전화로 인증
	@PostMapping("/phoneAuth.do")
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
			String content = "[더 반찬]을 항상 이용해주셔서 항상 감사합니다\n\n"
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
	
	@PostMapping("/viewIdList.do")
	public String viewIdList(Model model,String mem_id) {
		
		model.addAttribute("mem_id", mem_id);
		return "viewIdList";
	}//viewIdList
	
	
	@PostMapping("/emailAuth.do")
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

		System.out.println(cnt);
		// 만약 이메일이 존재하면
		if(cnt == 1) {
			System.out.println(cnt);
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
		}
		return null;
	}
	
/*
 //*  문의사항 등록 후 List반영이 안되는 이슈때문에 inquiry.do 문의 등록 후 list 담아서 myInquiry로 이동하게 변경
	@GetMapping("/myInquiry.do")
	public String getmyInquiry(@RequestParam(value = "pageNUM", defaultValue = "1") int pageNUM ,Model model) {
		
		String mem_id = "leewooo";
		
		totalRecord = dao.totBoard(mem_id);
		
		totalPage = (int) Math.ceil(totalRecord / (double) pageSIZE);

		int start = (pageNUM-1) * pageSIZE+1;
		int end = start + pageSIZE-1;
		
		List<MyInqListVo> list = dao.findMyInq(mem_id,start,end);
		
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("list",list);
		
		return "myInquiry";
	}//getmyInquiry
*/
}//class
