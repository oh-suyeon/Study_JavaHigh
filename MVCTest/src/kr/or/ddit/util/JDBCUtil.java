package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 완료!");
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!");
			e.printStackTrace();
		}
	}
	
	
	// DB 연결 Connection 객체
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "djs02061", "java");
			
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
