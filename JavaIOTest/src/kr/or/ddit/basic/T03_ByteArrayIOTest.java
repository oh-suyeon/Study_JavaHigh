package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T03_ByteArrayIOTest {
	
	public static void main(String[] args) throws IOException {
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		
		// 스트림 선언 및 객체 생성
		ByteArrayInputStream bais = null; // 스트림
		bais = new ByteArrayInputStream(inSrc);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int data;
		
		// read() 메서드 : byte 단위로 자료읽어와 int형으로반환
		// 더 이상 읽을 자료 없으면 -1 반환
		while((data = bais.read()) != -1) {
			baos.write(data);
		}
		outSrc = baos.toByteArray();
		System.out.println("inSrc => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));
		
		bais.close();
		baos.close();

	}
	
}
