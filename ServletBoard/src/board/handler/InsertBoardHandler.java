package board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardServiceImpl;
import board.service.IBoardService;
import board.vo.BoardVO;
import common.handler.CommandHandler;

public class InsertBoardHandler implements CommandHandler{

	private IBoardService boardService = BoardServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if("GET".equals(req.getMethod())) {
			return false;
		}else if("POST".equals(req.getMethod())){
			return true;
		}
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		
		if("GET".equals(req.getMethod())) {
			
			return req.getContextPath() + "/views/board/insertBoard.jsp";
			
		}else if("POST".equals(req.getMethod())){
			
			String boardTitle = req.getParameter("boardTitle");
			String boardContent = req.getParameter("boardContent");
			String boardWriter = req.getParameter("boardWriter");
			
			BoardVO boardVO = new BoardVO();
			boardVO.setBoardTitle(boardTitle);
			boardVO.setBoardContent(boardContent);
			boardVO.setBoardWriter(boardWriter);
			
			int cnt = boardService.insertBoard(boardVO);
			
			String msg = "";
			if(cnt > 0) {
				msg = "success";
			}else {
				msg = "fail";
			}
			
			return req.getContextPath() + "/board/getBoardList.do?msg=" + msg;
		}
		
		return null;
	}

}
