package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class P03_FileStream {
	
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("d:/D_Other/out2.txt");
		for(char ch = 'a'; ch <= 'z'; ch++) {
			fos.write(ch);
		}
		
		System.out.println("작업 완료.");
			
		fos.close();
		
		FileInputStream fis = new FileInputStream("d:/D_Other/out2.txt");
		int c;
		while ((c = fis.read()) != -1) {
			System.out.print((char) c);
		}
		System.out.println("작업 완료.");
		fis.close();
		
	}
	
}
