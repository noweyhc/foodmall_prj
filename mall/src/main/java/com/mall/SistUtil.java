package com.mall;

import java.io.File;

public class SistUtil {
	
	public static String getFileName(String fname, String path) {
		String newFname = null;
		
		File dir = new File(path);
		//디렉토리(폴더)는 파일로 취급해요!
			
		String []file_list= dir.list();
		//그 디렉토리(폴더)의 모든 파일목록을 갖고와요!
		
		
		boolean flag = false;
		for(String name:file_list) {
			if(name.equals(fname)) {
				flag = true;
				break;
			}
		}
		//디렉토리의 모든 파일명을 일일이 매개변수로 전달받은 파일명과 
		//비교합니다. 같은 파일명이 있으면 flag를 true를 담아요!
		
		//만약 flag를 true이면 다른이름을 정해주고
		//그렇지 않으면 그이름을 사용하도록 합니다.
		
		newFname = fname;
		if(flag == true) {
			// ball1.jpg
			// ball1
			String name = newFname.substring(0, newFname.indexOf("."));
			
			// ball1.jpg			
			//.jpg
			String ext = newFname.substring(newFname.indexOf("."));
			
			newFname = name + System.currentTimeMillis() + ext;
		}		
		
		return newFname ;
	}
}
