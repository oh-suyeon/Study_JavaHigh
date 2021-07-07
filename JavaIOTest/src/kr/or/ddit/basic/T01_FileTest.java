package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

public class T01_FileTest {
	
	public static void main(String[] args) throws IOException {
		
		// 파일 객체 핸들링 하기
		File file = new File("d:\\D_Other\\test.txt");
		System.out.println("파일명 : " + file.getName());
		System.out.println("파일여부 : " + file.isFile());
		System.out.println("디렉토리(폴더) 여부 : " + file.isDirectory());
		System.out.println("-------------------------------------");
		
		File file2 = new File("d:\\D_Other");
		System.out.println(file2.getName() + "은");
		if(file2.isFile()) {
			System.out.println("파일");
		} else {
			System.out.println("디렉토리(폴더)");
		}
		System.out.println("-------------------------------------");
		
		File file3 = new File(file2, "test.txt");
		System.out.println(file3.getName() + "의 용량 크기 : " + file3.length() + "bytes");
		
		File file4 = new File("/D_Other/", "test.txt");
		System.out.println("절대경로 : " + file4.getAbsolutePath());
		System.out.println("경로 : " + file4.getPath());
		System.out.println("표준 경로 : " + file4.getCanonicalPath());	// 예외 던지기
		
		System.out.println("현재 클래스의 ");
		
		
		
		// 디렉토리 만들기
		File file5 = new File("d:/D_Other/연습용");
		if(file5.mkdir()) {
			System.out.println(file5.getName() + " 만들기 성공");
		} else {
			System.out.println(file5.getName() + " 만들기 실패");
		}
		System.out.println();
		
		File file6 = new File("d:/D_Other/test/java/src");
		if(file6.mkdir()) {
			System.out.println(file6.getName() + " 만들기 성공");
		} else {
			System.out.println(file6.getName() + " 만들기 실패");
		}
		System.out.println();

		File file7 = new File("d:/D_Other/test/java/src");
		if(file7.mkdirs()) {
			System.out.println(file7.getName() + " 만들기 성공");
		} else {
			System.out.println(file7.getName() + " 만들기 실패");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
