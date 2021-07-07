package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {
	
	private DatagramSocket socket;
	
	// 프로그램 시작
	public void start() throws IOException {
		
		// 포트 8888번을 사용하는 소켓을 생성한다. 
		socket = new DatagramSocket(8888);
		
		// 패킷 송수신을 위한 객체변수 선언
		DatagramPacket inPacket, outPacket; // 패킷 안에 넣을 데이터는 바이트 배열이어야 한다. 
		
		byte[] inMsg = new byte[1]; // 패킷 수신을 위한 바이트 배열 (바이트 객체 한개밖에 못 담는 배열 - 내용이 중요한 게 아니라 상대방의 정보를 얻기 위함.)
		byte[] outMsg; 				// 패킷 송신을 위한 바이트 배열
		
		while(true) {
			inPacket = new DatagramPacket(inMsg, inMsg.length);	//사이즈는 1
			
			System.out.println("패킷 수신 대기중...");
			
			// 패킷을 통해 데이터를 수신 (Receive)한다. -- 상대방이 데이터를 던지기 시작할때까지 블락된다. 받은 정보는 inPacket에 담긴다. 
			socket.receive(inPacket);
			
			System.out.println("패킷 수신 완료.");
			
			// 수신한 패킷으로부터 client의 IP주소와 port번호를 얻는다. 
			InetAddress address = inPacket.getAddress();
			int port = inPacket.getPort();
			
			// 서버의 현재 시간을 시분초 형태로 반환한다.
			SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			String time = sdf.format(new Date());
			outMsg = time.getBytes(); // 시간 문자열을 byte배열로 변환
			
			// 패킷을 생성해서 client에게 전송(send)한다.
			outPacket = new DatagramPacket(outMsg, outMsg.length, address, port);
			
			socket.send(outPacket); // 전송 시작
		}
		// 무한 루트라서 소켓을 안 닫는 건가? 그래도 언젠가는 자원 반납해야 하지않나??
	}
	
	public static void main(String[] args) throws IOException {
		new UdpServer().start();
	}
}
