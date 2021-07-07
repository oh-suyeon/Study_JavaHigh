package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class T07_FileWriterTest {
	public static void main(String[] args) {
		InputStreamReader isr = new InputStreamReader(System.in); // in은 콘솔 입력 (바이트 기반 인풋스트림)
		
		FileWriter fw = null;
		
		try {
			//파일 출력용 문자 스트림 객체 생성
			fw = new FileWriter("d:/D_Other/testChar.txt");
			int c;
			System.out.println("아무거나 입력하세요.");
			// 콘솔에서 입력 다 하면 Ctrl + z 키를 누른다.
			while((c=isr.read()) != -1) { // 다 읽으면 -1을 반환
				fw.write(c); // 콘솔로 받은 값 파일로 출력
			}
			System.out.println("작업 끝...");
			isr.close();
			fw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
