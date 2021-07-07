package practice;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

public class P08_PrintStream {
	
	public static void main(String[] args) throws IOException {
		
		FileOutputStream fos = new FileOutputStream("d:/D_Other/print4.txt");
		PrintStream out = new PrintStream(fos);
		
		out.print("하하\n");
		out.println("안녕하세요. PrintStream입니다.2");
		out.println(out);
		out.close();
		
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/print5.txt");
		
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos2, "UTF-8"));
		
		pw.print("ㅇㅇㅇㅇ");
		pw.close();
	}
	
}
