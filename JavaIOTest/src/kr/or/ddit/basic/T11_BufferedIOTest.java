package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class T11_BufferedIOTest {
	
	public static void main(String[] args) {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		
		try {
			fos = new FileOutputStream("d:/D_Other/bufferTest.txt");
			// 버퍼 크기 지정하지 않으면 기본값 8192byte(8kb)로 설정
			// 버퍼 크기가 5인 스트림 생성
			bos = new BufferedOutputStream(fos, 5);	// byte단위. 너무 크면 램을 많이 쓴다. 
			for(int i = '1'; i <= '9'; i++) {
				bos.write(i);	// 버퍼에 하나씩 쌓은 다음에 5개가 차면 기반 스트림이 데이터를 보낸다. 두
				System.out.println(i);
			}
			bos.flush(); // 작업 종료 전 버퍼에 남은 데이터 모두 출력 (close시 자동으로 호출됨.) (마지막 버퍼는 4개밖에 안 차니까 데이터를 보내지 않고 끝나버린다. 그래서 필요.)
			System.out.println("작업끝!");
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
