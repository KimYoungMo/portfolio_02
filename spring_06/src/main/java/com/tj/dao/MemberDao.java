package com.tj.dao;

import java.util.HashMap;
import java.util.List;

import com.tj.dto.Board;
import com.tj.dto.Member;

public interface MemberDao {
	/**
	 * 사용자가 회원가입 시, 입력한 정보를 DB(member)에 저장
	 * @param memberInfo
	 * @return
	 */
	public int join(HashMap<String, Object> memberInfo);
	
	/**
	 * 사용자가 입력한 ID를 기존 DB(member)에서 검색 
	 * @param memberId
	 * @return MemberDto mDto || null
	 */
	public Member findMemberId(String memberId);
	
	/**
	 * 사용자가 입력한 비밀번호를 암호문으로 변환
	 * @param memberPw
	 * @return String jdbcTemplate.queryForObject()
	 */
	public String makeCipherText(String memberPw);
	
	/**
	 * 사용자가 회원가입 시 입력한 ID를 기존 DB(member)에서 검색하여 중복여부 확인
	 * @param params
	 * @return
	 */
	public int checkId(HashMap<String, String> params);
	
}
