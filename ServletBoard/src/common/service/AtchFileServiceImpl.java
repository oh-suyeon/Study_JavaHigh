package common.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;

import com.ibatis.sqlmap.client.SqlMapClient;

import common.dao.AtchFileDaoImpl;
import common.dao.IAtchFileDao;
import common.filter.FileUploadRequestWrapper;
import common.vo.AtchFileVO;
import util.SqlMapClientFactory;

public class AtchFileServiceImpl implements IAtchFileService{

	private static IAtchFileService fileService;
	private IAtchFileDao fileDao;
	private SqlMapClient smc;
	
	private AtchFileServiceImpl() {
		fileDao = AtchFileDaoImpl.getInstance();
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IAtchFileService getInstance() {
		if(fileService == null) {
			fileService = new AtchFileServiceImpl();
		}
		return fileService;
	}
	
	@Override
	public AtchFileVO saveAtchFileList(Map<String, Object> fileItemMap) throws Exception {
		
		// 파일 기본 정보 저장하기
		AtchFileVO atchFileVO = new AtchFileVO();
		fileDao.insertAtchFile(smc, atchFileVO);
		
		for(Object obj : fileItemMap.values()) {
			
			FileItem item = (FileItem) obj;
			
			File uploadDir = new File(FileUploadRequestWrapper.UPLOAD_DIRECTORY);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			// 전체 경로 중 파일 명만 추출하기 
			String originFileName = new File(item.getName()).getName();
			long fileSize = item.getSize();
			String storeFileName = "";
			String filePath = "";
			File storeFile = null;
			
			do {
				
				storeFileName = UUID.randomUUID().toString().replace("-", "");
				filePath = FileUploadRequestWrapper.UPLOAD_DIRECTORY + File.separator + storeFileName;
				storeFile = new File(filePath);
				
			}while(storeFile.exists()); // 파일명이 중복되지 않을때까지
	
			// 확장명 추출
			String fileExtension = originFileName.lastIndexOf(".") < 0 ? 
									"" : originFileName.substring(originFileName.lastIndexOf(".") + 1);
			
			item.write(storeFile); // 업로드파일 저장
			
			atchFileVO.setStreFileNm(storeFileName);
			atchFileVO.setFileSize(fileSize);
			atchFileVO.setOrignlFileNm(originFileName);
			atchFileVO.setFileStreCours(filePath);
			atchFileVO.setFileExtsn(fileExtension);
			
			// 파일 세부 정보 저장
			long cnt = fileDao.insertAtchFileDetail(smc, atchFileVO);
			
			item.delete(); // 임시 업로드 파일 삭제하기
		}
		
		return atchFileVO;
	}

	@Override
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO) throws Exception {
		return fileDao.getAtchFileList(smc, atchFileVO);
	}

	@Override
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO) throws Exception {
		return fileDao.getAtchFileDetail(smc, atchFileVO);
	}

	
	
}
