package com.mall.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mall.dao.inquiry.InquiryDao;
import com.mall.interceptor.LoginSessionListener;
import com.mall.util.FileNameUtil;
import com.mall.vo.answer.answerVo;
import com.mall.vo.inquiry.InquiryEmailPhoneVo;
import com.mall.vo.inquiry.InquiryVo;
import com.mall.vo.mypage.MyInqDetRespVo;
import com.mall.vo.mypage.MyInqDetailVo;
import com.mall.vo.mypage.MyInqListVo;
import com.mall.vo.user.UserVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("inquiry")
public class InquiryController {

	private final InquiryDao dao;
	private final FileNameUtil FileNameUtil;
	private final JavaMailSender javaMailSender;
	private final LoginSessionListener lsl;
	
	//페이징 처리 관련 변수
	public static int pageSIZE = 10; // 한 화면에 보여지는 레코드 수
	public static int totalRecord = 0; //전체 레코드 수
	public static int totalPage = 1;// 전체 페이지 수
	
	// 1:1문의 view페이지
	@GetMapping("/inquiry")
	public String inquiryForm(Model model,HttpSession session, HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String mem_id = (String) session.getAttribute("login");
		
		
		if(mem_id == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('1:1 문의 작성은 로그인 후 후기 작성이 가능합니다.!'); location.href='/'; </script>");
			out.close();
		}
		
		// view화면에 보여줄 고객 정보 [고객명,아이디]를 담는다.
		UserVO uv = dao.getMemberInfo(mem_id);
		// 상태 유지 후 inquiry로 페이지 이동
		session.setAttribute("login", mem_id);
		
		model.addAttribute("uv",uv);
		return "inquiry/inquiry";
	}
	
	// 고객이 1:1문의를 등록하는 페이지
	@PostMapping("/inquiry")
	public String inquirySubmit( Model model,InquiryVo ivo,String cs_email, String cs_phone,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String mem_id = (String) session.getAttribute("login");
		
		// 이미지를 저장할 경로
		String path = request.getSession().getServletContext().getRealPath("inquiry");
		// 
		MultipartFile uploadFile = ivo.getUploadFile();
		
		ivo.setCs_fname("");
		
		String fname = uploadFile.getOriginalFilename();
		
		if(fname != null && !"".equals(fname)){
			// 파일 복사 
			try {
				// 파일 내용 가져오기
				byte []data = uploadFile.getBytes();
				// 파일 이름이 중복이 되면 MallimgUtil을 통해서 뒤에 시간을 붙여준다.
				fname = FileNameUtil.FileRename(path, fname);
				
				FileOutputStream fos = new FileOutputStream(path + "/" + fname);
				fos.write(data);
				fos.close();
				ivo.setCs_fname(fname);
			} catch (IOException e) {
				e.printStackTrace();
			}//end catch
			
			int re = dao.insertInquiry(ivo,cs_email,cs_phone,mem_id);
			
			if(re == 1) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('고객님께서 문의해주신 소중한 의견이 전달되었습니다.'); location.href='/'; </script>");
				out.close();
			}//end if
		
		}//end if
		
