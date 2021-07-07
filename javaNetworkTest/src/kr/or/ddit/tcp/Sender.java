package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 소켓을 통해서 메시지를 보내는 역할 담당
 * @author PC-08
 *
 */

public class Sender extends Thread{
	
	private Scanner scan;
	private String name;
	private DataOutputStream dos;
	
	
	public Sender(Socket socket) {
		name = "[" + socket.getInetAddress() + " : "
				+ socket.getLocalPort() + "]";
		scan = new Scanner(System.in);
		
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(dos != null) {
			try {
				dos.writeUTF(name + " >>> " + scan.nextLine()); // 엔터로 값이 한 줄씩 들어올 때마다 실행된다. 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
