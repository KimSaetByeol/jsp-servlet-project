package com.saetbyeol.util;

import java.io.File;

public class FileThing {
	public void fileDelete(String path, String fileName) {
		System.out.println(path);
		System.out.println(fileName);
		
		File file = new File(path + fileName);
		if(file.exists()) {
			if(file.delete()) {
				System.out.println("파일 삭제 성공");
			} else {
				System.out.println("파일 삭제에 문제 발생");
			}
		} else {
			System.out.println("파일이 없음");
		}
	}
}
