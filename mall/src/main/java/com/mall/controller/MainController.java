package com.mall.controller;

import java.util.List;

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
	
	private final ProductDao dao;

	@RequestMapping("/")
	public ModelAndView mainpage() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("newProducts", dao.selectNew(8));
		mav.setViewName("main");
		return mav;
	}
	
}
