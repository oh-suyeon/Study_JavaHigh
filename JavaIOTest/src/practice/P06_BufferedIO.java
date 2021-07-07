package practice;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class P06_BufferedIO {
	
	public static void main(String[] args) throws IOException {
		
		FileOutputStream fow = new FileOutputStream("d:/D_Other/bufferTest1.txt");
		BufferedOutputStream bos = new BufferedOutputStream(fow, 5);
		for(int i = '1'; i <= '9'; i++) {
			bos.write(i);
			System.out.println(i);
		}
		bos.flush();
		System.out.println("끝");
		
		bos.close();
		
		FileReader fr = new FileReader("./src/kr/or/ddit/basic/T11_BufferedIOTest.java");
		BufferedReader br = new BufferedReader(fr);
		
		String temp = "";
		
		for(int i = 1; (temp = br.readLine()) != null; i++) {
			System.out.printf("%4d : %s\n", i, temp);
		}
		br.close();
		
		
	}
	
	
}
