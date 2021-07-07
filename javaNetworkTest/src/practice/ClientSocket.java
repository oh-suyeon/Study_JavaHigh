package practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {
	
	public static void main(String[] args) {
		
		Socket socket= null;
		
		OutputStream ops = null;
		DataOutputStream dops = null;
		
		InputStream ips = null;
		DataInputStream dips = null;				
		
		Scanner sc = null;
		
		try {
			
			socket = new Socket("localhost", 9000); // 어디로? 내pc의 ip주소. 127.0.0.1 == localhost 도메인 주소, 포드번호
			System.out.println("서버 연결!");
			
			ops = socket.getOutputStream();
			dops = new DataOutputStream(ops);
			
			ips = socket.getInputStream();
			dips = new DataInputStream(ips);
			
			sc = new Scanner(System.in);
			
			while(true) {
				System.out.println("메시지 입력!");
				String outMessage = sc.nextLine();
				dops.writeUTF(outMessage);
				dops.flush();
				
				String inMessage = dips.readUTF();
				System.out.println("inMessage : " + inMessage);
				
				if(outMessage.equals("STOP")) break;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(socket != null) {
					socket.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
}
