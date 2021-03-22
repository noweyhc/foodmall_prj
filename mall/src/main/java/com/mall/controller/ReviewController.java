package com.mall.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mall.dao.product.ProductDao;
import com.mall.dao.review.ReviewDao;
import com.mall.util.FileNameUtil;
import com.mall.vo.reivew.ReviewVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ReviewController {
	
	private final ProductDao productDao;
	private final ReviewDao reviewDao;
	private final FileNameUtil fileNameUtil;
	
	@GetMapping("/reviewList")
	public String reviewList() {
		return "reviewList";
	}//reviewList
	
	//상품번호를 받아와서 model로 넘겨준다. 글작성부분에서는 상품제목을 받아서 review를 작성
	@GetMapping("/reviewWrite")
	public String reviewWriteForm(@RequestParam(value = "product_no", defaultValue = "6705") int product_no,Model model) {
		//상품의 번호를 받아 제목을 검색하여 title를 넘겨준다.
		model.addAttribute("product",productDao.selectOne(product_no));
		//글쓰기 페이지로 이동
		return "reviewWrite";
	}//reviewWrite
	
	@PostMapping("/reviewWrite")
	public String reviewWrite(ReviewVO reviewVO,HttpSession httpSession,HttpServletRequest request) {
		//현재 로그인 되어있는 사용자 아이디를 얻어온다.
		reviewVO.setMem_id("leewooo");
		System.out.println(reviewVO.getMem_id());
		
		//경로 얻어오고
		String path = request.getServletContext().getRealPath("/img");
		//사용자가 업로드한 파일 얻어오고
		MultipartFile uploadFile = reviewVO.getUploadFile();
		//사용자가 업로드한 파일의 파일명을 얻어온다. (중복검사)
		String fname = fileNameUtil.FileRename(path, uploadFile.getOriginalFilename());
		
		//만약 사용자가 올린 파일이 존재한다면?
		if(!"".equals(fname.trim()) && fname != null) {
			try{
				//사용자가 올린 파일의 Data를 가져오고
				byte[] data = uploadFile.getBytes();
				//스트림을 연결한다.
				FileOutputStream fileOutputStream = new FileOutputStream(new File(path+"/"+fname));
				//지정한 경로로 파일을 저장하고
				fileOutputStream.write(data);
				//스트림 끊기
				fileOutputStream.close();
				//저장 된 파일명 vo에 담기
				reviewVO.setReview_fname(fname);
			} catch (IOException e) {
				e.printStackTrace();
			}//end catch
		}//end if
		
		//DB저장
		int result = reviewDao.save(reviewVO);
		
		if(result == 1) {
			//이 후 리뷰 페이지로 리다이렉트
			return "redirect:reviewList";
		}//end if
		
		//만약 DB결과값이 1이아니라면 올라간 파일 삭제
		new File(path + "/" + fname).delete();
		return "error";
		
	}//reviewWrite

}
