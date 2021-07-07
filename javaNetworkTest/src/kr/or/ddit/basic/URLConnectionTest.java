package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class URLConnectionTest {
	
	public static void main(String[] args) throws IOException {
		//특정 서버(예 : naver 서버)의 정보와 파일 내용을 출력하는 예제
		
		URL url = new URL("https://www.naver.com/index.html");
		
		//Header 정보 가져오기
		
		//URLConnection 객체 구하기
		URLConnection urlConn = url.openConnection();
		
		System.out.println("Content-Type : " + urlConn.getContentType());
		System.out.println("Encoding : " + urlConn.getContentEncoding());
		System.out.println("Content : " + urlConn.getContent());
		System.out.println();
		
		// 전체 Header 정보 출력
		Map<String, List<String>> headerMap = urlConn.getHeaderFields();
		
		// Header의 key 값 구하기
		Iterator<String> iterator = headerMap.keySet().iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			System.out.print(key+ " : " + headerMap.get(key));
		}
		System.out.println("----------------------------------------");
		
		// 해당 호스트의 페이지 내용 가져오기
		
		// 파일을 읽어오기 위한 스트림생성
		InputStream is = url.openConnection().getInputStream();
//		InputStream is = url.openStream();
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		
		int c = 0;
		while((c=isr.read()) != -1) {
			System.out.print((char) c);
		}
		
		// 스트림 닫기
		isr.close();
		
		
		
	}
}
