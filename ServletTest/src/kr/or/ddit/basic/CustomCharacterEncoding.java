package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Filter를 이용한 한글 인코딩 예제
 * @author PC-08
 *
 */

public class CustomCharacterEncoding implements Filter{

	private String encoding; // 인코딩 정보 
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resq, FilterChain fc)
			throws IOException, ServletException {
		
		System.out.println("인코딩 설정 정보 : " + this.encoding);
		req.setCharacterEncoding(this.encoding);
		resq.setContentType("text/html;charset=utf-8");
		
		fc.doFilter(req, resq);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		if(config.getInitParameter("encoding") == null) {
			// 기본 인코딩을 utf-8로 설정 (원래 기본 인코딩은 ISO-8859-1)
			this.encoding = "UTF-8";
		}else {
			this.encoding = config.getInitParameter("encoding");
		}
	}
}
