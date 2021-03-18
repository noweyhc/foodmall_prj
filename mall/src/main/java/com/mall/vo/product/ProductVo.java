package com.mall.vo.product;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVo {
	private int product_no;
	private String product_category;
	private int product_stock;
	private String product_title;
	private String product_subtitle;
	private String product_main_img;
	private int product_caloies;
	private String product_origin;
	private String product_storage;
	private String product_detail_img1;
	private String product_detail_img2;
	private String product_detail_text;
	private String product_ingredient;
	private int product_price;
	private Timestamp product_regdate;
	private MultipartFile mainImgFile;
	private MultipartFile detailImgFile1;
	private MultipartFile detailImgFile2;
}
