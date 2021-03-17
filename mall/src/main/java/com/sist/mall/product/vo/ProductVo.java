package com.sist.mall.product.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductVo {
	private int no;
	private String category;
	private int stock;
	private String title;
	private String subtitle;
	private String mainImg;
	private int caloies;
	private String origin;
	private String storage;
	private String detailImg1;
	private String detailImg2;
	private String detailText;
	private String ingredient;
	private int price;
	private String regdate;
	private MultipartFile mainImgFile;
	private MultipartFile detailImgFile1;
	private MultipartFile detailImgFile2;
}
