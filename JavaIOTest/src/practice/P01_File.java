package practice;

import java.io.File;

public class P01_File {
	
	public static void main(String[] args) {
		File file = new File("d:/D_Other/Tulips.jpg");
		System.out.println(file.getName());
		System.out.println(file.isFile());
		System.out.println(file.isDirectory());
		System.out.println(file.length());
		System.out.println(file.getAbsolutePath());
		
		File prac = new File("d:/D_Other/prac");
		if(prac.mkdir()) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
			
		}
	}
}

