package common.handler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.service.AtchFileServiceImpl;
import common.service.IAtchFileService;
import common.vo.AtchFileVO;

public class FileDownloadHandler implements CommandHandler {

	private IAtchFileService fileService = AtchFileServiceImpl.getInstance(); 
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		long fileId = req.getParameter("fileId") != null ? 
					Long.parseLong(req.getParameter("fileId")) : -1;
		int fileSn = req.getParameter("fileSn") != null?
					Integer.parseInt(req.getParameter("fileSn")) : 1;
		
		//파일 정보 조회
		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setAtchFileId(fileId);
		atchFileVO.setFileSn(fileSn);
		atchFileVO = fileService.getAtchFileDetail(atchFileVO);
		
		//파일 다운로드 처리를 위한 응답헤더 정보 설정하기
		resp.setContentType("application/octet-stream");
		
		String fileName = URLEncoder.encode(atchFileVO.getOrignlFileNm(), "UTF-8");
		
		// + 문자를 공백으로 바꾸기
		// url에서 쓸 수 없는 값은 %(퍼센트 인코딩)이 된다.
		// 이때 공백은 +으로 인코딩이 된다.
		// 그러면 다운받을 떄 +로 나온다. 보기 안 좋으니까 공백으로 되돌리기
		fileName.replaceAll("\\+", "%20");

		resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(atchFileVO.getFileStreCours()));
		
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		
		int c = 0;
		
		while((c=bis.read()) != -1) {
			bos.write(c);
		}
		bis.close();
		bos.close();
		
		
		return null; // 뷰페이지가 없으니까 null을 넘긴다. 웹 컨트롤러에서 걸러진다.
	}

}
