package kr.or.ddit.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class TcpFileClient {

// 클라이언트는 서버에 접속하여 서버가 보내주는 파일을 D:/C_Lib폴더에 저장한다. 
	private Socket socket;
	private InputStream in;
	private FileOutputStream fos;
	
	//클라이언트 시작
	public void clientStart() {
		
		File file = new File("d:/C_Lib/bo_left.png");
		
		try {
			
			socket = new Socket("192.168.42.149", 7777);
			System.out.println("파일 다운로드 시작...");
			
			fos = new FileOutputStream(file);
			in = socket.getInputStream();
			
			BufferedInputStream bis = new BufferedInputStream(in);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			
			int c = 0;
			while((c = bis.read()) != -1) {
				bos.write(c);
			}
			
			bis.close();
			bos.close(); 
			
			System.out.println("파일 다운로드 완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {in.close();} catch (Exception e) {}
			}
			if(fos != null) {
				try {fos.close();} catch (Exception e) {}
			}
			if(socket != null) {
				try {socket.close();} catch (Exception e) {}
			}
		}
		
	}
	
	public static void main(String[] args) {
		new TcpFileClient().clientStart();
	}
	
	
	
}
