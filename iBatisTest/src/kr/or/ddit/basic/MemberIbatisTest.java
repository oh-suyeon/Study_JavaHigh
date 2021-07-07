package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.member.vo.MemberVO;

public class MemberIbatisTest {
	
	public static void main(String[] args) {
		
		//iBatis로 DB 자료 처리하기
		//1. 환경설정 파일 읽어서 실행
		try {
			// 1.1 xml 문서 읽어오기
			Charset charset = Charset.forName("UTF-8"); // 설정파일 인코딩 설정 (한글도 읽을 수 있게 Charset)
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			// 1.2. 위에서 읽어온 Reader 객체를 이용해 실제 작업을 진행할 객체 생성 
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close(); // Reader객체 닫기
			
			// 2. 실행할 SQL문에 맞는 쿼리문 호출해서 원하는 작업을 수행
			// 2.1. insert 작업
			System.out.println("insert 작업 시작...");
			
			// 1) 저장할 데이터를 VO에 담는다. 
			MemberVO mv = new MemberVO();
			mv.setMemId("d001");
			mv.setMemName("강감찬");
			mv.setMemTel("010-1111-1111");
			mv.setMemAddr("경상남도 진주시");
			
			// 2) sqlMapClient 객체 변수를 이용하여 해당 쿼리문 실행
			// 형식) smc.insert("namespace값.id값", 파라미터클래스); -- insert하는 메서드를 제공해줌
			// 반환값 : 성공하면null 반환 (성공하면 반환값이 없음)
			/*Object obj = smc.insert("memberTest.insertMember", mv); //memberTest.insetMember의 memberTest.가 namespace다. insertMember라는 변수명이 중복되지 않기 위해 
			if(obj == null) {
				System.out.println("insert작업 성공!");
			} else {
				System.out.println("insert작업 실패!");
			}
			System.out.println("----------------------------------------------");
			*/
			// 2.2) update 작업 연습
			
			System.out.println("update 작업 시작...");
			
			MemberVO mv2 = new MemberVO();
			mv2.setMemId("d001");
			mv2.setMemName("이순신");
			mv2.setMemTel("010-333-3333");
			mv2.setMemAddr("서울시 영등포구");
			
			//update() 메서드의 반환값은 성공한 레코드 수
			int cnt = smc.update("memberTest.updateMember", mv2);
			
			if(cnt > 0) {
				System.out.println("update 작업 성공");
			} else {
				System.out.println("update 작업 실패");
			}
			System.out.println("----------------------------------------------");
			
			// 2.3) delete 작업 연습
			System.out.println("delete 작업 시작...");
			
			// delete메서드의 반환값 : 성공한 레코드 수
			int cnt2 = smc.delete("memberTest.deleteMember", "d001");
			if(cnt2 > 0) {
				System.out.println("delete 작업 성공!");
			} else {
				System.out.println("delete 작업 실패!");
			}
			
			// 2.4) select 연습
			/*
			// 1) 응답의 결과가 여러개일 경우
			System.out.println("select 연습시작(결과가 여러 건)");
			
			// 응답결과가 여러개일 경우에는 queryForList메서드를 사용한다.
			// 이 메서드는 여러개의 레코드를 VO에 담은 후 이 VO데이터를 List에 자동으로 추가한다. 
			List<MemberVO> memList = smc.queryForList("memberTest.getMemberAll");
			
			for(MemberVO vo : memList) {
				System.out.println("============================");
				System.out.println("ID : " + vo.getMemId());
				System.out.println("이름 : " + vo.getMemName());
				System.out.println("전화 : " + vo.getMemTel());
				System.out.println("주소 : " + vo.getMemAddr());
				System.out.println("============================");
			}
			System.out.println("출력 끝...");
			*/
			
			// 2) 응답이 1개일 경우
			System.out.println("select 연습 시작(결과가 1개일 경우)...");
			
			// 응답 결과가1개가 확실한 경우에는 queryForObject 메서드를 사용
			MemberVO mv3 = (MemberVO) smc.queryForObject("memberTest.getMember", "77");
		
			System.out.println("============================");
			System.out.println("ID : " + mv3.getMemId());
			System.out.println("이름 : " + mv3.getMemName());
			System.out.println("전화 : " + mv3.getMemTel());
			System.out.println("주소 : " + mv3.getMemAddr());
			System.out.println("============================");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
