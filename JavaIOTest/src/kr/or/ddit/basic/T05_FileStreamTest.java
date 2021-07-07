package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

// 파일읽기
public class T05_FileStreamTest {
	public static void main(String[] args) {
		
		FileInputStream fis = null;
		
		try {
			// 방법1. 파일 정보 문자열로 지정
			fis = new FileInputStream("d:/D_Other/test2.txt");
			
			// 방법2. 파일정보 File 객체 이용해 지정
	//			File file = new File("d:/D_Other/test2.txt");
	//			fis = new FileInputStream(file);
			
			int c; // 읽어온 데이터 저장할 변수
			
			while((c=fis.read()) != -1) {
				System.out.print((char) c);	// 1바이트로 표현할 수 있는 알파벳은 제대로 나오지만 한글은 2바이트 이상필요해서 깨진다. ㅂ
			}
			
			fis.close(); // 작업 완료 후 스트림 닫기
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
