package com.mall.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.dao.product.ProductDao;
import com.mall.dao.sale.SaleDao;
import com.mall.util.AdminUtil;
import com.mall.vo.product.ProductVo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final ProductDao productDao;

	@RequestMapping("/")
	public ModelAndView mainpage() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("newProducts", productDao.selectNew(8));
		mav.setViewName("main");
		return mav;
	}
	
	//상품명 검색 결과를 반환하고 검색 결과 페이지로 넘김
	@RequestMapping("/search")
	public ModelAndView searchPage(HttpServletRequest request) {
		String keyword = request.getParameter("keyword");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("keyword", keyword);
		
		//검색 결과가 비어있을 시 안내 문구를 보이기 위해 결과값 추가
		List<ProductVo> resultList = productDao.searchByName(keyword);
		if(resultList.isEmpty()) {
			mav.addObject("notFound", "true");
		}else {
			mav.addObject(resultList);
		}
		
		mav.setViewName("searchResult");
		return mav;
	}
	
}
