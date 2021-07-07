package kr.or.ddit.basic;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class T08_ServletFilterTest implements Filter{

	@Override
	public void destroy() {
		// 필터가 사라지는 시점 전에 반드시 해야 하는일이 있다면 여기에. 
		
		// 필터 객체가 웹컨테이너에 의해 서비스로부터 제거되기 전에 호출.
		System.out.println("T08_ServletFilterTest=> destroy() 호출됨.");
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc) throws IOException, ServletException {
		// 실제 필터링 작업 
		
		System.out.println("T08_ServletFilterTest => doFilter() 시작...");
		System.out.println(
				"접속 IP : " + req.getRemoteAddr() 
				+ "\n포트번호 : " + req.getRemotePort()
				+ "\n현재시간 : " + new Date().toString()
				);
		// 필터체인을 실행한다. (req, resp 객체 전달)
		fc.doFilter(req, resp); //반드시 호출되어야 함
		System.out.println("T08_ServletFilterTest => doFilter() 끝...");
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
		System.out.println("T08_ServletFilterTest => init() 호출됨."); // 사용자 요청이 없어도 서버가 켜지면 만들어진다. (반대로 서블릿은 요청이 있어야 만들어지는 lazy 로딩)
		// 초기화 작업 필요하면 여기서	
		
		// 초기화 파라미터 정보 가져오기
		String initParam = fc.getInitParameter("init-param");
		System.out.println("init-param : " + initParam);
		
	}
	
	
}
