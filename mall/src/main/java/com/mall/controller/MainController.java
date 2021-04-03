package com.mall.controller;

import java.util.ArrayList;
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
	private final SaleDao saleDao;

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
		mav.addObject("resultCount", resultList.size());
		if(resultList.isEmpty()) {
			mav.addObject("notFound", "true");
		}else {
			mav.addObject("resultList", resultList);
		}
		
		mav.setViewName("searchResult");
		return mav;
	}
	
	//카테고리 검색 결과를 반환하고 목록 페이지로 넘김
	@RequestMapping("/category")
	public ModelAndView searchCategory(HttpServletRequest request) {
		String[] keywords = request.getParameterValues("key");
		ModelAndView mav = new ModelAndView();
		String title = "";
		
		//각 키워드에 해당하는 상품 목록을 받아서 추가
		List<ProductVo> categoryList = new ArrayList<ProductVo>();
		for(String key : keywords) {
			title += ("/" + key);
			categoryList.addAll(productDao.selectCategory(key));
		}
		
		//상품 목록에서 중복 제거
		List<ProductVo> list = new ArrayList<ProductVo>();
		for(ProductVo pv : categoryList) {
			if(!list.contains(pv)) {
				list.add(pv);
			}
		}
		
		mav.addObject("list", list);
		mav.addObject("title", title.substring(1));
		mav.addObject("saleMap", saleDao.selectValidMap());
		mav.setViewName("listProducts");
		return mav;
	}
	
}
