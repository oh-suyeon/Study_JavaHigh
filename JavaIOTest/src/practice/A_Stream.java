package practice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class A_Stream {
	public static void main(String[] args) throws IOException {
		
		FileInputStream fis = new FileInputStream("d:/D_Other/Tulips.jpg");
		BufferedInputStream bis = new BufferedInputStream(fis, 5);
		
		FileOutputStream fos = new FileOutputStream("d:/D_Other/복사본_Tulips.jpg");
		BufferedOutputStream bos = new BufferedOutputStream(fos, 5);
		
		int c;
		
		while((c=bis.read()) != -1) {
			bos.write(c);
		}
		
		System.out.println("복사 완료");
		
		bis.close();
		bos.close();
		
	}
}
