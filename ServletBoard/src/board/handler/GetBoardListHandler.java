package board.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardServiceImpl;
import board.service.IBoardService;
import board.vo.BoardVO;
import common.handler.CommandHandler;

public class GetBoardListHandler implements CommandHandler{

	private static IBoardService boardService = BoardServiceImpl.getInstance(); 
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String flag = req.getParameter("flag")==null ? "" : req.getParameter("flag");
		
		if("".equals(flag)) {
			
			//페이징 처리
			int pageNo = req.getParameter("pageNo")==null?1:Integer.parseInt(req.getParameter("pageNo"));
			
			BoardVO boardVO = new BoardVO(); // 빈값
			int totalCnt = boardService.countBoard(boardVO);
			boardVO.setTotalCount(totalCnt);
			boardVO.setCurrentPageNo(pageNo);
			boardVO.setCountPerPage(5);
			boardVO.setPageSize(5);
			
			//리스트 가져오기
			List<BoardVO> boardList = boardService.getBoardList(boardVO);
			
			req.setAttribute("boardList", boardList);
			req.setAttribute("boardVO", boardVO);
			
			return req.getContextPath() + "/views/board/getBoardList.jsp";
		
		}else if("search".equals(flag)) {
			
			//검색 조건 처리
			String searchOpt = req.getParameter("searchOpt")==null?"":req.getParameter("searchOpt");
			String searchValue = req.getParameter("searchValue")==null?"":req.getParameter("searchValue");
			
			BoardVO boardVO = new BoardVO();
			
			if("boardWriter".equals(searchOpt)) {
				boardVO.setBoardWriter(searchValue);
			}else if("boardTitle".equals(searchOpt)) {
				boardVO.setBoardTitle(searchValue);
			}else if("boardContent".equals(searchOpt)) {
				boardVO.setBoardContent(searchValue);
			}
			
			int totalCnt = boardService.countBoard(boardVO);
			
			int pageNo = req.getParameter("pageNo")==null?1:Integer.parseInt(req.getParameter("pageNo"));
			
			boardVO.setTotalCount(totalCnt);
			boardVO.setCurrentPageNo(pageNo);
			boardVO.setCountPerPage(5);
			boardVO.setPageSize(5);
			
			//데이터 가져와 보내기
			List<BoardVO> boardList = boardService.searchBoardList(boardVO);
			req.setAttribute("boardList", boardList);
			req.setAttribute("boardVO", boardVO);
			req.setAttribute("searchOpt", searchOpt);
			req.setAttribute("searchValue", searchValue);
			
			return req.getContextPath() + "/views/board/getBoardList.jsp";
		}
		
		return null;
	}

}
