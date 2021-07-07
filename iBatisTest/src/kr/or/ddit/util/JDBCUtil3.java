package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class JDBCUtil3 {
	// ResourceBundle 객체 이용하기
	static ResourceBundle bundle;
	
	static {
		bundle = ResourceBundle.getBundle("db");
		
		try {
			Class.forName(bundle.getString("driver"));
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패!");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection( bundle.getString("url"), 
												bundle.getString("username"), 
												bundle.getString("password") );
			
		}catch (SQLException e) {     
			System.out.println("DB 연결 실패!");
			e.printStackTrace();
			return null;
		}
	}
	
	public static void disConnect(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		if(rs != null) try {rs.close();} catch(SQLException e) {};
		if(stmt != null) try {stmt.close();} catch(SQLException e) {};
		if(pstmt != null) try {pstmt.close();} catch(SQLException e) {};
		if(conn != null) try {conn.close();} catch(SQLException e) {};
	}
		
	
	
}
