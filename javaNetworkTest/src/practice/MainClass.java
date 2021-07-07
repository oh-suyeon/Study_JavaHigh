package practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainClass {
	
	public static void main(String[] args) {
		// 네트워크 관련한 것들은 예외처리 필요하기 때문에 변수만 선언 해놓고 초기화 하지 않겠다.
		ServerSocket serverSocket = null; 
		Socket socket = null;
		
		InputStream ips = null;
		DataInputStream dips = null;
		
		OutputStream ops = null;
		DataOutputStream dops = null;
		
		try {
			
			serverSocket = new ServerSocket(9000); 
			// 서버 소켓 만들어짐
			System.out.println("클라이언트 맞을 준비됐당");
			
			socket = serverSocket.accept(); 
			
			// http://localhost:9000/ 브라우저로 치고 들어가면 실행된다.
			System.out.println("클라이언트 연결됐당"); 
			System.out.println("socket : " + socket);
			
			ips = socket.getInputStream();
			dips = new DataInputStream(ips);
			
			ops = socket.getOutputStream();
			dops = new DataOutputStream(ops);
			
			while(true) {
				String clientMessage = dips.readUTF();
				System.out.println("clientMessage : " + clientMessage);
				
				dops.writeUTF("메시지 전송 끝!");
				dops.flush();
				
				if(clientMessage.equals("STOP")) break;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally { //자원 반납
			try {
				if(socket != null) {
					socket.close();
				}
				if(serverSocket != null) {
					serverSocket.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
