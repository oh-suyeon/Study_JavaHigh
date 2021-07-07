package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class T12_BufferedIOTest {
	public static void main(String[] args) throws IOException  {
		FileReader fr = new FileReader("./src/kr/or/ddit/basic/T11_BufferedIOTest.java");
		/*
		int c;
		while((c=fr.read()) != -1) {
			System.out.print((char) c);
		}
		fr.close();
		*/
		
		BufferedReader br = new BufferedReader(fr);
		String temp = "";
		for(int i = 1; (temp=br.readLine()) != null; i++) {	// 다 읽으면 null을 반환
			System.out.printf("%4d : %s\n", i, temp);
		}
		br.close();
		//
		
	}
}
