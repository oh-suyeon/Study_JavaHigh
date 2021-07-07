package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {

	public void start() throws IOException {
		
		DatagramSocket socket = new DatagramSocket(); // 소켓 생성
		InetAddress serverAddress = InetAddress.getByName("192.168.42.149"); // IP주소를 InetAddress 객체로 줘야 한다.
		
		//  데이턱 저장될 공간으로 byte 배열을 생성한다. (패킷 수신용)
		byte[] msg = new byte[100]; // 최대 100바이트 받을 수 있다.
		
		DatagramPacket outPacket = new DatagramPacket(msg, 1, serverAddress, 8888);
		
		DatagramPacket inPacket = new DatagramPacket(msg, msg.length);
		
		socket.send(outPacket);  // 전송
		socket.receive(inPacket);// 수신 -- 받을 때까지 블락된다. 
		
		System.out.println("현재 서버 시간 => " + new String(inPacket.getData())); // 바이트 데이터를 문자열로 만드는 getData()
		
		socket.close(); // 소켓 종료
		
	}
	
	public static void main(String[] args) throws IOException {
		new UdpClient().start();
	}
	
}
