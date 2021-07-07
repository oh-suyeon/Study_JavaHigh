package board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardServiceImpl;
import board.service.IBoardService;
import board.vo.BoardVO;
import common.handler.CommandHandler;

public class UpdateBoardHandler implements CommandHandler{

	private IBoardService boardService = BoardServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		
		if("GET".equals(req.getMethod())) {
			return false;
		}else if("POST".equals(req.getMethod())) {
			return true;
		}
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		if("GET".equals(req.getMethod())) {
			
			String boardNo = req.getParameter("boardNo");
			
			BoardVO boardVO = new BoardVO();
			boardVO = boardService.getBoard(boardNo);
			
			req.setAttribute("boardVO", boardVO);
			
			return req.getContextPath() + "/views/board/updateBoard.jsp";
			
		}else if("POST".equals(req.getMethod())) {
			
			BoardVO boardVO = new BoardVO();
			
			String boardNo = req.getParameter("boardNo");
			String boardTitle = req.getParameter("boardTitle");
			String boardContent = req.getParameter("boardContent");
			boardVO.setBoardNo(boardNo);
			boardVO.setBoardTitle(boardTitle);
			boardVO.setBoardContent(boardContent);
			
			int cnt = boardService.updateBoard(boardVO);
			
			String msg = "";
			if(cnt > 0) {
				msg = "success";
			}else {
				msg = "fail";
			}
			
			return req.getContextPath() + "/board/getBoard.do?boardNo=" + boardNo + "&msg=" + msg;
		}
		
		return null;
	}

}
