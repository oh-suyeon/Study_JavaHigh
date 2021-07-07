package board.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import board.vo.BoardVO;
import common.vo.PagingVO;

public interface IBoardDao {
	
	/**
	 * 전체 게시글 목록 조회
	 * @param smc
	 * @return
	 * @throws Exception
	 */
	public List<BoardVO> getBoardList(SqlMapClient smc, BoardVO boardVO) throws Exception;
	
	/**
	 * 게시글 읽기
	 * @param smc
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public BoardVO getBoard(SqlMapClient smc, String boardNo) throws Exception;
	
	/**
	 * 검색한 게시글 목록 조회
	 * @param smc
	 * @param boardVO
	 * @return
	 * @throws Exception
	 */
	public List<BoardVO> searchBoardList(SqlMapClient smc, BoardVO boardVO) throws Exception;
	
	/**
	 * 게시글 개수 세기
	 * @param smc
	 * @param boardVO
	 * @return
	 * @throws Exception
	 */
	public int countBoard(SqlMapClient smc, BoardVO boardVO) throws Exception;
	
	/**
	 * 게시글 등록
	 * @param smc
	 * @param boardVO
	 * @return
	 * @throws Exception
	 */
	public int insertBoard(SqlMapClient smc, BoardVO boardVO) throws Exception;
	
	/**
	 * 게시글 수정
	 * @param smc
	 * @param boardVO
	 * @return
	 * @throws Exception
	 */
	public int updateBoard(SqlMapClient smc, BoardVO boardVO) throws Exception;
	
	/**
	 * 게시글 삭제
	 * @param smc
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public int deleteBoard(SqlMapClient smc, String boardNo) throws Exception;
}
