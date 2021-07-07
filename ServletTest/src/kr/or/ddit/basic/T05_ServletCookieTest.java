package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T05_ServletCookieTest extends HttpServlet {

	/**
	 * 쿠키 정보 다루기 위한 예제
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//setCookieExam(req, resp); // 쿠키 설정 예제
		//readCookieExam(req, resp); // 쿠키 정보 읽기 예제
		deleteCookieExam(req, resp); // 쿠키 삭제 예제
	}
	
	private void deleteCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		Cookie cookie = null;
		Cookie[] cookies = req.getCookies();
		
		
		// 응답 헤더에 인코딩 및 Content Type 설정
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		String title = "쿠키 정보 삭제 예제";
		
		out.println("<html><head><title>" + title
					+ "</title></head>");
		out.println("<body>");
		if(cookies != null) {
			out.println("<h2>" + title + "</h2>");
			for(int i=0; i<cookies.length; i++) {
				cookie = cookies[i];
				if((cookie.getName()).equals("userId")) {
					// 쿠키 제거하기
					cookie.setMaxAge(0);
					resp.addCookie(cookie);
					out.println("삭제한 쿠키 : " + cookie.getName() + "<br>");
				}
				out.print("쿠키이름 : " + cookie.getName() + ", ");
				out.print("쿠키 값 : " + URLDecoder.decode(cookie.getValue(), "utf-8") + "<br>");
			}
		}else{
			out.print("<h2>쿠키 정보가 없습니다.</h2>");
		}
		out.println("</body>");
		out.println("</html>");
		
	}

	private void readCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		// 현재 도메인에서 사용중인 쿠키 정보 배열 가져오기
		Cookie[] cookies = req.getCookies();
		Cookie cookie = null;
		
		// 응답 헤더에 인코딩 및 Content Type 설정
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		String title = "쿠키 정보 읽기 예제";
		
		out.println("<html><head><title>" + title 
				+ "</title></head>");
		out.println("<body>");
		
		if(cookies != null) {
			out.println("<h2>" + title + "</h2>");
			
			for(int i=0; i<cookies.length; i++) {
				cookie = cookies[i];
				out.print("name : " + cookie.getName() + "<br>");
				out.print("value : " + URLDecoder.decode(cookie.getValue(), "utf-8") + "<br>");
				out.print("domain : " + cookie.getDomain() + "<br>");
				out.print("path : " + cookie.getPath() + "<br>");
				out.print("MaxAge(초) : " + cookie.getMaxAge() + "<br>");
				out.print("<hr>");
			}
		}else {
			out.println("<h2> 쿠키 정보가 없습니다. </h2>");
		}
		out.print("</body>");
		out.print("</html>");
	}

	private void setCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 쿠키 생성하기
		Cookie userId = new Cookie("userId", req.getParameter("userId"));
		
		// 쿠키 값에 한글을 사용시 인코딩 처리를 한다. 
		Cookie name = new Cookie("name", URLEncoder.encode(req.getParameter("name"), "utf-8"));
		
		//쿠키 소멸시간 설정(초 단위)
		userId.setMaxAge(60 * 60 * 24); // 1일
		userId.setHttpOnly(true); // javascript를 이용한 직접 접근 방지
		
		name.setMaxAge(60 * 60 * 48); // 2일
		
		// 응답 헤더에 쿠키 추가하기 
		resp.addCookie(userId);
		resp.addCookie(name);
		
		// 응답 헤더에 인코딩 및 Content-Type 설정 -- 우리가 설정한 걸 사용자에게 보여주려고 브라우저에 뿌리고 싶어서 html으로 만든 것. 
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		String title = "쿠키 설정 예제";
		
		out.println("<html><head><title>" + title + "</title></head>");
		out.println("<body><h1 align = \"center\">" + title + "</h1>");
		out.println("<ul><li><b>ID</b>: " + req.getParameter("userId") + "</li>"
					+ "<li><b>이름</b>: " + req.getParameter("name") + "</li>");
		out.println("</body></html>");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
