package com.kym.pf.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface BoardDao {

	public List<HashMap<String, Object>> getBoard(HashMap<String, Object> params);

	public int getTotalArticleCnt(HashMap<String, Object> params);

	public int write(HashMap<String, Object> params);

	public HashMap<String, Object> read(HashMap<String, Object> params);

	public int update(HashMap<String, Object> params);

	public int delete(HashMap<String, Object> params);

	public int updateHits(HashMap<String, Object> params);

	public int gListCount(HashMap<String, String> params);

	public ArrayList<HashMap<String, Object>> gList(HashMap<String, String> params);

}
