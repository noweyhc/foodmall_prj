package com.mall.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mall.dao.notice.NoticeDao;
import com.mall.vo.notice.NoticeVo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class NoticeController {

	public static int pageSIZE = 10;
	public static int totalRecord = 0;
	public static int totalPage = 1;

	private final NoticeDao dao;

	// 목록 조회+페이징
	@RequestMapping("/listNotice.do")
	public ModelAndView listNotice(HttpServletRequest request,
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
		mav.addObject("list", dao.listNotice(start, end));
		mav.addObject("totalPage", totalPage);
		return mav;
	}

	// 글 쓰기
	@RequestMapping(value = "/insertNotice.do", method = RequestMethod.GET)
	public void insertNoticeForm() {
	}

	// 글 쓰기 완료
	@RequestMapping(value = "/insertNotice.do", method = RequestMethod.POST)
	public ModelAndView insertNoticeSubmit(NoticeVo n) {
		ModelAndView mav = new ModelAndView();
		int re = dao.insertNotice(n);
		if (re == 1) {
			mav.setViewName("redirect:/listNotice.do");
			System.out.println("등록 성공");
		} else {
			mav.addObject("msg", "게시물 등록에 실패하였습니다.");
			mav.setViewName("error");
		}
		return mav;
	}

	// 글 상세 조회
	@RequestMapping("/detailNotice.do")
	public ModelAndView detailNotice(HttpServletRequest request, int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("n", dao.detailNotice(no));
		return mav;
	}

	// 글 수정
	@RequestMapping(value = "/updateNotice.do", method = RequestMethod.GET)
	public ModelAndView updateNoticeForm(HttpServletRequest request, int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("n", dao.detailNotice(no));
		return mav;
	}

	// 글 수정완료
	@RequestMapping(value = "/updateNotice.do", method = RequestMethod.POST)
	public ModelAndView updateNoticeSubmit(NoticeVo n) {
		ModelAndView mav = new ModelAndView();
		int re = dao.updateNotice(n);
		if (re == 1) {
			mav.setViewName("redirect:/listNotice.do");
		} else {
			mav.addObject("msg", "삭제에 실패하였습니다.");
			mav.setViewName("error");
		}
		return mav;
	}
	
	// 글 삭제
	@RequestMapping(value = "/deleteNotice.do", method = RequestMethod.GET)
	public ModelAndView deleteNoticeForm(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("no", no);
		return mav;
	}

	// 글 삭제 완료
	@RequestMapping(value = "/deleteNotice.do", method = RequestMethod.POST)
	public ModelAndView deleteSubmit(int no) {
		ModelAndView mav = new ModelAndView();
		int re = dao.deleteNotice(no);
		if (re == 1) {
			mav.setViewName("redirect:/listNotice.do");
		} else {
			mav.addObject("msg", "삭제에 실패하였습니다.");
			mav.setViewName("error");
		}
		return mav;
	}


}
