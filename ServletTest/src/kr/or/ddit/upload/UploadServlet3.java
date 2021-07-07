package kr.or.ddit.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload3")
//멀티파트라는 걸 알려줘야 한다. 
// 기본 서블릿 api로 작업할 거니까 톰캣한테 이거 처리하라고 알려주려면
@MultipartConfig(fileSizeThreshold = 1024*1024*3, maxFileSize = 1024*1024*40, maxRequestSize = 1024*1024*50)
public class UploadServlet3 extends HttpServlet{

	private static final String UPLOAD_DIRECTORY = "upload_files";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		try {
			
			String fileName = "";
			for(Part part : req.getParts()) {
				System.out.println(part.getHeader("content-disposition"));
				
				fileName = getFileName(part);
				
				if(fileName != null && !fileName.equals("")) { // 파일이 있다면. !fileName.equals("") 파일 첨부 인풋 있긴 한데 첨부를 안한경우.
					part.write(uploadPath + File.separator + fileName);
					
				}
			}
			
//			resp.setCharacterEncoding("UTF-8");
//			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().print("업로드 완료");
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("파라미터 값 : " + req.getParameter("sender"));
	}
	
	/**
	 * Part 객체 파싱하여 파일 이름 추출하기
	 * @param part
	 * @return 파일명 : 파일명이 존재하지 않으면, 즉 파일이 아니라 일반 폼필드 값이라면 null 값 리턴함 (폼필드)
	 */
	private String getFileName(Part part) {
		// getHeader => Content-Disposition의 헤더 값을 얻어온다. 스트링으로 얻어와서 구분자로 split하면 스트링 배열이 된다. 
		// 하나씩 꺼내올 수 있다. ; 사이 사이에 공백있으니까 trim으로 제거하기 
		// filename으로 시작하는 거 찾기 - 있으면 파일이다. 파일 이름은 substring으로 가져오기
		for(String content : part.getHeader("Content-Disposition").split(";")) {
			if(content.trim().startsWith("filename")) {
				return content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
			}
		}
		return null; // filename 없을 경우 (일반 폼필드)
	}
	
}
