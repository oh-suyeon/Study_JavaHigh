package assignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import kr.or.ddit.util.JDBCUtil3;
import kr.or.ddit.util.ScanUtil;

public class JDBC_board {
	
	SimpleDateFormat dateForm = new SimpleDateFormat("yy-MM-dd");
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public static void main(String[] args) {
		JDBC_board jb = new JDBC_board();
		jb.start();
	}
	
	public void start(){
		do{
			int choice = displayBoard(); //메뉴 출력
			switch(choice){
				case 1 :  // 글 보기
					System.out.println(" 조회할 글 번호 >>");
					int no = ScanUtil.nextInt();
					read(no);
					break;
				case 2 :  // 글 등록
					insert();
					break;
				case 3 :  // 검색
					search();
					break;
				case 0 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					System.exit(0);
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(true);
	}

	private void search() {
		System.out.println("작성자>> ");
		String userId = ScanUtil.nextLine();
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = "SELECT * FROM JDBC_BOARDING WHERE BOARD_WRITER = ? ORDER BY BOARD_NO DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.print(rs.getInt("BOARD_NO") + "\t" + 
								 rs.getString("BOARD_TITLE") + "\t\t" + 
								 rs.getString("BOARD_WRITER") + "\t" + 
								 dateForm.format(rs.getObject("BOARD_DATE")) + "\n" );
			}
			
		}catch (SQLException e) {
			System.out.println("게시글 가져오기 실패!");
			e.printStackTrace();
			
		}finally {
			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		}
	}

	private void insert() {
		System.out.println("제목>> ");
		String title = ScanUtil.nextLine();
		System.out.println("작성자>> ");
		String userId = ScanUtil.nextLine();
		System.out.println("내용>> ");
		String content = ScanUtil.nextLine();
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = "INSERT INTO JDBC_BOARDING (BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT) "
					+ " VALUES (BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, userId);
			pstmt.setString(3, content);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(title + " 게시글 등록 완료");
			} else {
				System.out.println(title + " 게시글 등록 실패");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		}
		
	}

	private void read(int no) {
		System.out.println("========================================");
		System.out.print   ("번호\t제목\t\t작성자\t작성일\n");
		System.out.println("----------------------------------------");
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = "SELECT * FROM JDBC_BOARDING WHERE BOARD_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.print(rs.getInt("BOARD_NO") + "\t" + 
						 rs.getString("BOARD_TITLE") + "\t\t" + 
						 rs.getString("BOARD_WRITER") + "\t" + 
						 dateForm.format(rs.getObject("BOARD_DATE")) + "\n" );
				System.out.println("----------------------------------------");
				System.out.println(rs.getString("BOARD_CONTENT"));
			}
			
		}catch (SQLException e) {
			System.out.println("게시글 가져오기 실패!");
			e.printStackTrace();
			
		}finally {
			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		}
	
		System.out.println("----------------------------------------");
		System.out.println(" 1.수정\t2.삭제\t0.메인");
		System.out.println("========================================");
		
		int input = ScanUtil.nextInt();
		switch(input){
		case 1 : 
			update(no);
			read(no);
			break;
		case 2 :
			delete(no); 
			break;
		case 0 : 
			break;
		}
		
	}

	private void delete(int no) {
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "DELETE FROM JDBC_BOARDING WHERE BOARD_NO = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(no + "번 게시글을 삭제했습니다.");
			} else {
				System.out.println(no + "번 게시글을 삭제하지 못했습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		}
	}
		
	private void update(int no) {
		System.out.println("수정할 제목>> ");
		String title = ScanUtil.nextLine();
		System.out.println("수정할 내용>> ");
		String content = ScanUtil.nextLine();

		try {
			conn = JDBCUtil3.getConnection();

			String sql = "UPDATE JDBC_BOARDING "
						+ "SET BOARD_TITLE = ? "
						+ ", BOARD_CONTENT = ? "
						+ ", BOARD_DATE = SYSDATE "
						+ "WHERE BOARD_NO = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, no);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(title + " 게시글을 수정했습니다.");
			} else {
				System.out.println(title + " 게시글을 수정하지 못했습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		}
	}

	private int displayBoard() {
		System.out.println("========================================");
		System.out.print   ("번호\t제목\t\t작성자\t작성일\n");
		System.out.println("----------------------------------------");
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = "SELECT * FROM JDBC_BOARDING ORDER BY BOARD_NO DESC";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.print(rs.getInt("BOARD_NO") + "\t" + 
								 rs.getString("BOARD_TITLE") + "\t\t" + 
								 rs.getString("BOARD_WRITER") + "\t" + 
								 dateForm.format(rs.getObject("BOARD_DATE")) + "\n" );
			}
			
		}catch (SQLException e) {
			System.out.println("전체 글 목록 가져오기 실패!");
			e.printStackTrace();
			
		}finally {
			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		}
	
		System.out.println("----------------------------------------");
		System.out.println(" 1.조회\t2.등록\t3.검색\t0.종료");
		System.out.println("========================================");
		
		int choice = ScanUtil.nextInt();
		return choice;
	}
	
}
