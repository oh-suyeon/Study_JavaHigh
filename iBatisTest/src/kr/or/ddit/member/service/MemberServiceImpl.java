package kr.or.ddit.member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil;
import kr.or.ddit.util.JDBCUtil3;
import kr.or.ddit.util.SqlMapClientFactory;

public class MemberServiceImpl implements IMemberService {

	private static IMemberService memService;
	
	public static IMemberService getInstance() {
		if(memService == null) {
			memService = new MemberServiceImpl();
		}
		return memService;
	}
	
	private IMemberDao memDao;

	private SqlMapClient smc;
	
	private MemberServiceImpl() {
		memDao = MemberDaoImpl.getInstance();
		smc = SqlMapClientFactory.getInstance();
	}
	
	@Override
	public int insertMember(MemberVO mv) {
		
		int cnt = 0;
		
		try {
			cnt = memDao.insertMember(smc, mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {

		int cnt = 0;
		
		try {
			cnt = memDao.deleteMember(smc, memId);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv) {
		
		int cnt = 0;
		
		try {
			cnt = memDao.updateMember(smc, mv);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} 
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			memList = memDao.getMemberList(smc);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} 
		
		return memList;
	}

	@Override
	public boolean checkMember(String memId) {

		boolean chk = false;
		
		try {
			chk = memDao.getMember(smc, memId);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return chk;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		
		List<MemberVO> memList = new ArrayList<>();
		
		try {
			memList = memDao.getSearchMember(smc, mv);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} 
		
		return memList;
	}
}
