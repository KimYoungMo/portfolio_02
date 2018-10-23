package com.tj.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BoardAttachService {
	
	/**
	 * 게시글 읽기 중 첨부파일 클릭 시, 첨부되어 있는 파일의 정보를 DB(board_attach)에서 불러오고 다운
	 * @param params
	 * @return
	 */
	public Map<String, Object> download(HashMap<String, String> params);
	
	/**
	 * DB(board_attach)에 입력되어 있는 정보를 갖고 옴 (HashMap<String, Object>)
	 * @param boardSeq
	 * @param typeSeq
	 * @return
	 */
	public HashMap<String, Object> getFile2(int boardSeq, int typeSeq);
	
	/**
	 * 게시글 수정(첨부파일 삭제) DB(board_attach)에 입력된 파일 정보 삭제
	 * @param fileIdx
	 * @return
	 */
	public int deleteAttach(int fileIdx);
}