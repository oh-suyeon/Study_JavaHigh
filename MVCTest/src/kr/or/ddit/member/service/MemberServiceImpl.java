package kr.or.ddit.member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil;
import kr.or.ddit.util.JDBCUtil3;

public class MemberServiceImpl implements IMemberService {

	private IMemberDao memDao;
	
	public MemberServiceImpl() {
		memDao = new MemberDaoImpl();
	}
	
	@Override
	public int insertMember(MemberVO mv) {
		
		Connection conn = JDBCUtil3.getConnection();
		
		int cnt = 0;
		
		try {
			cnt = memDao.insertMember(conn, mv);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {

		Connection conn = JDBCUtil3.getConnection();
		
		int cnt = 0;
		
		try {
			cnt = memDao.deleteMember(conn, memId);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv) {
		
		Connection conn = JDBCUtil3.getConnection();
		
		int cnt = 0;
		
		try {
			cnt = memDao.updateMember(conn, mv);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		Connection conn = JDBCUtil3.getConnection();
		
		try {
			memList = memDao.getMemberList(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		
		return memList;
	}

	@Override
	public boolean checkMember(String memId) {
		boolean chk = false;
		
		Connection conn = JDBCUtil3.getConnection();
		
		try {
			chk = memDao.getMember(conn, memId);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		return chk;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		
		Connection conn = JDBCUtil.getConnection();
		
		List<MemberVO> memList = new ArrayList<>();
		
		try {
			memList = memDao.getSearchMember(conn, mv);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		
		return memList;
	}
}
