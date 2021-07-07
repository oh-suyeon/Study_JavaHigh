package board.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import board.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao{

	private static IBoardDao boardDao;
	
	private BoardDaoImpl(){}
	
	public static IBoardDao getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		return boardDao;
	}
	
	@Override
	public List<BoardVO> getBoardList(SqlMapClient smc, BoardVO boardVO) throws Exception {
		List<BoardVO> boardList = smc.queryForList("board.getBoardList", boardVO);
		return boardList;
	}

	@Override
	public BoardVO getBoard(SqlMapClient smc, String boardNo) throws Exception {
		BoardVO boardVO = (BoardVO) smc.queryForObject("board.getBoard", boardNo);
		return boardVO;
	}

	@Override
	public List<BoardVO> searchBoardList(SqlMapClient smc, BoardVO boardVO) throws Exception {
		List<BoardVO> boardList = smc.queryForList("board.searchBoardList", boardVO);
		return boardList;
	}

	@Override
	public int countBoard(SqlMapClient smc, BoardVO boardVO) throws Exception {
		int cnt = (int) smc.queryForObject("board.countBoard", boardVO);
		return cnt;
	}

	@Override
	public int insertBoard(SqlMapClient smc, BoardVO boardVO) throws Exception {
		int cnt = 0;
		
		Object obj = smc.insert("board.insertBoard", boardVO);
		
		if(obj==null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public int updateBoard(SqlMapClient smc, BoardVO boardVO) throws Exception {
		int cnt = smc.update("board.updateBoard", boardVO);
		return cnt;
	}

	@Override
	public int deleteBoard(SqlMapClient smc, String boardNo) throws Exception {
		int cnt = smc.delete("board.deleteBoard", boardNo);
		return cnt;
	}

}
