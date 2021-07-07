package kr.or.ddit.member.main;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;


public class MemberMain {
	
	private Scanner scan = new Scanner(System.in);
	
	private IMemberService memService;
	
	public MemberMain() {
		memService = new MemberServiceImpl();
	}
	
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
		System.out.println("  5. 자료 검색");
		System.out.println("  6. 작업 끝.");
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
			choice = scan.nextInt(); // 메뉴번호 입력받기
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
				case 5 :  // 자료 검색
					searchMember();
					break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}
	
	
	/**
	 * 회원 정보를 검색하는 메서드
	 */
	private void searchMember() {
	/*
	 * 검색할 회원ID, 회원이름, 전화번호, 주소등을 입려가면 입력한 정보만 사용하여 검색하는 기능을 구현한다.
	 * 주소는 입력한값이 포함만되어도 검색되도록 한다. 
	 * 입력하지 않을 자료는 엔터키로 다음 입력으로 넘긴다. 
	 */
		scan.nextLine();
		System.out.println();
		System.out.println("검색할 회원 정보 입력");
		
		System.out.print("회원 ID >> ");
		String memId = scan.nextLine().trim();
		
		System.out.print("회원 이름 >> ");
		String memName = scan.nextLine().trim();
		
		System.out.print("회원 전화번호 >> ");
		String memTel = scan.nextLine().trim();
		
		System.out.print("회원 주소 >> ");
		String memAddr = scan.nextLine().trim();
		
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		
		// 입력한 정보로 검색한 멤버들을 출력하는 부분 
		
		System.out.println();
		System.out.println("-----------------------------------------------------------");
		System.out.println(" ID \t이   름 \t전화번호 \t주   소");
		System.out.println("-----------------------------------------------------------");
		
		List<MemberVO> memList = memService.getSearchMember(mv);
		if(memList.size() == 0) {
			System.out.println("검색한 회원정보가 존재하지 않습니다.");
		}
		
		for(MemberVO mv2 : memList) {
			System.out.println(" " + mv2.getMemId() + "\t" + mv2.getMemName() + "\t" + mv2.getMemTel() + "\t" + mv2.getMemAddr());
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("출력 끝.");
		
	}

	private void deleteMember() {
		boolean chk = false;
		String memId;
		
		do {
			System.out.println();
			System.out.println("삭제할 회원 정보 입력");
			System.out.print("회원 ID >> ");
			memId = scan.next();
			
			chk = checkMember(memId);
			
			if(chk == false) {
				System.out.println(memId + "는 존재하지 않는 아이디입니다.");
				System.out.println("다시 입력하세요.");
			}
			
		}while(chk == false);
		
		int cnt = memService.deleteMember(memId);
		
		if(cnt > 0) {
			System.out.println(memId + "회원을 삭제했습니다.");
		} else {
			System.out.println(memId + "회원을 삭제하지 못했습니다.");
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
		
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		
		int cnt = memService.updateMember(mv);
		
		if(cnt > 0) {
			System.out.println(memId + "회원 정보를 수정했습니다.");
		} else {
			System.out.println(memId + "회원 정보를 수정하지 못했습니다.");
		}
	}

	
	private void displayMember() {
		System.out.println();
		System.out.println("-----------------------------------------------------------");
		System.out.println(" ID \t이   름 \t전화번호 \t주   소");
		System.out.println("-----------------------------------------------------------");
		
		List<MemberVO> memList = memService.getAllMemberList();
		
		for(MemberVO mv : memList) {
			System.out.println(" " + mv.getMemId() + "\t" + mv.getMemName() + "\t" + mv.getMemTel() + "\t" + mv.getMemAddr());
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("출력 끝.");
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
		
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		
		int cnt = memService.insertMember(mv);
		
		if(cnt > 0) {
			System.out.println(memId + "회원 추가 작업 성공!");
		} else {
			System.out.println(memId + "회원 추가 작업 실패!");
		}
	}

	
	// 회원 Id를 이용하여 회원이 있는지 알려주는메서드
	// param = memId 체크할회원 ID
	// return = 존재하면 true, 아니면 false
	private boolean checkMember(String memId) {
		
		boolean check = false;
		
		check = memService.checkMember(memId);
		
		return check;
	}

	
	public static void main(String[] args) {
		MemberMain memObj = new MemberMain();
		memObj.start();
	}

}






