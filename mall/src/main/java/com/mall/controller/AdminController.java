package com.mall.controller;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mall.dao.product.ProductDao;
import com.mall.vo.product.ProductVo;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	
	private final ProductDao dao;

	@RequestMapping("")
	public String adminMainpage() {
		return "/admin/adminMain";
	}
	
	@RequestMapping(value = "/product-register", method = RequestMethod.GET)
	public String registerProductForm() {
		return "/admin/productRegister";
	}

	@RequestMapping(value = "/product-register", method = RequestMethod.POST)
	public ModelAndView registerProduct(ProductVo pv, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String path = request.getRealPath("/img");
		
		MultipartFile uploadImg = pv.getMainImgFile();
		MultipartFile uploadDetailImg1 = pv.getDetailImgFile1();
		MultipartFile uploadDetailImg2 = pv.getDetailImgFile2();
	
		String mainImgName = "";
		String detailImgName1 = "";
		String detailImgName2 = "";
		
		try {
			mainImgName =("product" + String.valueOf(pv.getProduct_no()) + uploadImg.getOriginalFilename().substring(uploadImg.getOriginalFilename().indexOf(".")));
			detailImgName1 =("product" + String.valueOf(pv.getProduct_no()) + "_detail1" + uploadDetailImg1.getOriginalFilename().substring(uploadDetailImg1.getOriginalFilename().indexOf(".")));
			if(uploadDetailImg2.getSize() != 0) {
				detailImgName2 =("product" + String.valueOf(pv.getProduct_no()) + "_detail2" + uploadDetailImg2.getOriginalFilename().substring(uploadDetailImg2.getOriginalFilename().indexOf(".")));							
			}
		}catch(Exception e){
			e.printStackTrace();
			mav.addObject("result", "필수값이 없습니다");	
			mav.setViewName("productRegister");
			return mav;
		}
		
		pv.setProduct_main_img(mainImgName);
		pv.setProduct_detail_img1(detailImgName1);
		pv.setProduct_detail_img2(detailImgName2);

		try {
			byte []data = uploadImg.getBytes();
			FileOutputStream fos = new FileOutputStream(path + "/" + mainImgName);
			fos.write(data);
			fos.flush();
			data = uploadDetailImg1.getBytes();
			fos = new FileOutputStream(path + "/" + detailImgName1);
			fos.write(data);
			fos.flush();
			
			if(uploadDetailImg2.getSize() != 0) {
				data = uploadDetailImg2.getBytes();
				fos = new FileOutputStream(path + "/" + detailImgName2);
				fos.write(data);
				fos.flush();
			}
			
			fos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int re = dao.registerProduct(pv);
		
		if(re == 1) {
			mav.addObject("result", "등록 성공");
		}else {
			mav.addObject("result", "등록 실패");			
		}
		
		mav.setViewName("/admin/productRegister");
		return mav;
	}
	
}