		return "";
	}//inquirySubmit
	
	// 고객이 작성한 1:1문의를 확인하는 페이지
	@GetMapping("/myInquiry")
	public String getmyInquiry(@RequestParam(value = "pageNUM", defaultValue = "1") int pageNUM ,Model model,HttpSession session,HttpServletRequest request) {
		
		String mem_id = (String) session.getAttribute("login");
		
		totalRecord = dao.totBoard(mem_id);
		
		totalPage = (int) Math.ceil(totalRecord / (double) pageSIZE);

		int start = (pageNUM-1) * pageSIZE+1;
		int end = start + pageSIZE-1;
		
		List<MyInqListVo> list = dao.findMyInq(mem_id,start,end);
		
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("list",list);
		
		return "inquiry/myInquiry";
	}//getmyInquiry
	
	// 관리자 페이지에서 클라이언트가 문의 남긴 문의 리스트 페이지
	@GetMapping("/inquiryList")
	public String inquiryList(Model model, @RequestParam(value="pageNUM",defaultValue = "1") int pageNUM,@RequestParam(value="keyword",defaultValue = "")  String keyword,@RequestParam(value="searchFeild",defaultValue = "cs_title")  String searchFeild) {
	System.out.println(pageNUM);
	System.out.println(keyword);
	System.out.println(searchFeild);
	int cntTbCs = dao.cntTbCs(keyword,searchFeild);
		
	// 총 게시글 수
	totalRecord = cntTbCs;
	// 페이지 수 구하기
	totalPage = (int)Math.ceil(totalRecord / (double)pageSIZE);
	// 한화면에 보여질 글 갯수
	int start = (pageNUM-1)*pageSIZE+1;
	int end = start+pageSIZE-1;
	// 한페이지에 보여질 글 갯수 정보를 담고있는 start와 end를 인자로 가지로 조회 후 List에 담는다.
	List<InquiryVo> list= null;
	
	list = dao.findAllInquiry(start,end,keyword,searchFeild);

	if(list == null) {
		model.addAttribute("list",list);
		return "/inquiry/inquiryList";
	}
	
	// 페이지 수 상태유지
	model.addAttribute("totalPage",totalPage);
	// 고객들의 문의 리스트 상태유지
	model.addAttribute("list",list);
	
		return "inquiry/inquiryList";
	}//inquiryList
	
	//관리자 페이지에 사용자가 문의한 내용 디테일 페이지
	@GetMapping("/inquiryDetail.do")
	public String inquiryDetail(Model model,int cs_no) {
		InquiryVo userIqVo = dao.selectInquiry(cs_no);
		
		model.addAttribute("userIqVo",userIqVo);
		
		return "inquiryDetail";
	}//inquiryDetail
	
	// 고객 답변 메일로 전송하는 메소드
	@PostMapping("/sendQnA.do")
	@ResponseBody
	public int sendQnA(Model model,answerVo answerVo, int cs_no,String cs_mem_id) {
		
		InquiryEmailPhoneVo iep = dao.getMemberEmail(cs_no);

		System.out.println(cs_mem_id+"abc");
		int re = dao.insertAnswer(answerVo,cs_no,cs_mem_id);
		int respChk = 0;
		//만약 정상적으로 답변이 작성이 되었다면
		if(re == 1) {
			// 미답변 -> 답변으로 바꾼다.
			// 0 -> 1
			respChk = dao.ansOK(cs_no);
		}
		
		// 고객에게 답변 완료 안내멘트 제목/내용
		String subject = "[밥도둑] 문의번호 ["+ cs_no  +"] 1:1 문의 답변입니다.";
		String content = "안녕하세요." + cs_mem_id +"님\n" + "고객님 께서 문의해주신 소중한 문의 내용 답변이 완료 되었습니다.\n [마이페이지] -> [내가 문의한 목록]에서 확인이 가능하십니다.\n 항상 이용해주셔서 감사합니다.[밥도둑]";
		
		// 메일 보내기
		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setSubject(subject);
		mailMessage.setFrom("jewelrye6");
		mailMessage.setText(content);
		mailMessage.setTo(iep.getCs_email());
		javaMailSender.send(mailMessage);
		
		return respChk;
	}
	
	// 미답변 -> 답변으로 바꾸기 위한 
	public String ansOK(Model model,int data) {
		
		System.out.println(data);
		return "";
	}
	
	// 마이페이지 고객 문의 내용 DetailPage
	@GetMapping("myInqDetail/{cs_no}")
	public String myInqDetail(@PathVariable int cs_no,Model model) {
		
		MyInqDetailVo detailVo = dao.findDetailInq(cs_no);
		
		// 관리자가 답변한 내용 담는 Vo
		MyInqDetRespVo midrVo = dao.findInqResp(cs_no);
		
		model.addAttribute("detailVo",detailVo);
		model.addAttribute("midrVo",midrVo);
		return "/inquiry/myInqDetail";
	}
}
