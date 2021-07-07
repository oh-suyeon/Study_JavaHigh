package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04_ByteArrayIOTest {
	
	public static void main(String[] args) {
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}; // 10번 읽는게 비효율적이니까
		byte[] outSrc = null;
		
		byte[] temp = new byte[4]; // 자료 읽기에 사용할 배열을생성한다.
		
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			while(bais.available() > 0) {
				/*
				bais.read(temp); //temp배열 크기만큼 읽어와 temp배열에 저장
				baos.write(temp);	// temp 배열 내용 출력.
				*/
				
				int len = bais.read(temp); // 실제 읽어온 byte 수를 반환
				baos.write(temp, 0, len); // temp의 배열 내용 중 0번째부터 len개수만큼 출력
	
				System.out.println("temp : " + Arrays.toString(temp));
			}
			System.out.println();
			outSrc = baos.toByteArray();
			System.out.println("inSrc => " + Arrays.toString(inSrc));
			System.out.println("outSrc => " + Arrays.toString(outSrc));
			
			bais.close();
			baos.close();
			
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
