package com.mall.controller;

import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mall.SistUtil;
import com.mall.dao.faq.FaqDao;
import com.mall.vo.faq.FaqVo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class FaqController {

	public static int pageSIZE = 10;
	public static int totalRecord = 0;
	public static int totalPage = 1;

	private final FaqDao dao;

	// 목록 조회+페이징
	@RequestMapping("/listFAQ.do")
	public ModelAndView listBoard(HttpServletRequest request,
			@RequestParam(value = "pageNUM", defaultValue = "1") int pageNUM) {
		totalRecord = dao.totBoard();
		totalPage = (int) Math.ceil(totalRecord / (double) pageSIZE);

		System.out.println("pageNUM:" + pageNUM);

		// 우리는 한화면에 10개씩 보여주고 싶어요 ==> pageSIZE
		// 만약 현재페이지가 1페이지라면 보여줘야 할 시작레코드는 1
		// 만약 현재페이지가 2페이지라면 보여줘야 할 시작레코드는 11

		int start = (pageNUM - 1) * pageSIZE + 1;
		int end = start + pageSIZE - 1;

		System.out.println("시작레코드:" + start);
		System.out.println("끝나는레코드:" + end);
		System.out.println("-------------------------------");

		ModelAndView mav = new ModelAndView();
		mav.addObject("list", dao.listBoard(start, end));
		mav.addObject("totalPage", totalPage);
		return mav;
	}

	/*
	 * // 글 작성
	 * 
	 * @RequestMapping(value = "/insertFAQ.do", method = RequestMethod.GET) public
	 * ModelAndView insertForm(HttpServletRequest request, @RequestParam(value =
	 * "no", defaultValue = "0") int no) { ModelAndView mav = new ModelAndView();
	 * String path = request.getRealPath("/img");
	 * 
	 * int re = dao.registerProduct(pv);
	 * 
	 * if(re == 1) { mav.addObject("result", "등록 성공"); }else {
	 * mav.addObject("result", "등록 실패"); } return mav; }
	 */
	@RequestMapping(value = "/insertFAQ.do", method = RequestMethod.GET)
	public void insertForm() {
	}

	@RequestMapping(value = "/insertFAQ.do", method = RequestMethod.POST)
	public ModelAndView insertSubmit(FaqVo f, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String path = request.getRealPath("/file");
		System.out.println("path:" + path);
		MultipartFile uploadFile = f.getUploadFile();
		String fname = uploadFile.getOriginalFilename();
		// f.setFname("");
		// f.setFsize(0);
		if (fname != null && !fname.equals("")) {
			try {
				byte[] data = uploadFile.getBytes();
				fname = SistUtil.getFileName(fname, path);
				FileOutputStream fos = new FileOutputStream(path + "/" + fname);
				fos.write(data);
				fos.close();
				// f.setFname(fname);
				// f.setFsize(data.length);
			} catch (Exception e) {
				System.out.println("예외발생:" + e.getMessage());
			}
		}

		int re = dao.insertBoard(f);
		if (re == 1) {
			mav.setViewName("redirect:/listFAQ.do");
		} else {
			mav.addObject("msg", "게시물 등록에 실패하였습니다.");
			mav.setViewName("error");
		}
		return mav;
	}

	// 상세 조회
	@RequestMapping("/detailFAQ.do")
	public ModelAndView detailBoard(HttpServletRequest request, int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("f", dao.getBoard(no));
		return mav;
	}

	/*
	 * // 글 쓰기
	 * 
	 * @RequestMapping(value = "/insertFAQ.do", method = RequestMethod.GET) public
	 * ModelAndView insertForm(HttpServletRequest request, @RequestParam(value =
	 * "no", defaultValue = "0") int no) { String title = "새글작성"; if (no != 0) {
	 * title = "답글작성"; }
	 * 
	 * ModelAndView mav = new ModelAndView(); mav.addObject("no", no);
	 * mav.addObject("title", title); return mav; }
	 */

	/*
	 * //수정 양식
	 * 
	 * @RequestMapping(value = "/updateFAQ.do",method = RequestMethod.GET) public
	 * ModelAndView updateForm(HttpServletRequest request, int no) { ModelAndView
	 * mav = new ModelAndView(); mav.addObject("b", dao.getBoard(no)); return mav; }
	 * 
	 * //수정완료(비밀번호 필요)
	 * 
	 * @RequestMapping(value = "/updateFAQ.do",method = RequestMethod.POST) public
	 * ModelAndView updateSubmit(HttpServletRequest request, FAQVo f) {
	 * 
	 * String path = request.getRealPath("/file"); String oldFname = f.getFname();
	 * ModelAndView mav = new ModelAndView(); MultipartFile uploadFile =
	 * f.getUploadFile(); String fname = uploadFile.getOriginalFilename(); if(fname
	 * != null && !fname.equals("")) { try { fname = SistUtil.getFileName(fname,
	 * path);
	 * 
	 * byte []data = uploadFile.getBytes(); FileOutputStream fos = new
	 * FileOutputStream(path + "/" +fname); fos.write(data);; fos.close();
	 * 
	 * f.setFname(fname); f.setFsize(data.length);
	 * 
	 * }catch (Exception e) { System.out.println("예외발생:"+e.getMessage()); }
	 * 
	 * }
	 * 
	 * int re = dao.updateBoard(b); if(re == 1) { if(fname != null &&
	 * !fname.equals("") && oldFname != null && !oldFname.equals("")) { File file =
	 * new File(path + "/" + oldFname); file.delete(); }
	 * mav.setViewName("redirect:/listBoard.do"); }else { mav.addObject("msg",
	 * "게시물 수정에 실패하였습니다."); mav.setViewName("error"); }
	 * 
	 * return mav; }
	 * 
	 * //삭제 양식
	 * 
	 * @RequestMapping(value = "/deleteFAQ.do", method = RequestMethod.GET) public
	 * ModelAndView deleteForm(HttpServletRequest request, int no) { ModelAndView
	 * mav = new ModelAndView(); mav.addObject("no",no); return mav; }
	 * 
	 * //삭제 완료(비밀번호 필요)
	 * 
	 * @RequestMapping(value = "/deleteFAQ.do", method = RequestMethod.POST) public
	 * ModelAndView deleteSubmit(HttpServletRequest request,int no, String pwd) {
	 * String path = request.getRealPath("/file"); String fname =
	 * dao.getBoard(no).getFname();
	 * 
	 * ModelAndView mav = new ModelAndView(); int re = dao.deleteBoard(no, pwd);
	 * if(re == 1) { if(fname != null && !fname.equals("")) { File file = new
	 * File(path + "/" + fname); file.delete(); }
	 * 
	 * mav.setViewName("redirect:/listFAQ.do"); }else { mav.addObject("msg",
	 * "삭제에 실패하였습니다."); mav.setViewName("error"); } return mav; }
	 * 
	 * 
	 */

}
