package practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MultiChatClient {
	
	Scanner scan = new Scanner(System.in);
	private String nickName; 
	
	public void clientStart() {
		System.out.println("대화명 : ");
		nickName = scan.next();
		
		Socket socket = null;
		
		try {
			String serverIp= "localhost";
			socket = new Socket(serverIp, 7777);
			System.out.println("서버에 연결되었습니다.");
			
			ClientSender sender = new ClientSender(socket, nickName);
					
			ClientReceiver receiver = new ClientReceiver(socket);
			
			sender.start();
			receiver.start();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
				if(dos != null) {
					dos.writeUTF(name);
				}
				while(dos != null) {
					System.out.println("1.전체 2.귓속말 0.종료");
					int choice = scan.nextInt();
					scan.nextLine();
					
					switch(choice) {
						case 1: dos.writeUTF("all"); 
								break; 
						case 2: System.out.println("대화명 >"); // 검사하는 프로토콜을 따로 만들어야 함. 
								dos.writeUTF(scan.nextLine()); 
								break;
						case 0: System.out.println("종료되었습니다.");
								System.exit(0);
						default: break;
					}
					
					System.out.println("메시지 >");
					dos.writeUTF(scan.nextLine());
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	class ClientReceiver extends Thread {
		
		private DataInputStream dis;
		
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
