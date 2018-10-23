package com.kym.pf.dao;

import java.util.HashMap;
import java.util.List;

public interface BoardAttachDao {

	public int inputFile(HashMap<String, Object> params);
	
	public List<HashMap<String, Object>> getFile(int boardSeq, int typeSeq);

	public HashMap<String, Object> download(HashMap<String, Object> params);

	public int deleteAttach(int fileIdx);


}
