package com.kym.pf.service;

import java.util.ArrayList;
import java.util.HashMap;

public interface MemberService {
	
	/**
	 * 사용자의 정보를 입력받아 DB에 저장 및 회원가입
	 * @param params
	 * @return
	 */
	public int join(HashMap<String, Object> params);
	
	/**
	 * 사용자가 입력한 ID를 DB에서 검색하여 중복여부 확인
	 * @param params
	 * @return
	 */
	public int checkId(HashMap<String, Object> params);
	
	public HashMap<String, Object> login(HashMap<String, Object> params) throws Exception;
	
	public ArrayList<HashMap<String, Object>> mList(HashMap<String, String> params);
	
	public int mListCount(HashMap<String, String> params);

	public int delMember(HashMap<String, String> params);
	
}
