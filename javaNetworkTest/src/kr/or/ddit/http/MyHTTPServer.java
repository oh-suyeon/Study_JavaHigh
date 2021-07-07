package kr.or.ddit.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 간단한 웹서버 예제
 * @author macween
 *
 */
public class MyHTTPServer {

	private final int port = 80;
	private final String encoding = "UTF-8";

	/**
	 * 응답헤더 생성하기
	 * @param contentLength 응답내용크기
	 * @param mimeType 마임타입
	 * @return 바이트배열
	 */
	private byte[] makeResponseHeader(int contentLength, String mimeType) {
		String header = "HTTP/1.1 200 OK\r\n" // 윈도우는 "\r\n"으로 줄바꿈을 사용, 리눅스는 "\n" .. 운영체제마다다르다... 모르면 그냥 "\r\n"으로 쓰기
			+ "Server: MyHTTPServer 1.0\r\n"
			+ "Content-length: " + contentLength + "\r\n"
			+ "Content-type: " + mimeType + "; charset=" + this.encoding + "\r\n\r\n";
		return header.getBytes(); // 스트림으로 쏴줘야 하니까 바이트 배열로 만들어줬다. 
	}

	/**
	 * 응답내용 생성하기
	 * @param filePath 응답으로 사용할 파일경로
	 * @return 바이트배열 데이터
	 */
	private byte[] makeResponseBody(String filePath) {

		FileInputStream fis = null;
		byte[] data = null;
		try {
			File file = new File(filePath);
			data = new byte[(int) file.length()];

			fis = new FileInputStream(file);
			fis.read(data); //읽은 다음에 바이트 배열에 저장

		}catch(IOException ex) {
			System.err.println("입출력 오류!!!");
			ex.printStackTrace();
		}finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return data;
	}

	/**
	 * Http서버 시작
	 */
	public void start() {
		//ExecutorService pool = Executors.newFixedThreadPool(100);
		System.out.println("HTTP 서버가 시작되었습니다.");
		try (ServerSocket server = new ServerSocket(this.port)) {

			while(true) {
				try {
					Socket socket = server.accept();
					//pool.submit(new HttpHandler(socket));
					HttpHandler handler = new HttpHandler(socket);
					new Thread(handler).start(); // 요청처리 시작

				} catch(IOException ex) {
					System.err.println("커넥션 오류!!!");
					ex.printStackTrace();
				}catch(RuntimeException ex) {
					System.err.println("알수없는 오류!!!");
					ex.printStackTrace();
				}
			}
		}catch(IOException ex) {
			System.err.println("서버 시작 오류!!!");
			ex.printStackTrace();
		}
	}

	/**
	 * HTTP 요청 처리를 위한 Runnable 객체
	 * @author macween
	 *
	 */
	private class HttpHandler implements Runnable {
		private final Socket socket;

		public HttpHandler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			OutputStream out = null;
			BufferedReader br = null;
			try {
				out = new BufferedOutputStream(socket.getOutputStream());
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				// 요청헤더 정보 파싱하기
				StringBuilder request = new StringBuilder();
				while (true) {
					String str = br.readLine();

					if (str.equals("")) break; // empty line 체크

					request.append(str + "\n");
				}

				System.out.println("요청헤더:\n" + request.toString());
				System.out.println("-------------------------------------");

				String reqPath = "";

				// 요청 페이지 정보 가져오기
				StringTokenizer st = new StringTokenizer(request.toString());
				while(st.hasMoreTokens()) {
					String token = st.nextToken();
					if(token.startsWith("/")) {
						reqPath = token;
					}
				}

				String fileName = "./WebContent" + reqPath; // 상대경로(프로젝트 폴더 기준) 설정

				// 해당 파일이름을 이용하여	Content-type 정보 추출하기
				String contentType = URLConnection.getFileNameMap().getContentTypeFor(fileName);

				// css파일인 경우 인식이 안되서 추가함.
				if(contentType == null && fileName.endsWith(".css")) contentType = "text/css";

				System.out.println("contentType => " + contentType);

				File file = new File(fileName); 
				if(!file.exists()) {
					makeErrorPage(out, 404, "Not Found");
					return;
				}

				byte[] body = makeResponseBody(fileName);

				byte[] header = makeResponseHeader(body.length, contentType);

				// 요청헤더가 HTTP/1.0 이나 그 이후의 버전을 지원할 경우 MIME 헤더를 전송한다. ==> 그 이전 버전에는 header를 안 보냈다.
				if (request.toString().indexOf("HTTP/") != -1) {
					out.write(header); // 응답헤더 보내기
				}

				System.out.println("응답헤더:\n" + new String(header));
				System.out.println("-------------------------------------");

				out.write(body); // 응답내용 보내기
				out.flush();

			}catch (IOException ex) {
				System.err.println("입출력 오류!!!");

			}finally {
				try {
					socket.close(); // 소켓 닫기(연결 끊기)
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		/**
		 * 에러페이지 생성
		 * @param out
		 * @param statusCode
		 * @param errMsg
		 */
		private void makeErrorPage(OutputStream out, int statusCode, String errMsg) {
			String statusLine = "HTTP/1.1" + " " + statusCode + " " + errMsg;
			try {
				out.write(statusLine.getBytes());
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		MyHTTPServer server = new MyHTTPServer();
		server.start();
	}



}
