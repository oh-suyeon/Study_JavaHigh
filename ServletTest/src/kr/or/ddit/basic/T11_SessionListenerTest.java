package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("T11_SessionListenerTest") 어노테이션을 이용해서 간단하게 등록할수 있다. 3.0부터 지원
public class T11_SessionListenerTest extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// HttpSession 객체 생성 및 소멸 시키기 
		HttpSession session = req.getSession(); // 세션 생성
		
		session.setAttribute("ATTR1", "속성1"); // 추가 
		session.setAttribute("ATTR1", "속성11"); //변경
		session.setAttribute("ATTR2", "속성2");
		session.removeAttribute("ATTR1"); //제거
		
		// Http 세션 영역 내에 HttpSessionBindingListener 구현한 객체가 바인딩되면 호출됨.
		MySessionBindingListener bindingListener = new MySessionBindingListener();
		
		session.setAttribute("obj", bindingListener);
		session.removeAttribute("obj");
		
		session.invalidate();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
