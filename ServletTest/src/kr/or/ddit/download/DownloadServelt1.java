package kr.or.ddit.download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/filedown")
public class DownloadServelt1 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = "sparrow.png";
		// 파일 다운로드 처리를 위한 응답헤더 정보 설정하기
		resp.setContentType("application/octet-stream"); // octet-stream. 잘 모르겠는데 바이너리 데이터라면 이걸 써준다.
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("d:/D_Other/" + fileName));
		
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		
		int c = 0;
		
		while((c=bis.read()) != -1) {
			bos.write(c);
		}
		bis.close();
		bos.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	
}
