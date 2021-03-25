package com.mall.vo.inquiry;

import java.security.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InquiryVo {

	private int cs_no; // cs 번호
	private String cs_mem_id; // 고객 아이디
	private String cs_title; // 제목
	private String cs_content; // 내용
	private Timestamp cs_regdate; // 작성일
	private String cs_fname; // 문의사항 사진
	private String cs_category_one; // 카테고리 대분류
	private String cs_category_two; // 카테고리 소분류
	private MultipartFile uploadFile; //업로드되는 파일
	
}
