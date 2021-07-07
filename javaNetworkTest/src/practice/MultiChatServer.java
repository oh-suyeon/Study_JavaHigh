package practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MultiChatServer {
	
	Map<String, Socket> clients;
	
	public MultiChatServer() {
		clients = Collections.synchronizedMap(new HashMap<String, Socket>());
	}
	
	public void serverStart() {
		Socket socket = null;
		
		try(ServerSocket serverSocket = new ServerSocket(7777)) { 
			
			System.out.println("서버가 시작되었습니다.");
			
			while(true) {

				socket = serverSocket.accept(); 
				
				System.out.println("[" + socket.getInetAddress() + " : "
									+ socket.getPort() + "]에서 접속하였습니다.");
				

				ServerReceiver receiver = new ServerReceiver(socket);
				receiver.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	class ServerReceiver extends Thread {
		private Socket socket;
		private DataInputStream dis;
		private String name;
		
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				dis = new DataInputStream(socket.getInputStream());
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			try {
		
				name = dis.readUTF();
				
				sendMessageAll("#" + name + "님이 입장했습니다.");
				
				clients.put(name, socket);
				System.out.println("현재 서버 접속자 수는 " + clients.size() + "명 입니다.");
				
				while(dis != null){
					String to = dis.readUTF();
					String msg = dis.readUTF();
					sendMessage(msg, name, to);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				
			}finally {
				
				sendMessageAll(name + "님이 나가셨습니다.");
				
				clients.remove(name);
				
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "] 에서 접속을 종료했습니다.");
				System.out.println("현재 접속자 수는 " + clients.size() + "명 입니다.");
			}
		}
	}
	
	//메시지 만들고 분류(전체/귓속말)
	public void sendMessage(String msg, String from, String to) {
		
		if(to.equals("all")) {
			sendMessageAll("[" + from + "] " + msg);
		} else {
			sendMessageTo("[ (귓속말) " + from + "] " + msg, from, to);
		}
		
	}

	//전체
	public void sendMessageAll(String msg) {
		Iterator<String> it = clients.keySet().iterator();
		while(it.hasNext()) {
			try {
				String name = it.next(); 
				
				DataOutputStream dos = new DataOutputStream(clients.get(name).getOutputStream());
				dos.writeUTF(msg); 
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//귓속말
	public void sendMessageTo(String msg, String from, String to) {
		try {
			if(clients.get(to) != null) {
				DataOutputStream dos1 = new DataOutputStream(clients.get(from).getOutputStream());
				dos1.writeUTF(msg);
				DataOutputStream dos2 = new DataOutputStream(clients.get(to).getOutputStream());
				dos2.writeUTF(msg);
			} else {
				DataOutputStream dos = new DataOutputStream(clients.get(from).getOutputStream());
				dos.writeUTF("존재하지 않는 대화명입니다.");
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		
		new MultiChatServer().serverStart();
		
	}
}
