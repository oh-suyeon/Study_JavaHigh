package practice;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class P04_FileWriter {
	
	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		
		FileWriter fw = new FileWriter("d:/D_Other/testChar2.txt");
		int c;
		System.out.println("입력");
		
		while((c=isr.read()) != -1) {
			fw.write(c);
		}
		System.out.println("끝");
		isr.close();
		fw.close();
	}
	
}
