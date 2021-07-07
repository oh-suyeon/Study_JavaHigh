package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class T09_FileEncodingTest {
	public static void main(String[] args) {
		// 파일 인코딩으로 읽어오기
		FileInputStream fis = null;
		InputStreamReader isr = null;
		
		try {
			//FileInputStream 객체 생성 후 이 객체 매개변수로 받는 InputStreamReader 객체 생성
//			fis = new FileInputStream("d:/D_Other/test_ansi.txt");
			fis = new FileInputStream("d:/D_Other/test_utf8.txt");
//			isr = new InputStreamReader(fis, "CP949");
			isr = new InputStreamReader(fis, "UTF-8");
			int c;
			while((c=isr.read()) != -1) {
				System.out.print((char)c);
			}
			System.out.println();
			System.out.println("출력 끝!");
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				isr.close(); // 보조스트림만 닫아도 됨
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
