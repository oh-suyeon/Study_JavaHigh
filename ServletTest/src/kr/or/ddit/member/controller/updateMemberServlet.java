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

public class updateMemberServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String memId = req.getParameter("memId");
		
		// 1.서비스 객체생성
		IMemberService memberService = MemberServiceImpl.getInstance();
		
		// 2.회원 정보 조회
		MemberVO memVO = memberService.getMember(memId);
		
		// 3.req 객체에 회원 정보 저장
		req.setAttribute("memVO", memVO);
		
		// 4. view로 이동
		req.getRequestDispatcher("/member/updateForm.jsp").forward(req, resp);
		
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
		
		//2. 서비스 객체 생성
		IMemberService memService = MemberServiceImpl.getInstance();
		
		//3. 회원 정보 수정
		int cnt = memService.updateMember(mv);
		String msg = "";
		
		if(cnt > 0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		//4. 목록조회 화면으로 이동
		resp.sendRedirect(req.getContextPath() + "/member/list?msg=" + URLEncoder.encode(msg, "utf-8"));
		
	}
	
}