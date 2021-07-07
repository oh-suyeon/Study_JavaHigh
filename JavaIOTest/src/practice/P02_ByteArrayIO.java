package practice;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class P02_ByteArrayIO {
	public static void main(String[] args) throws IOException {
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4];
		
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int data;
		
//		while ((data = bais.read()) != -1) {
//			baos.write(data);
//		}
		
		while(bais.available() > 0) {
			int len = bais.read(temp);
			baos.write(temp, 0, len);
			
			System.out.println(Arrays.toString(temp));
		}
		System.out.println();
		outSrc = baos.toByteArray();
		System.out.println(Arrays.toString(inSrc));
		System.out.println(Arrays.toString(outSrc));
		
		bais.close();
		baos.close();
		
	}
}
