package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

public class T14_PrintStreamTest {
	
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("d:/D_Other/print.txt");
		
	//	PrintStream out = new PrintStream(System.out);	// 기반 스트림. 콘솔 창에 출력.
		PrintStream out = new PrintStream(fos);	// 기반 스트림. 파일에 출력.
		
		out.print("안녕하세요. PrintStream입니다.\n");
		out.println("안녕하세요. PrintStream입니다.2");
		out.println("안녕하세요. PrintStream입니다.3");
		out.println(out); // 객체 출력
		out.println(3.14);
		out.close();
			
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/print2.txt");
		
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos2, "UTF-8"));
		// 보조 스트림 안에 보조 스트림이 있어도 상관없다. 
		// 내부의 보조 스트림 안에 기반 스트림이 있으니까.
		// 여러 개의 보조 스트림을 중첩할 수 있다.
		
		
		pw.print("안녕하세요. PrintWriter 입니다. \n");
		pw.println("안녕하세요. PrintWriter 입니다.2");
		pw.println("안녕하세요. PrintWriter 입니다.3");
		
		pw.close();
	}
	
}
