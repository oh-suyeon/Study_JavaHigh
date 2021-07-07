package kr.or.ddit.member.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil3;

public class MemberDaoImpl implements IMemberDao{

	private static IMemberDao memDao; //  MemberDaoImpl로 해도 되는데 일반적으로 변수 선언할 때는 인터페이스타입으로 많이 한다. 
	
	private MemberDaoImpl() {
	}
	
	public static IMemberDao getInstance() {
		if(memDao == null) {
			memDao = new MemberDaoImpl();
		}
		return memDao;
	}
	
	
	@Override
	public int insertMember(SqlMapClient smc, MemberVO mv) throws SQLException {
		
		int cnt = 0;
		
		Object obj = smc.insert("member.insertMember", mv);
		
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public boolean getMember(SqlMapClient smc, String memId) throws SQLException {
		
		boolean check = false;
		
		int count = (int) smc.queryForObject("member.getMember", memId);
		
		if(count > 0) {
			check = true;
		}
			
		return check;
	}
		

	@Override
	public List<MemberVO> getMemberList(SqlMapClient smc) throws SQLException {
		
		List<MemberVO> memList = smc.queryForList("member.getMemberAll");
		
		return memList;
	}

	@Override
	public int updateMember(SqlMapClient smc, MemberVO mv) throws SQLException {
		
		int cnt = smc.update("member.updateMember", mv);
		
		return cnt;
	}

	@Override
	public int deleteMember(SqlMapClient smc, String memId) throws SQLException {
		
		int cnt = smc.delete("member.deleteMember", memId);
		
		return cnt;
	}

	@Override
	public List<MemberVO> getSearchMember(SqlMapClient smc, MemberVO mv) throws SQLException {
		
		List<MemberVO> memList = smc.queryForList("member.getSearchMemberList", mv);
		
		return memList;
	}
	
}
