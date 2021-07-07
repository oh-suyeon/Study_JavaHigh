package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class MutipartParser implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc) throws IOException, ServletException {
		
		try {
			
			// 요청 래퍼 객체 생성 (원래의 req를 넣는게 아니라 우리가 구현한 기능이 더해진 reqWrapper를 넣는다. )
			FileUploadRequestWrapper reqWrapper = new FileUploadRequestWrapper((HttpServletRequest)req);
			
			// 래퍼 객체 적용
			fc.doFilter(reqWrapper, resp);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
