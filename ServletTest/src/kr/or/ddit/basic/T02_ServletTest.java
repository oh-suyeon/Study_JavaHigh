package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T02_ServletTest extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Post 방식으로 넘어오는 Body 데이터를 인코딩 처리. 
		// get 방식은 톰캣의URLEncoding 설정을 이용함.
		// 반드시 request에서 값을 가져오기 전에 먼저 설정해야 적용됨.
		//req.setCharacterEncoding("UTF-8"); // 한글을 쓸 거라 인코딩 먼저 해준다. 
		
		// 요청정보로부터 name값을 가져옴.
		String name = req.getParameter("name"); // http://localhost/ServletTest/T02_ServletTest 뒤에  "?name=홍길동" 붙인다. url이 허용하는 길이까지 &으로 연결해서 쓸 수있다. (쿼리)
		// 그렇게 던졌더니 깨진다. 바디가 아니라 url로 보냈기때문. 한글을 보내려면 보낼 때 인코딩 처리를 해야한다. 
		// server 자바 프로젝트의 server.xml에서 connector에 URIEncoding="UTF-8"를 추가한다. 
		
		System.out.println("name => " + name);
		
		// 응답 메시지 인코딩 설정
		//resp.setCharacterEncoding("UTF-8");
		// 응답 메시지 컨텐트 타입 설정
		resp.setContentType("text/plain");  // MIME 타입. plain는 일반 문자열
		
		// 실제 수행할 로직(기눙)이 시작되는 부분
		PrintWriter out = resp.getWriter(); // 문자 기반 출력 스트림. 
		out.println("name =>" + name);
		out.println("서블릿 경로 =>" + req.getServletPath());
		out.println("컨텍스트 경로 =>" + req.getContextPath());
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
