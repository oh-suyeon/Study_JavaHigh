package board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardServiceImpl;
import board.service.IBoardService;
import common.handler.CommandHandler;

public class DeleteBoardHandler implements CommandHandler{

	private IBoardService boardService = BoardServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return true;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String boardNo = req.getParameter("boardNo");
		
		int cnt = boardService.deleteBoard(boardNo);
		
		String msg = "";
		if(cnt > 0) {
			msg = "success";
		}else {
			msg = "fail";
		}
		
		return req.getContextPath() + "/board/getBoardList.do?msg=" + msg;
	}

}
