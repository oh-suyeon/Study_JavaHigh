package common.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import common.vo.AtchFileVO;

public class AtchFileDaoImpl implements IAtchFileDao{

	private static IAtchFileDao dao;
	
	private AtchFileDaoImpl() {}
	
	public static IAtchFileDao getInstance() {
		if(dao == null) {
			dao = new AtchFileDaoImpl();
		}
		return dao;
	}
	
	@Override
	public long insertAtchFile(SqlMapClient smc, AtchFileVO atchFileVO) throws SQLException {
		long cnt = (long) smc.insert("atchFile.insertAtchFile", atchFileVO);
		return cnt;
	}

	@Override
	public long insertAtchFileDetail(SqlMapClient smc, AtchFileVO atchFileVO) throws SQLException {
		int cnt = 0;
		
		Object obj = smc.insert("atchFile.insertAtchFileDetail", atchFileVO);
		
		if(obj ==null) {
			cnt = 1;
		}
		return cnt;
	}

	@Override
	public List<AtchFileVO> getAtchFileList(SqlMapClient smc, AtchFileVO atchFileVO) throws SQLException {
		List<AtchFileVO> atchFileList = smc.queryForList("atchFile.getAtchFileList", atchFileVO);
		return atchFileList;
	}

	@Override
	public AtchFileVO getAtchFileDetail(SqlMapClient smc, AtchFileVO atchFileVO) throws SQLException {
		AtchFileVO fileVO = (AtchFileVO) smc.queryForObject("atchFile.getAtchFileDetail", atchFileVO);
		return fileVO;
	}

}
