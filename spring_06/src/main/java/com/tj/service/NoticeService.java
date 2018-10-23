package com.tj.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface NoticeService {
	
	/**
	 * 공지사항 작성 시, 입력된 정보를  DB(board)에 입력
	 * @param params
	 * @param mFiles
	 * @return
	 */
	public int writeCont(HashMap<String, Object> params, List<MultipartFile> mFiles);
	
	/**
	 * 게시글 읽기 시, DB(board)에 입력된 게시글 불러오기
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> bringCont(HashMap<String, String> params);
	
	/**
	 * nList.jsp에 보여 줄 총 게시글 수 가져오기
	 * @param params
	 * @return
	 */
	public int getTotalArticleCnt(HashMap<String, String> params);
	
	/**
	 * 사용자가 선택한 게시글의 정보를 DB(board)에서 가져오기
	 * @param boardSeq
	 * @param typeSeq
	 * @return
	 */
	public Map<String, Object> read(int boardSeq, int typeSeq);
	
	/**
	 * 게시글 삭제를 누를 시, DB(board)에 저장된 게시글 정보 삭제
	 * @param boardSeq
	 * @param typeSeq
	 * @return
	 */
	public int delete(int boardSeq, int typeSeq, String hasFile);
	
	/**
	 * 게시글 수정을 누를 시, DB(board)에 저장된 게시글 정보 업데이트
	 * @param boardSeq
	 * @param typeSeq
	 * @return
	 */
	public int modify(int boardSeq, int typeSeq);
	
	/**
	 * 게시글 수정 중 첨부파일 삭제를 누를 시, DB()
	 * @param fileIdx
	 * @param boardSeq
	 * @param typeSeq
	 * @return
	 */
	public boolean deleteAttach(int fileIdx, int boardSeq, int typeSeq);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public int update(HashMap<String, Object> params, List<MultipartFile> mFiles);
	
	/**
	 * 
	 * @param boardSeq
	 * @param typeSeq
	 * @return
	 */
	public List<HashMap<String, String>> getFile(int boardSeq, int typeSeq);
}
