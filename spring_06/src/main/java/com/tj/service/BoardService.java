package com.tj.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

public interface BoardService {

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
	 * 게시글을 선택하면 읽을 게시글의 정보를 DB(board)에서 불러옴
	 * @param boardSeq
	 * @param typeSeq
	 * @return
	 */
	public Map<String, Object> read(int boardSeq, int typeSeq);
	
	/**
	 * 선택한 게시글의 모든 정보를 DB(board)에서 삭제
	 * @param boardSeq
	 * @param typeSeq
	 * @return
	 */
	public int delete(int boardSeq, int typeSeq);
	
	/**
	 * 선택한 게시글의 정보 중 수정
	 * @param boardSeq
	 * @param typeSeq
	 * @return
	 */
	public int modify(int boardSeq, int typeSeq);
	
	public int update(HashMap<String, Object> params);
	
}
