package kr.or.ddit.udp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpFileReceiver {
	
	public static void main(String[] args) throws IOException {
		
		int port = 8888;

		long fileSize = 0;
		long totalReadBytes = 0;
		
		byte[] buffer = new byte[1000];
		int readBytes = 0;
		
		System.out.println("파일 수신 대기중...");	// 먼저 시작해야 함.
		
		DatagramSocket socket = new DatagramSocket(port);
		
		FileOutputStream fos = new FileOutputStream("d:/D_Other/bo_left.png");
		
		DatagramPacket dp = new DatagramPacket(buffer, buffer.length); // 보낼 용도면 ip주소랑 포트번호도 있어야 함.
		
		socket.receive(dp);
		String str = new String(dp.getData()).trim();	// 처음에 받을 건 'start' 문자열 String
		
		if(str.equals("start")) { // sender에서 전송을 시작한경우... // start가 맞는지 확인한다. 
			dp = new DatagramPacket(buffer, buffer.length);
			socket.receive(dp); // 파일 사이즈가 온다. long
			str = new String(dp.getData()).trim();
			fileSize = Long.parseLong(str);
			
			double startTime = System.currentTimeMillis();
			
			while(true) {
				socket.receive(dp);
				readBytes = dp.getLength(); // 무한루프 돌면서 실제 파일 데이터 받기
				fos.write(dp.getData(), 0, readBytes); // 받은 데이터는 파일로 output
				totalReadBytes += readBytes;
				
				System.out.println("진행 상태 : " + totalReadBytes 
						+ "/" + fileSize + "Bytes ("
						+ (totalReadBytes * 100 / fileSize)
						+ " %)");
				
				if(totalReadBytes >= fileSize) { // 다 읽으면 빠져나감
					break;
				}
			}
			
			double endTime = System.currentTimeMillis();
			double diffTime = (endTime - startTime) / 1000;
			double transferSpeed = (fileSize / 1000) / diffTime;
			
			System.out.println("걸린 시간 : " + diffTime + "(초)");
			System.out.println("평균 전송 속도 : " + transferSpeed + "KB/s");
			
			System.out.println("수신 완료...");
			
			fos.close();
			socket.close();
			
		}else {
			System.out.println("비정상 데이터 발견!!!");
			fos.close();
			socket.close();
		}
		
	}
	
}
