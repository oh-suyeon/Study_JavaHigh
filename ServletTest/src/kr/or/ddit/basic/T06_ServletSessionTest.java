package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class T06_ServletSessionTest extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 세션 가져오는데 없으면 새로 생성한다. 
		HttpSession session = req.getSession(true);
		
		// 생성시간 가져오기
		Date createTime = new Date(session.getCreationTime());
		
		// 마지막 접근 시간 가져오기
		Date lastAccessTime = new Date(session.getLastAccessedTime());
		
		String title = "재방문을 환영합니다.";
		
		int visitCount = 0; // 방문 횟수
		
		String userId = "sem"; // 사용자 아이디
		
		if(session.isNew()) { // 세션이 새로 만들어졌는지 확인
			title = "첫 방문을 환영합니다.";
			session.setAttribute("userId", userId);
		}else {
			visitCount = (Integer) session.getAttribute("visitCount");
			visitCount++;
			userId = (String) session.getAttribute("userId");
		}
		
		System.out.println("방문 횟수 : " + visitCount);
		session.setAttribute("visitCount", visitCount);
		
		// session.invalidate();// 지워버리기
		session.setMaxInactiveInterval(30);
		
		// 응답 헤더에 인코딩 및 content-type 설정
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<html><head><title>" + title 
				+ "</title></head>");
		out.println("<body><h1 align=\"center\">" + title
				+ "</h1>"
				+ "<h2 align=\"center\">세션 정보</h2>"
				+ "<table border=\"1\" align=\"center\">"
				+ "<tr bgcolor=\"orange\">" 
				+ "<th>구분</th><th>값</th></tr>"
				+ "<tr>"
				+ "<td>세션ID</td><td>" + session.getId() + "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>생성시간</td><td>" + createTime + "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>마지막 접근시간</td><td>" + lastAccessTime + "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>유저ID</td><td>" + userId + "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>방문횟수</td><td>" + visitCount + "</td>"
				+ "</tr>"
				+ "</table></body></html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}
