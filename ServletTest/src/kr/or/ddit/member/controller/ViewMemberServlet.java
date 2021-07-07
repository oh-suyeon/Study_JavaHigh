package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class ViewMemberServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		
		// 1. 서비스 객체
		IMemberService memService = MemberServiceImpl.getInstance();
		
		// 2. 회원 정보 조회 & 담기
		MemberVO memVO = memService.getMember(memId);
		req.setAttribute("memVO", memVO);
		
		// 3. 뷰에게 전달
		req.getRequestDispatcher("/member/select.jsp").forward(req, resp);;
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
