package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * SqlMapClient 객체를 제공하는 팩토리 클래스
 * @author PC-08
 */
public class SqlMapClientFactory {
	private static SqlMapClient smc; // SqlMapClient 객체 변수 선언
	
	public static SqlMapClient getInstance() {
		if(smc == null) {
			try {
				Charset charset = Charset.forName("UTF-8");
				Resources.setCharset(charset);
				
				Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
				
				smc = SqlMapClientBuilder.buildSqlMapClient(rd);
				
				rd.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return smc;
	}
}
