package board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardServiceImpl;
import board.service.IBoardService;
import board.vo.BoardVO;
import common.handler.CommandHandler;

public class GetBoardHandler implements CommandHandler{
	
	private IBoardService boardService = BoardServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String boardNo = req.getParameter("boardNo");
		BoardVO boardVO = new BoardVO(); 
		boardVO = boardService.getBoard(boardNo);
		req.setAttribute("boardVO", boardVO);
		
		return req.getContextPath() + "/views/board/getBoard.jsp";
	}

}
