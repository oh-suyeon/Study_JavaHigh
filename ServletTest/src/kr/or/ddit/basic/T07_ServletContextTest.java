package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T07_ServletContextTest extends HttpServlet {
	
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		config.getServletContext();
//		super.init(config);
//	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ServletContext ctx = req.getServletContext();
		
//		ctx = this.getServletContext();
		
		System.out.println("서블릿 컨텍스트의 기본 경로 : " + ctx.getContextPath()); // 자주 사용
		System.out.println("서버 정보 : " + ctx.getServerInfo());
		System.out.println("서블릿 API의 메이저 버전 정보 : " + ctx.getMajorVersion());
		System.out.println("서블릿 API의 마이너 버전 정보 : " + ctx.getMinorVersion());
		System.out.println("배포 기술자에 기술된 컨텍스트명 : " + ctx.getServletContextName());
		System.out.println("리소스 경로 목록 : " + ctx.getResourcePaths("/"));
		System.out.println("모든 등록된 서블릿 목록 맵 : " + ctx.getServletRegistrations().toString());
		System.out.println("파일에 대한 MIME 타입 정보 : " + ctx.getMimeType("a.mp3"));
		System.out.println("파일시스템 상의 실제 경로 : " + ctx.getRealPath("/"));
		System.out.println("리소스의 URL 정보 : " + ctx.getResource("/index.html"));
		
		// 속성값 설정
		ctx.setAttribute("attr1", "속성1");
		
		// 속성값 변경
		ctx.setAttribute("attr1", "속성1");

		// 속성값 가져오기;
		System.out.println("attr1의 속성값 : " + ctx.getAttribute("attr1"));
		
		// 속성값 지우기
		ctx.removeAttribute("attr1");
		
		// 로깅 작업하기
		ctx.log("서블릿 컨텍스트를 이용한 로깅 작업 중 입니다.");
		
		// 포워딩 처리
		ctx.getRequestDispatcher("/T06_ServletSessionTest").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
