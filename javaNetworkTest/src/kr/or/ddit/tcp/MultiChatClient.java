package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MultiChatClient {
	
	Scanner scan = new Scanner(System.in);
	private String nickName; // 대화명
	
	// 시작 메서드
	public void clientStart() {
		// 대화명 입력받기
		System.out.println("대화명 : ");
		nickName = scan.next();
		
		Socket socket = null;
		
		try {
			String serverIp= "localhost";
			socket = new Socket(serverIp, 7777);
			System.out.println("서버에 연결되었습니다.");
			
			// 송신용 스레드 생성
			ClientSender sender = new ClientSender(socket, nickName);
					
			// 수신용 스레드 생성
			ClientReceiver receiver = new ClientReceiver(socket);
			
			sender.start();
			receiver.start();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 메시지를 전송하는 Thread 
	class ClientSender extends Thread {
		private DataOutputStream dos;
		private String name;
		private Scanner scan = new Scanner(System.in);
		
		public ClientSender(Socket socket, String name) {
			this.name = name;
			try {
				dos = new DataOutputStream(socket.getOutputStream());
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try {
				// 시작하자 마자 자신의 대화명을 서버로 전송
				if(dos != null) {
					dos.writeUTF(name);
				}
				while(dos != null) {
					// 키보드로 입력받은 메시지를 서버로 전송
					dos.writeUTF(scan.nextLine());
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// 수신용 Thread 클래스 정의
	class ClientReceiver extends Thread {
		
		private DataInputStream dis;
		
		// 생성자
		public ClientReceiver(Socket socket) {
			try {
				dis = new DataInputStream(socket.getInputStream());
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(dis != null) {
				try {
					// 서버로부터 수신한 메시지 출력하기
					System.out.println(dis.readUTF());
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		new MultiChatClient().clientStart();
		
	}
	
	
	
	
	
}
