package board.service;

import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import board.dao.BoardDaoImpl;
import board.dao.IBoardDao;
import board.vo.BoardVO;
import util.SqlMapClientFactory;

public class BoardServiceImpl implements IBoardService{
	
	private static IBoardService boardService;
	private IBoardDao boardDao;
	private SqlMapClient smc;
	
	public static IBoardService getInstance() {
		if(boardService==null) {
			boardService = new BoardServiceImpl();
		}
		return boardService;
	}
	
	private BoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstance();
		smc = SqlMapClientFactory.getInstance();
	}
	
	@Override
	public List<BoardVO> getBoardList(BoardVO boardVO) {
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			boardList = boardDao.getBoardList(smc, boardVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return boardList;
	}

	@Override
	public BoardVO getBoard(String boardNo) {
		
		BoardVO boardVO = new BoardVO();
		
		try {
			boardVO = boardDao.getBoard(smc, boardNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return boardVO;
	}

	@Override
	public List<BoardVO> searchBoardList(BoardVO boardVO) {
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			boardList = boardDao.searchBoardList(smc, boardVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return boardList;
	}

	@Override
	public int countBoard(BoardVO boardVO) {
		int cnt = 0;
		
		try {
			cnt = boardDao.countBoard(smc, boardVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int insertBoard(BoardVO boardVO) {
		int cnt = 0;
		
		try {
			cnt = boardDao.insertBoard(smc, boardVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO boardVO) {
		int cnt = 0;
		
		try {
			cnt = boardDao.updateBoard(smc, boardVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(String boardNo) {
		int cnt = 0;
		
		try {
			cnt = boardDao.deleteBoard(smc, boardNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

}
