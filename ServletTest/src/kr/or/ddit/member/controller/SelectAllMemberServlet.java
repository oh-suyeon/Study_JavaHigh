package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class SelectAllMemberServlet extends HttpServlet {

	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 서비스 객체 생성
		IMemberService memService = MemberServiceImpl.getInstance();
		
		// 2. 회원정보 조회 -- 어딘가에 저장해놓아야 함. => req => 나중에 뷰에서 쓸 거야
		List<MemberVO> memList =  memService.getAllMemberList();
		req.setAttribute("memList", memList); // 응답하기 전까지는 꺼내 쓸 수 있다. 
		
		// 3. 포워딩으로 뷰에게 일(데이터) 주기
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/list.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
