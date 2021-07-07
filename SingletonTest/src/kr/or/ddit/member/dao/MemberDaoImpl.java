package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil3;

public class MemberDaoImpl implements IMemberDao{

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
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
	public int insertMember(Connection conn, MemberVO mv) throws SQLException {
		
		String sql = "INSERT INTO MYMEMBER"
					+ " (MEM_ID, MEM_NAME, MEM_TEL, MEM_ADDR)"
					+ " VALUES (?, ?, ?, ?) ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mv.getMemId());
		pstmt.setString(2, mv.getMemName());
		pstmt.setString(3, mv.getMemTel());
		pstmt.setString(4, mv.getMemAddr());
		
		int cnt = pstmt.executeUpdate();
		
		JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
	
		return cnt;
	}

	@Override
	public boolean getMember(Connection conn, String memId) throws SQLException {
		
		boolean check = false;
		
		String sql = "SELECT COUNT(*) CNT FROM MYMEMBER "
				+ "WHERE MEM_ID = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		
		rs = pstmt.executeQuery();
		
		int count = 0;
		
		if(rs.next()) {
			count = rs.getInt("CNT");
		}
		
		if(count > 0) {
			check = true;
		}
			
		JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		
		return check;
	}
		

	@Override
	public List<MemberVO> getMemberList(Connection conn) throws SQLException {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();		
		
		String sql = "SELECT * FROM MYMEMBER";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			MemberVO mv = new MemberVO();
			
			mv.setMemId(rs.getString("MEM_ID"));
			mv.setMemName(rs.getString("MEM_NAME"));
			mv.setMemTel(rs.getString("MEM_TEL"));
			mv.setMemAddr(rs.getString("MEM_ADDR"));
			
			memList.add(mv);
		}

		JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		
		return memList;
	}

	@Override
	public int updateMember(Connection conn, MemberVO mv) throws SQLException {
		

		String sql = "UPDATE MYMEMBER "
					+ "SET MEM_NAME = ? "
					+ ", MEM_TEL = ? "
					+ ", MEM_ADDR = ? "
					+ "WHERE MEM_ID = ? ";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, mv.getMemName());
		pstmt.setString(2, mv.getMemTel());
		pstmt.setString(3, mv.getMemAddr());
		pstmt.setString(4, mv.getMemId());
		
		int cnt = pstmt.executeUpdate();
		
		JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		
		return cnt;
	}

	@Override
	public int deleteMember(Connection conn, String memId) throws SQLException {
		
		String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ? ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		
		int cnt = pstmt.executeUpdate();
		
		JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		
		return cnt;
	}

	@Override
	public List<MemberVO> getSearchMember(Connection conn, MemberVO mv) throws SQLException {
		
		List<MemberVO> memList = new ArrayList<>();
		
		String sql = "SELECT * FROM MYMEMBER WHERE 1=1 ";
		
		if(mv.getMemId() != null && !mv.getMemId().equals("")) {
			sql += " AND MEM_ID = ? ";
		}
		if(mv.getMemName() != null && !mv.getMemName().equals("")) {
			sql += " AND MEM_NAME = ? ";
		}
		if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
			sql += " AND MEM_TEL = ? ";
		}
		if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
			sql += " AND MEM_ADDR LIKE '%' || ? || '%' ";
		}
		
		pstmt = conn.prepareStatement(sql);
		
		int index = 1;
		if(mv.getMemId() != null && !mv.getMemId().equals("")) {
			pstmt.setString(index++, mv.getMemId());
		}
		if(mv.getMemName() != null && !mv.getMemName().equals("")) {
			pstmt.setString(index++, mv.getMemName());
		}
		if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
			pstmt.setString(index++, mv.getMemTel());
		}
		if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
			pstmt.setString(index++, mv.getMemAddr());
		}
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			MemberVO mv2 = new MemberVO();
			
			mv2.setMemId(rs.getString("MEM_ID"));
			mv2.setMemName(rs.getString("MEM_NAME"));
			mv2.setMemTel(rs.getString("MEM_TEL"));
			mv2.setMemAddr(rs.getString("MEM_ADDR"));
			
			memList.add(mv2);
		}
		return memList;
	}
	
}
