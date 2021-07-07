package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class T10_FileEncodingTest {
	
	public static void main(String[] args) throws IOException {
		//out_utf8.txt-> utf-8
		//out_ansi.txt-> ms949
		
		InputStreamReader isr = new InputStreamReader(System.in);
		
		FileOutputStream fos1 = new FileOutputStream("d:/D_Other/out_utf8.txt");
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/out_ansi.txt");
		
		//OutputStreamWriter는 출력 스트림에 연결되어, 문자 출력 스트림인 Writer로 변환시키는 보조 스트림.
		OutputStreamWriter osw1 = new OutputStreamWriter(fos1, "utf-8");
		OutputStreamWriter osw2 = new OutputStreamWriter(fos2, "ms949");
		
		int c;
		System.out.println("이무거나 입력하세요.");
		
		while((c=isr.read()) != -1) {
			osw1.write(c);
			osw2.write(c);
		}
		
		System.out.println("작업 끝...");
		
		isr.close();
		osw1.close();
		osw2.close();
	}
	
}
