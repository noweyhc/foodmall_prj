package com.mall.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class AdminUtil {
	
	//해당 경로의 이미지 파일을 삭제하는 함수
	public void deleteImg(String path) {
		File file = new File(path);
		if(file.exists()) {
			file.delete();
		}
	}
	
	
	/**
	 * @param no 상품 번호
	 * @param imgFile 이미지 파일 객체
	 * @param tale 이미지 파일에 붙일 꼬리표 (메인이미지는 없음, 상세이미지는 _detail1,2)
	 * @return 새로운 파일 이름
	 */
	public String imgRename(int no, MultipartFile imgFile, String tale) {
		String newName = "";
		
		// 새로운 파일 이름 : product[상품번호][꼬리표].확장자
		newName = "product" + String.valueOf(no) + tale + 
				imgFile.getOriginalFilename().substring(imgFile.getOriginalFilename().indexOf("."));
		
		return newName;
	}
	
	/**
	 * @param path 이미지를 저장할 서버 경로
	 * @param imgFile 서버에 업로드할 이미지
	 * @param fileName 서버에 업로드할 이미지 이름
	 * @throws IOException 
	 */
	public void uploadImg(String path, MultipartFile imgFile, String fileName) throws IOException {
		byte []data = imgFile.getBytes();
		FileOutputStream fos = new FileOutputStream(path + "/" + fileName);
		fos.write(data);
		fos.flush();
		fos.close();
	}
}
