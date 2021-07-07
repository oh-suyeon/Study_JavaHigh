package kr.or.ddit.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil2 {
	//db.properties 파일 내용으로 DB정보 설정하기
	
	//1. Properties 객체 이용
	static Properties prop; // 객체 변수 선언
	
	static {
		prop = new Properties(); // 객체 생성
		
		try {
			FileInputStream fis = new FileInputStream("res/db.properties");
			prop.load(fis);
			Class.forName(prop.getProperty("driver"));
			
		} catch (IOException e) {
			System.out.println("파일이 없거나 입출력 오류입니다.");
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection( prop.getProperty("url"), 
												prop.getProperty("username"), 
												prop.getProperty("password") );
			
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
