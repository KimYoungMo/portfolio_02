package com.kym.pf.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface BoardService {

	public List<HashMap<String, Object>> getBoard(HashMap<String, Object> params);

	public int getTotalArticleCnt(HashMap<String, Object> params);

	public int write(HashMap<String, Object> params, List<MultipartFile> mFiles);

	public HashMap<String, Object> read(HashMap<String, Object> params);

	public int update(HashMap<String, Object> params);

	public int delete(HashMap<String, Object> params);

	public List<HashMap<String, Object>> getFile(int boardSeq, int typeSeq);

	public int gListCount(HashMap<String, String> params);

	public ArrayList<HashMap<String, Object>> gList(HashMap<String, String> params);

}
