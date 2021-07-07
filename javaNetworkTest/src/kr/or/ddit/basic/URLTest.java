package kr.or.ddit.basic;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class URLTest {
	public static void main(String[] args) throws MalformedURLException, URISyntaxException {
		URL url = new URL("http", "ddit.or.kr", 80, //포트번호
						  "/main/index.html?ttt=123#kkk"); // 실제 위치
		
		System.out.println("전체 URL 주소 : " + url.toString());
		
		System.out.println("protocol : " + url.getProtocol());
		System.out.println("host : " + url.getHost());
		System.out.println("query : " + url.getQuery()); // 필요한 정보를 질의하는 부분...
		System.out.println("file : " + url.getFile()); // 쿼리 정보 포함
		System.out.println("path : " + url.getPath()); // 쿼리 정보 미포함
		System.out.println("port : " + url.getPort());
		System.out.println("ref : " + url.getRef()); // 레퍼런스
		System.out.println();
		
		System.out.println(url.toExternalForm());
		System.out.println(url.toString());
		System.out.println(url.toURI().toString());
		
	}
}
