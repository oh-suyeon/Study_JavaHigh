package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T04_ErrorHandler extends HttpServlet {
		// 에러 페이지를 그대로 사용자에게 보여주는 건 기본이 아니다. 
		// 에러 페이지는 개발자가 따로 만든 걸 사용자에게 보여준다.
		// 네이버 등의 페이지에서 '찾을 수 없는 페이지' 페이지를 따로 만들어 보여주는 것처럼.
	
		//
	
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			// 서블릿 예외 정보 확인하기
			Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
			
			// 에러 상태 코드 
			Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
			
			// 에러 발생한 서블릿 이름
			String servletName = (String) req.getAttribute("javax.servlet.error.servlet_name");
			
			if(servletName == null) {
				servletName = "알 수 없는 서블릿 이름";
			}
		
			String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");
			
			if(requestUri == null) {
				requestUri = "알 수 없는 URI";
			}
			
			// 응답 헤더 설정
			
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html");
			
			PrintWriter out= resp.getWriter();
			String title = "에러 및 예외 정보";
			
			out.println("<html><head><title>" + title + "</title></head>\n");
			
			if(throwable == null && statusCode == null) {
				out.println("<h2>에러정보 없음</h2>");
			}else if(statusCode != null) {
				out.print("상태코드 : " + statusCode);
			}else {
				out.println("<h2>예외/에러 정보</h2>");
				out.println("서블릿 이름 : " + servletName + "<br><br>");
				out.println("예외 타입 : " + throwable.getClass().getName() + "<br><br>");
				out.println("요청 URI : " + requestUri + "<br><br>");
				out.println("예외 메시지 : " + throwable.getMessage() + "<br><br>");
			}
			out.println("</body>");
			out.println("</html>");
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
		}
}
