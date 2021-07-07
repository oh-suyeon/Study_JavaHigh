package common.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import common.vo.AtchFileVO;

public interface IAtchFileDao {
	
	/**
	 * 첨부 파일 저장
	 * @param atchFileVO
	 * @return
	 * @throws SQLException
	 */
	public long insertAtchFile(SqlMapClient smc, AtchFileVO atchFileVO) throws SQLException;
	
	/**
	 * 첨부 파일 세부 정보 저장
	 * @param atchFileVO
	 * @return
	 * @throws SQLException
	 */
	public long insertAtchFileDetail(SqlMapClient smc, AtchFileVO atchFileVO) throws SQLException;
	
	/**
	 * 첨부 파일 목록 조회
	 * @param atchFileVO
	 * @return
	 * @throws SQLException
	 */
	public List<AtchFileVO> getAtchFileList(SqlMapClient smc, AtchFileVO atchFileVO) throws SQLException;
	
	/**
	 * 첨부 파일 세부 정보 조회 메서드
	 * @param atchFileVO
	 * @return
	 * @throws SQLException
	 */
	public AtchFileVO getAtchFileDetail(SqlMapClient smc, AtchFileVO atchFileVO) throws SQLException;
}
