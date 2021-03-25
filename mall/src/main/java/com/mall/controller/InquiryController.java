package com.mall.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mall.dao.inquiry.InquiryDao;
import com.mall.util.FileNameUtil;
import com.mall.vo.inquiry.InquiryVo;
import com.mall.vo.user.UserVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class InquiryController {

	private final InquiryDao dao;
	private final FileNameUtil FileNameUtil;
	
	//페이징 처리 관련 변수
	public static int pageSIZE = 10; // 한 화면에 보여지는 레코드 수
	public static int totalRecord = 0; //전체 레코드 수
	public static int totalPage = 1;// 전체 페이지 수
	
	@GetMapping("/inquiry.do")
	public String inquiryForm(Model model) {
		
		String id = "leewooo";
		
		UserVO uv = dao.getMemberInfo(id);

		model.addAttribute("uv",uv);
		
		return "inquiry";
	}
	
	@PostMapping("/inquiry.do")
	public String inquirySubmit(Model model,InquiryVo ivo,String cs_email, String cs_phone,HttpServletRequest request) {
		
		String path = request.getSession().getServletContext().getRealPath("inquiry");
		
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
			
			System.out.println(ivo.getCs_fname());
			
			int re = dao.insertInquiry(ivo,cs_email,cs_phone);
			
		}//end if
		
		return "";
	}//inquirySubmit
	
	@GetMapping("/inquiryList.do")
	public String inquiryList(Model model, @RequestParam(value="pageNUM",defaultValue = "1") int pageNUM) {
		
	
	// 총 게시글 수
	totalRecord = dao.totBoard();
	
	totalPage = (int)Math.ceil(totalRecord / (double)pageSIZE);
	
	System.out.println(pageNUM);
	
	// 한화면에 보여질 글 갯수
	int start = (pageNUM-1)*pageSIZE+1;
	int end = start+pageSIZE-1;
	
	List<InquiryVo> list=dao.findAllInquiry(start,end);
	
	model.addAttribute("totalPage",totalPage);
	model.addAttribute("list",list);
	
		return "inquiryList";
	}//inquiryList
	
	/**
	 * 관리자 페이지에 사용자가 문의한 내용 디테일 페이지
	 * @param model
	 * @param cs_no
	 * @return
	 */
	@GetMapping("/inquiryDetail.do")
	public String inquiryDetail(Model model,int cs_no) {
		InquiryVo userIqVo = dao.selectInquiry(cs_no);
		
		model.addAttribute("userIqVo",userIqVo);
		
		return "inquiryDetail";
	}//inquiryDetail
	
	@GetMapping("inquiryAnswer.do")
	public String inquiryAnswer(Model model,int cs_no) {
		
		
		model.addAttribute("cs_no",cs_no);
		
		return "inquiryAnswer";
	}
	
}
