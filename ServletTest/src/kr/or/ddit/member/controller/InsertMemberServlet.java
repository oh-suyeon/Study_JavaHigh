package kr.or.ddit.member.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class InsertMemberServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher =  req.getRequestDispatcher("/member/insertForm.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 요청 파라미터 정보 가져오기
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		
		// 2. 서비스 객체 생성
		IMemberService memService = MemberServiceImpl.getInstance();
		
		// 3. 회원등록 
		int cnt = memService.insertMember(mv);
		
		String msg = "";
		
		if(cnt>0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		// 목록 조회 화면으로 이동
		// req.getRequestDispatcher("/member/list").forward(req, resp);
		
		resp.sendRedirect(req.getContextPath() + "/member/list?msg=" + URLEncoder.encode(msg, "utf-8"));
		
		
		
		
	}
}
