package kr.or.ddit.basic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Logger;

import kr.or.ddit.util.JDBCUtil3;
import kr.or.ddit.util.ScanUtil;

/*
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128),    -- 주소
    CONSTRAINT MYMEMBER_PK PRIMARY KEY (mem_id)
);
*/
public class T01_MemberInfoTest {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//Log4j를 이용한 로그를 남기기 위한 로거 생성 
	private static final Logger SQL_LOGGER = Logger.getLogger("log4jexam.sql.Query");
	private static final Logger PARAM_LOGGER = Logger.getLogger("log4jexam.sql.Parameter");
	private static final Logger RESULT_LOGGER = Logger.getLogger(T01_MemberInfoTest.class.getName());
	
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴를 출력하는 메서드
	 */

	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = ScanUtil.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
					break;
				case 2 :  // 자료 삭제
					deleteMember();
					break;
				case 3 :  // 자료 수정
					updateMember();
					break;
				case 4 :  // 전체 자료 출력
					displayMember();
					break;
				case 5 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=5);
	}
	
	
	private void deleteMember() {
		boolean chk = false;
		String memId;
		
		do {
			
			System.out.println();
			System.out.println("수정할 회원 정보 입력");
			System.out.print("회원 ID >> ");
			memId = scan.next();
			
			chk = checkMember(memId);
			
			if(chk == false) {
				System.out.println(memId + "는 존재하지 않는 아이디입니다.");
				System.out.println("다시 입력하세요.");
			}
			
		}while(chk == false);
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ? ";
			
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + "회원을 삭제했습니다.");
			} else {
				System.out.println(memId + "회원을 삭제하지 못했습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		}
	}
	

	private void updateMember() {
		boolean chk = false;
		String memId;
		
		do {
			
			System.out.println();
			System.out.println("수정할 회원 정보 입력");
			System.out.print("회원 ID >> ");
			memId = scan.next();
			
			chk = checkMember(memId);
			
			if(chk == false) {
				System.out.println(memId + "는 존재하지 않는 아이디입니다.");
				System.out.println("다시 입력하세요.");
			}
			
		}while(chk == false);
		
		System.out.print("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.print("회원 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine(); // 쓰레기값 제거
		
		System.out.print("회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		try {
			conn = JDBCUtil3.getConnection();

			String sql = "UPDATE MYMEMBER "
						+ "SET MEM_NAME = ? "
						+ ", MEM_TEL = ? "
						+ ", MEM_ADDR = ? "
						+ "WHERE MEM_ID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + "회원 정보를 수정했습니다.");
			} else {
				System.out.println(memId + "회원 정보를 수정하지 못했습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		}
		
	}

	
	private void displayMember() {
		System.out.println();
		System.out.println("-----------------------------------------------------------");
		System.out.println(" ID \t이   름 \t전화번호 \t주   소");
		System.out.println("-----------------------------------------------------------");
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = "SELECT * FROM MYMEMBER";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String memId = rs.getString("MEM_ID");
				String memName = rs.getString("MEM_NAME");
				String memTel = rs.getString("MEM_TEL");
				String memAddr = rs.getString("MEM_ADDR");
				System.out.println(" " + memId + "\t" + memName + "\t" + memTel + "\t" + memAddr);
			}
			System.out.println("-----------------------------------------------------------");
			System.out.println("출력 끝.");
			
		}catch (SQLException e) {
			System.out.println("회원 자료 가져오기 실패!");
			e.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		}
	}

	
	private void insertMember() {
		boolean chk = false;
		String memId;
		
		do {
			System.out.println();
			System.out.println("추가할 회원 정보 입력");
			System.out.print("회원 ID >> ");
			memId = scan.next();
			
			chk = checkMember(memId);
			
			if(chk == true) {
				System.out.println(memId + "는 이미 존재하는 아이디입니다.");
				System.out.println("다시 입력하세요.");
			}
			
		}while(chk == true);
		
		System.out.print("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.print("회원 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine(); // 쓰레기값 제거

		System.out.print("회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = "INSERT INTO MYMEMBER (MEM_ID, MEM_NAME, MEM_TEL, MEM_ADDR) VALUES (?, ?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			SQL_LOGGER.info("쿼리 : " + sql);
			PARAM_LOGGER.info("파라미터 : (" + memId 
					+ ", " + memName
					+ ", " + memTel 
					+ ", " + memAddr + ")");
			RESULT_LOGGER.info("결과 : " + cnt);
			
			if(cnt > 0) {
				System.out.println(memId + "회원 추가 작업 성공!");
			} else {
				System.out.println(memId + "회원 추가 작업 실패!");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		}
		
	}

	
	// 회원 Id를 이용하여 회원이 있는지 알려주는메서드
	// param = memId 체크할회원 ID
	// return = 존재하면 true, 아니면 false
	private boolean checkMember(String memId) {
		
		boolean check = false;
		
		try {
			
			conn = JDBCUtil3.getConnection();
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
			
		}catch (SQLException e) {
			e.printStackTrace();
			check = false;
		}finally {
			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		}
		return check;
	}

	
	public static void main(String[] args) {
		T01_MemberInfoTest memObj = new T01_MemberInfoTest();
		memObj.start();
	}

}






