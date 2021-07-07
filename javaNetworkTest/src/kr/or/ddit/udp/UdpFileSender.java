package kr.or.ddit.udp;

import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpFileSender {
	
	public static void main(String[] args) {
		String serverIp = "127.0.0.1";
		int port = 8888;
		
		File file = new File("d:/C_Lib/bo_left.png");
		
		DatagramSocket socket = null;
		
		if(!file.exists()) {
			System.out.println("파일이존재하지 않습니다");
			System.exit(0);
		}
		
		long fileSize = file.length();
		long totalReadBytes = 0;
		double startTime = 0;
		
		try {
			socket = new DatagramSocket();
			InetAddress serverAddr = InetAddress.getByName(serverIp);
			startTime = System.currentTimeMillis();
			
			String str = "start"; // 전송시작을 알려주기 위한 문자열 (제일 처음 받는 메시지)
			DatagramPacket dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAddr, port);
			
			socket.send(dp);
			
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1000];
			
			str = String.valueOf(fileSize); // 총 파일 사이즈 정보  (두번째 메시지 - 전송하려는 파일 사이즈)
			dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAddr, port);
			socket.send(dp);
			
			while(true) {	// 세번째 메시지 - 실제 파일 데이터)
				Thread.sleep(10); // 패킷 전송간의 시간 간격 주기 위해서...
				int readBytes = fis.read(buffer, 0, buffer.length); // 버퍼 이용할 때 파라미터 3개짜리 아니면 쓰레기 데이터때문에 정상처리가 안되니까. 읽을 사이즈만큼만 처리하도록 한다. 
				if(readBytes == -1) {	// 읽을 게 없으면 -1 반환
					break;
				}
				// 읽어온 파일 내용 패킷에 담기 
				dp = new DatagramPacket(buffer, readBytes, serverAddr, port);
				socket.send(dp);
				
				totalReadBytes += readBytes;
				System.out.println("진행 상태 : " + totalReadBytes 
						+ "/" + fileSize + "Bytes ("
						+ (totalReadBytes * 100 / fileSize)
						+ " %)");
			}
			double endTime = System.currentTimeMillis();
			double diffTime = (endTime - startTime) / 1000;
			double transferSpeed = (fileSize / 1000) / diffTime;
			
			System.out.println("걸린 시간 : " + diffTime + "(초)");
			System.out.println("평균 전송 속도 : " + transferSpeed + "KB/s");
			
			fis.close();
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
