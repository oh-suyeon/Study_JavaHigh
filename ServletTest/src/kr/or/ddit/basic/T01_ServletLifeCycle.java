package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T01_ServletLifeCycle extends HttpServlet {
	@Override
	public void init() throws ServletException {
		// 초기화할 게 있으면 여기다가 초기화 코드 작성
		System.out.println("init() 호출됨");
	}
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 실제적인 작업 수행이 시작되는 지점(자바의 메인메서드 역할)
		// HttpServletRequest 사용자의 요청이 들어있는 객체 
		// HttpServletResponse 요청에 대한 응답이 들어있는 객체   
		// 사용자가 get 방식으로 요청했기 때문에 doGet을 호출해준다.
		System.out.println("service() 호출됨");
		super.service(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 메서드 방식이 GET인 경우 호출됨
		System.out.println("doGet() 호출됨");
		
		throw new ServletException("심각한 에러 발생~~!!");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 메서드 방식이 POST인 경우 호출됨
		System.out.println("doPost() 호출됨");
	}
	
	@Override
	public void destroy() {
		// 객체 소멸 시 (컨테이너로부터 서블릿 객체 제거시) 필요한 코드 작성
		// 서버를 끄고 메모리에 적재되어있던 서블릿을 제거할 때...
		System.out.println("destroy() 호출됨");
		
	}
	
}
