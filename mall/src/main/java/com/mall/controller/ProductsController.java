package com.mall.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mall.dao.product.ProductDao;
import com.mall.dao.sale.SaleDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ProductsController {

	private final ProductDao dao;
<<<<<<< HEAD
=======
	private final SaleDao saleDao;
	
	public static int pageSIZE = 12;	// 한 화면에 보여줄 게시물 수
	public static int totalRecord = 0;	// 총 게시물 수
	public static int totalPage = 1;	// 페이지 번호
>>>>>>> f3057407b6a8236bfe0db09a2c6d16bddb20253a

	public static int pageSIZE = 12; // 한 화면에 보여줄 게시물 수
	public static int totalRecord = 0; // 총 게시물 수
	public static int totalPage = 1; // 페이지 번호
	
	public static int category = 0;

	// 상품 목록 조회
	@RequestMapping("/listProducts.do")
	public ModelAndView listProducts(HttpServletRequest request,

		@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		totalRecord = dao.countPage();
		totalPage = (int) Math.ceil(totalRecord / (double) pageSIZE);

		int start = (pageNum - 1) * pageSIZE + 1;
		int end = start + pageSIZE - 1;

		ModelAndView mav = new ModelAndView();
		mav.addObject("list", dao.pagingProduct(start, end));
		mav.addObject("saleMap", saleDao.selectValidMap());
		mav.addObject("totalPage", totalPage);
		return mav;
	}

	@RequestMapping("/detailProducts.do")
	public ModelAndView detail(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("p", dao.selectOne(no));
		return mav;
	}


	@RequestMapping("/categoryProducts.do") 
	public ModelAndView categoryProducts(int category_no) {
		ModelAndView mav = new ModelAndView(); 	
		mav.setViewName("categoryProducts");
		mav.addObject("list", dao.categoryProduct(category_no)); 
		mav.addObject("category", category);
		
		return mav; 
	}
	

}
