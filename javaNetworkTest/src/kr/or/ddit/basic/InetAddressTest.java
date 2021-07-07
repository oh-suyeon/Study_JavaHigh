package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
	
	public static void main(String[] args) throws UnknownHostException {
		// naver 사이트의 IP 가져오기
		InetAddress naverIp = InetAddress.getByName("www.naver.com");
		System.out.println("Host Name => " + naverIp.getHostName());
		System.out.println("Host Address => " + naverIp.getHostAddress());
		System.out.println();
		
		// 자기 자신 컴퓨터 IP 가져오기
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("내 컴퓨터의 Host Name => " + localIp.getHostName());
		System.out.println("내 컴퓨터의 Host Address => " + localIp.getHostAddress());
		System.out.println();
		
		// ip 주소가 여러개인 호스트의 정보 가져오기
		InetAddress[] naverIps = InetAddress.getAllByName("www.naver.com");
		for(InetAddress nIp : naverIps) {
			System.out.println(nIp.toString());
		}
	}
} 
