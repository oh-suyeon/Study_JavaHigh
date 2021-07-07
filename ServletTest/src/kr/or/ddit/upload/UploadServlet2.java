package kr.or.ddit.upload;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 자카르타 프로젝트의file-upload 모듈을 이용한 파일업로드 예제
 * @author PC-08
 *
 */

@WebServlet("/upload2")
public class UploadServlet2 extends HttpServlet {

	private static final String UPLOAD_DIRECTOR = "upload_files";
	
	// 메모리 임계크기(이 크기가 넘어가면 레파지토리 위치에 임시파일로 저장됨). 
	// 서버는 여러 클라이언트를 상대하니까 메모리가 중요함. 이 크기가 넘어가면 잠시 파일로 관리한다. 
	// 용량은 바이트 기반. 3메가 바이트까지는 메모리로 처리하고 넘어가면 disk 임시 파일로 저장해서 떨구겠다. 
	// 서블릿 모든 서버는 컴퓨터 메모리에서 작업하고 있다. 여기에 파일 업로드를 하면 메모리를 많이 소모하게 된다. 그래서 적절하게 끊어야 함. 메모리 사용 제약.
	private static final int MEMORY_THRESHOLD = 1024*1024*3;
	
	// 파일 1개당 최대 크기. 제한을 건다. 40메가까지. 1개 단위로는. 
	private static final long MAX_FILE_SIZE = 1024*1024*40;
	
	// 요청 파일 최대 크기. 여러 파일이 왔을 때는 통으로 50메가까지. 30메가짜리 2개 한번에 못 보냄.
	private static final long MAX_REQUEST_SIZE = 1024*1024*50;
	
	
	// 사용 방법이 이렇게 정해져 있으니까 복사 붙여넣기하면 된다. 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// commons에 있는 것 import
		if(ServletFileUpload.isMultipartContent(req)) { // 멀티 파트인 경우.. (멀티 파트가 아닐 수도 있으니까...)
			
			// 폼필드 데이터 저장용  req.getParameter("sender")이렇게는 못 쓰니까. 
			Map<String, String> formMap = new HashMap<String, String>();
			
			// 저장할 곳 만들기
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(MEMORY_THRESHOLD);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir"))); // vm의 환경설정 값. 임시 저장소. 여기를 임시저장공간으로 사용한다. 
			System.out.println(System.getProperty("java.io.tmpdir"));
			
			// 업로드할 파일 객체 만들기
			ServletFileUpload upload = new ServletFileUpload(factory); // 제일중요한 객체. 이거 가지고 파싱하는 것. factory로 설정정보 넣어주고.
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);
			
			// 웹 애플리케이션 루트 디렉토리 기준 업로드 경로 설정하기. ""라고 쓴 건 컨텍스트 루트라는 의미
			// 여기에 파일을 넣을 거
			// "d:/D_Other/" 절대 경로로 해줄수도 있다. 
			// 아래는 서비스 중인 웹 어플리케이션 경로에 저장하겠다는 것. 
			// 애플리케이션을 그대로 카피해 전달해도 첨부 파일도 함께 간다는 장점이 있음. 
			// 사실 일반적이진 않다. 실제경로 얻어내는 거 보여주려고 한 예제
			String uploadPath = getServletContext().getRealPath("") 
								+ File.separator + UPLOAD_DIRECTOR;
			
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			try {
				
				List<FileItem> formItems = upload.parseRequest(req); // 이게 핵심. 이거 만들려고 준비한 거다. 파싱하는 거. 
				// (첨부파일, 폼 값(전송자))아이템 리스트다. 
				// 파싱된 파일아이템을 핸들링
				if(formItems != null && formItems.size() > 0) {
					for(FileItem item : formItems) { 
						if(!item.isFormField()) { 
							// 파일인 경우... item.getName()하면 사용자의 경로 다 가져온다. 여기서 getName()한번더 해서 파일의이름만 떼어오기. 
							String fileName = new File(item.getName()).getName();
							String filePath = uploadPath + File.separator + fileName; 
							// separator 파일 경로 구분자를 구분해준다. File.separator 대신 "/"로 할 순 있는데 이건 하드 코딩이고, 운영체제마다 다르니까. 
							File storeFile = new File(filePath);
							item.write(storeFile); // 업로드 파일 저장
						}else { // 폼 데이터인 경우 
							// 폼필드의 값이 한글인 경우에는 해당 문자열을 적절히 변환해 주어야 한다. getFieldName하면 sender가 들어간다. getString하면 값 홍길동만 꺼내올 수 있다. 
							formMap.put(item.getFieldName(), item.getString("UTF-8"));
						}
					}
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			for(Entry<String, String> entry : formMap.entrySet()){
				System.out.println("파라미터명 : " + entry.getKey());
				System.out.println("파라미터값 : " + entry.getValue());
			}
			resp.setContentType("text/html");
			resp.getWriter().print("업로드 완료됨.");
		}
	}
	
}
