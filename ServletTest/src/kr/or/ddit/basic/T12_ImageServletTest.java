package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/T12_ImageServletTest") // val이 딱 하나면 value=는 생략 가능. url 패턴을 써준다. 
public class T12_ImageServletTest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 먼저 Content-Type을 알려줘야 한다.
		resp.setContentType("image/jpg");
		
		// 이미지 쏴주기 1. 통로 만들기
		ServletOutputStream out = resp.getOutputStream();
		FileInputStream fis = new FileInputStream("d:/D_Other/Tulips.jpg");
		
		BufferedInputStream bis = new BufferedInputStream(fis);
		BufferedOutputStream bos = new BufferedOutputStream(out);
		
		// 이미지 쏴주기 2. 쏴주기
		
		int readBytes = 0; // 읽은 바이트 수
		
		while((readBytes = bis.read()) != -1) {
			bos.write(readBytes);
		}
		
		bis.close();
		bos.close();
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
