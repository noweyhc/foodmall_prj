package com.mall.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mall.dao.product.ProductDao;
import com.mall.dao.user.UserDAO;
import com.mall.vo.product.ProductVo;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	
	private final ProductDao dao;

	//관리자 메인 페이지로 이동
	@RequestMapping("")
	public String adminMainpage() {
		return "/admin/adminMain";
	}
	
	//url : /admin/product-register
	//GET 방식 접근일 때, 상품 등록 페이지로 이동
	@RequestMapping(value = "/product-register", method = RequestMethod.GET)
	public String registerProductForm() {
		return "/admin/productRegister";
	}

	//url : /admin/product-register
	//POST 방식 접근일 때, 입력된 정보의 상품을 등록함
	@RequestMapping(value = "/product-register", method = RequestMethod.POST)
	public ModelAndView registerProduct(ProductVo pv, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		
		//상품 이미지를 등록할 webapp/img 경로
		String path = request.getRealPath("/img");
		
		//파일 데이터를 받아와서 변수에 저장
		MultipartFile uploadImg = pv.getMainImgFile();
		MultipartFile uploadDetailImg1 = pv.getDetailImgFile1();
		MultipartFile uploadDetailImg2 = pv.getDetailImgFile2();
	
		//이미지 파일 이름 수정
		String mainImgName = "";
		String detailImgName1 = "";
		String detailImgName2 = "";
		
		//이미지 파일 이름을 product상품번호 형태로 수정하고, 필수 이미지가 없을 시 안내 메세지와 함께 돌려 보냄
		try {
			mainImgName =("product" + String.valueOf(pv.getProduct_no()) + uploadImg.getOriginalFilename().substring(uploadImg.getOriginalFilename().indexOf(".")));
			detailImgName1 =("product" + String.valueOf(pv.getProduct_no()) + "_detail1" + uploadDetailImg1.getOriginalFilename().substring(uploadDetailImg1.getOriginalFilename().indexOf(".")));
			//상세이미지2가 존재하는지 확인
			if(uploadDetailImg2.getSize() != 0) {
				detailImgName2 =("product" + String.valueOf(pv.getProduct_no()) + "_detail2" + uploadDetailImg2.getOriginalFilename().substring(uploadDetailImg2.getOriginalFilename().indexOf(".")));							
			}
		}catch(Exception e){
			mav.addObject("result", "메인이미지와 상세이미지1은 필수입니다.");	
			mav.setViewName("productRegister");
			return mav;
		}
		
		//DB에 수정된 이미지 파일 이름을 저장
		pv.setProduct_main_img(mainImgName);
		pv.setProduct_detail_img1(detailImgName1);
		pv.setProduct_detail_img2(detailImgName2);

		
		try {
			byte []data = uploadImg.getBytes();
			
			//webapp/img 경로에 이미지 파일을 차례로 저장
			FileOutputStream fos = new FileOutputStream(path + "/" + mainImgName);
			fos.write(data);
			fos.flush();
			data = uploadDetailImg1.getBytes();
			fos = new FileOutputStream(path + "/" + detailImgName1);
			fos.write(data);
			fos.flush();
			
			//상세이미지2가 있는지 확인
			if(uploadDetailImg2.getSize() != 0) {
				data = uploadDetailImg2.getBytes();
				fos = new FileOutputStream(path + "/" + detailImgName2);
				fos.write(data);
				fos.flush();
			}
			
			fos.close();
			
		} catch (IOException e) {
			mav.addObject("result", "이미지 등록 중 문제가 발생하였습니다. 다시 시도하세요.");	
			mav.setViewName("productRegister");
			return mav;
		}
		
		//쿼리문을 실행하고, 결과값을 확인
		int re = dao.registerProduct(pv);
		
		//등록 성공:1 , 실패:0
		if(re == 1) {
			mav.addObject("result", "등록 성공");
		}else {
			mav.addObject("result", "DB 등록 실패. 상품번호를 확인해주세요.");			
		}
		
		mav.setViewName("/admin/productRegister");
		return mav;
	}
	
	
	//url : /admin/product-edit
	//GET 방식 접근일 때, 상품 목록 창으로 이동
	@RequestMapping(value = "/product-edit", method = RequestMethod.GET)
	public ModelAndView editProductForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/productEdit");
		mav.addObject("list", dao.findAll());
		return mav;
	}
	
	//url : /admin/product-edit/상품번호
	@RequestMapping("/product-edit/{productNo}")
	public ModelAndView editProductFormDetail(@PathVariable String productNo) {
		ModelAndView mav = new ModelAndView();
		int no = Integer.parseInt(productNo);
		mav.addObject("product", dao.selectOne(no));
		mav.setViewName("/admin/productEditDetail");
		return mav;
	}
	
	//url : /admin/product-edit/delete?no=값
	@RequestMapping("/product-edit/delete")
	public String deleteProduct(int no, HttpServletRequest request) {
		//서버에서 해당 상품의 관련 이미지를 삭제
		ProductVo pv = dao.selectOne(no);
		
		//해당 상품의 이미지 경로를 배열에 담고, 차례로 삭제
		List<String> imgList = new ArrayList<>();
		imgList.add(request.getRealPath("/img") + "/" + pv.getProduct_main_img());
		imgList.add(request.getRealPath("/img") + "/" + pv.getProduct_detail_img1());
		if(pv.getProduct_detail_img2() != null) {
			imgList.add(request.getRealPath("/img") + "/" + pv.getProduct_detail_img2());			
		}
		
		for(String path : imgList) {
			File file = new File(path);
			if(file.exists()) {
				file.delete();
			}
		}
		
		//DB에서 해당 상품의 데이터를 삭제
		dao.deleteProduct(no);
		return "redirect:/admin/product-edit";
	}
	
}
