package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class P05_FileEncoding {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("d:/D_Other/test_utf8.txt");
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		
		int c;
		
		while((c=isr.read()) != -1) {
			System.out.print((char) c);
		}
		System.out.println();
		
		isr.close();
		
		
	}
	
}
