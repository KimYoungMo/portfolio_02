package com.tj.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BoardAttachDao {
	/**
	 * 게시글에 첨부한 파일의 정보를 DB(board_attach)에 입력
	 * @param params
	 * @return
	 */
	public int inputFile(HashMap<String, Object> params);
	
	/**
	 * DB(board_attach)에 입력되어 있는 정보를 갖고 옴 (HashMap<String, String>)
	 * @param boardSeq
	 * @param typeSeq
	 * @return
	 */
	public List<HashMap<String, Object>> getFile(int boardSeq, int typeSeq);
	
	/**
	 * DB(board_attach)에 입력되어 있는 정보를 갖고 옴 (HashMap<String, Object>)
	 * @param boardSeq
	 * @param typeSeq
	 * @return
	 */
	public HashMap<String, Object> getFile2(int boardSeq, int typeSeq);
	
	/**
	 * 게시글에 첨부된 파일의 정보를 다운로드
	 * @param params
	 * @return
	 */
	public Map<String, Object> download(HashMap<String, String> params);
	
	/**
	 * 게시글 수정(첨부파일 삭제) DB(board_attach)에 입력된 파일 정보 삭제
	 * @param fileIdx
	 * @return
	 */
	public int deleteAttach(int fileIdx);
	
	public int deleteHasFile(int boardSeq, int typeSeq);
}
