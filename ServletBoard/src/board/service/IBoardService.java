package board.service;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import board.vo.BoardVO;

public interface IBoardService {
	
	/**
	 * 전체 게시글 목록 조회
	 * @param smc
	 * @return
	 * @throws Exception
	 */
	public List<BoardVO> getBoardList(BoardVO boardVO);
	
	/**
	 * 게시글 읽기
	 * @param smc
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public BoardVO getBoard(String boardNo);
	
	/**
	 * 검색한 게시글 목록 조회
	 * @param smc
	 * @param boardVO
	 * @return
	 * @throws Exception
	 */
	public List<BoardVO> searchBoardList(BoardVO boardVO);
	
	/**
	 * 게시글 개수 세기
	 * @param smc
	 * @param boardVO
	 * @return
	 * @throws Exception
	 */
	public int countBoard(BoardVO boardVO);
	
	/**
	 * 게시글 등록
	 * @param smc
	 * @param boardVO
	 * @return
	 * @throws Exception
	 */
	public int insertBoard(BoardVO boardVO);
	
	/**
	 * 게시글 수정
	 * @param smc
	 * @param boardVO
	 * @return
	 * @throws Exception
	 */
	public int updateBoard(BoardVO boardVO);
	
	/**
	 * 게시글 삭제
	 * @param smc
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public int deleteBoard(String boardNo);
	
}
