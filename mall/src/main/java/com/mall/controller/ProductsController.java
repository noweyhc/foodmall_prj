package com.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mall.dao.product.ProductDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ProductsController {
	
	private final ProductDao dao;

	@RequestMapping("/detailProducts.do")
	public ModelAndView detail(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("p", dao.selectOne(no));
		return mav;
		}
	
	@RequestMapping("/listProducts.do")
	public ModelAndView listProducts() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("listProducts");
		mav.addObject("list", dao.findAll());
		return mav;
	}
}
