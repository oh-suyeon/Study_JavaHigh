package kr.or.ddit.basic;

import java.io.FileReader;
import java.io.IOException;

public class T08_FileReaderTest {
	
	public static void main(String[] args) throws IOException {
		
		//문자 기반 스트림으로 파일 내용 읽기
		FileReader fr = null; 
		
		fr = new FileReader("d:/D_Other/testChar2.txt");
		
		int c;
		
		while((c = fr.read()) != -1) {
			System.out.print((char) c);
		}
	}
	
	
}
