package com.mall.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView listFAQ(HttpServletRequest request, @RequestParam(value = "pageNUM", defaultValue = "1") int pageNUM) {
		totalRecord = dao.totBoard(); // 총 게시글 수
		totalPage = (int) Math.ceil(totalRecord / (double) pageSIZE);

		System.out.println("pageNUM:" + pageNUM);

		int start = (pageNUM - 1) * pageSIZE + 1;
		int end = start + pageSIZE - 1;

		System.out.println("시작레코드:" + start);
		System.out.println("끝나는레코드:" + end);
		System.out.println("-------------------------------");

		ModelAndView mav = new ModelAndView();
		mav.addObject("list", dao.listFAQ(start, end));
		mav.addObject("totalPage", totalPage);
		return mav;
	}

	// 글 쓰기
	@RequestMapping(value = "/insertFAQ.do", method = RequestMethod.GET)
	public void insertFAQForm() {
	}

	// 글 쓰기 완료
	@RequestMapping(value = "/insertFAQ.do", method = RequestMethod.POST)
	public ModelAndView insertFAQSubmit(FaqVo f) {
		ModelAndView mav = new ModelAndView();
		int re = dao.insertFAQ(f);
		if (re == 1) {
			mav.setViewName("redirect:/listFAQ.do");
			System.out.println("등록 성공");
		} else {
			mav.addObject("msg", "게시물 등록에 실패하였습니다.");
			mav.setViewName("error");
		}
		return mav;
	}

	// 글 상세 조회
	@RequestMapping("/detailFAQ.do")
	public ModelAndView detailFAQ(int no) {
		//조회수 증가
		dao.updateHit(no);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("f", dao.detailFAQ(no));
		
		return mav;
	}

	// 글 수정
	@RequestMapping(value = "/updateFAQ.do", method = RequestMethod.GET)
	public ModelAndView updateForm(HttpServletRequest request, int no) {

		
		ModelAndView mav = new ModelAndView();
		mav.addObject("f", dao.detailFAQ(no));
		return mav;
	}

	// 글 수정완료
	@RequestMapping(value = "/updateFAQ.do", method = RequestMethod.POST)
	public ModelAndView updateSubmit(FaqVo f) {
		ModelAndView mav = new ModelAndView();
		int re = dao.updateFAQ(f);
		if (re == 1) {
			mav.setViewName("redirect:/listFAQ.do");
		} else {
			mav.addObject("msg", "삭제에 실패하였습니다.");
			mav.setViewName("error");
		}
		return mav;
	}
	
	// 글 삭제
	@RequestMapping(value = "/deleteFAQ.do", method = RequestMethod.GET)
	public ModelAndView deleteForm(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("no", no);
		return mav;
	}

	// 글 삭제 완료
	@RequestMapping(value = "/deleteFAQ.do", method = RequestMethod.POST)
	public ModelAndView deleteSubmit(int no) {
		ModelAndView mav = new ModelAndView();
		int re = dao.deleteFAQ(no);
		if (re == 1) {
			mav.setViewName("redirect:/listFAQ.do");
		} else {
			mav.addObject("msg", "삭제에 실패하였습니다.");
			mav.setViewName("error");
		}
		return mav;
	}


}
