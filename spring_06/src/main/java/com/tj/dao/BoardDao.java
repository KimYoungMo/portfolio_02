package com.tj.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BoardDao {
	
	/**
	 * 게시글 작성 시, 입력된 정보를 DB(board)에 입력
	 * @param params
	 * @return
	 */
	public int writeCont(HashMap<String, Object> params);
	
	/**
	 * 게시글 읽기 시, DB(board)에 입력된 게시글 불러오기
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> bringCont(HashMap<String, String> params);
	
	/**
	 * List.jsp에 보여 줄 총 게시글 수 가져오기
	 * @return
	 */
	public int getTotalArticleCnt(HashMap<String, String> params);
	
	/**
	 * 게시글을 조회할 시, 조회수 증가
	 * @param typeSeq
	 * @param boardSeq
	 * @return
	 */
	public int updateHits(int boardSeq, int typeSeq);
	
	/**
	 * 게시글을 선택하여 읽을 시, DB(board)에 저장된 게시글 정보 불러오기
	 * @param boardSeq
	 * @param typeSeq
	 * @return
	 */
	public Map<String, Object> getBoard(int boardSeq, int typeSeq);
	
	/**
	 * 게시글 삭제를 누를 시, DB(board)에 저장된 게시글 정보 삭제
	 * @param boardSeq
	 * @param typeSeq
	 * @return
	 */
	public int delete(int boardSeq, int typeSeq);
	
	/**
	 * 게시글 내용 수정 시, DB(board)에 저장되어 있는 게시글 정보 업데이트
	 * @param params
	 * @return
	 */
	public int update(HashMap<String, Object> params);
	
}
