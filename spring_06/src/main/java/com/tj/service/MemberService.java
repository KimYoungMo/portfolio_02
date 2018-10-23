package com.tj.service;

import java.util.HashMap;
import java.util.List;

import com.tj.dto.Board;
import com.tj.dto.Member;

public interface MemberService {
	/**
	 * @param memberInfo
	 * @return mDao.join();
	 */
	public int join(HashMap<String, Object> memberInfo);
	/**
	 * @param memberId
	 * @param memberPw
	 * @return int result
	 * @throws Exception 
	 */
	public Member login(String memberId, String memberPw) throws Exception;
	
	public int checkId(HashMap<String, String> params);
	
}
