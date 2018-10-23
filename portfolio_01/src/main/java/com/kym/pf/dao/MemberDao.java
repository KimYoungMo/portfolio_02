package com.kym.pf.dao;

import java.util.ArrayList;
import java.util.HashMap;

public interface MemberDao {

	/**
	 * 사용자가 입력한 정보를 받아 DB에 저장하고 회원가입
	 * @param params
	 * @return
	 */
	public int join(HashMap<String, Object> params);
	
	/**
	 * 사용자가 입력한 ID를 DB에서 검색하여 중복확인
	 * @param params
	 * @return
	 */
	public int checkId(HashMap<String, Object> params);

	public HashMap<String, Object> findMemberId(HashMap<String, Object> params);

	public String makeCipherText(HashMap<String, Object> params);

	public ArrayList<HashMap<String, Object>> mList(HashMap<String, String> params);

	public int mListCount(HashMap<String, String> params);

	public int delMember(HashMap<String, String> params);
	
	
}
