package com.tj.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tj.dao.MemberDao;
import com.tj.dto.Board;
import com.tj.dto.Member;

public class MemberDaoImpl implements MemberDao{
	@Autowired private JdbcTemplate jdbcTemplate;
	
	@Override
	public int join(HashMap<String, Object> memberInfo) {
		String sql = "INSERT INTO member (`type_seq`, `member_id`, `member_pw`, `member_name`, `member_nick`, `email`, `birth_date`, `create_date`)";
		sql = sql.concat(" VALUES (1, ?, SHA2(MD5(?),512), ?, ?, ?, ?, NOW() );");
		
		/*getJdbcTemplate().update(sql,
				memberInfo.get("userId"),
				memberInfo.get("userPw"),
				memberInfo.get("userName"),
				memberInfo.get("userNick"),
				memberInfo.get("email"),
				memberInfo.get("birthDate")
		);*/
		return jdbcTemplate.update(sql,
				memberInfo.get("userId"),
				memberInfo.get("userPw"),
				memberInfo.get("userName"),
				memberInfo.get("userNick"),
				memberInfo.get("email"),
				memberInfo.get("birthDate")
		);
	}

	@Override
	public Member findMemberId(String memberId) {
		String sql = "SELECT * FROM member WHERE member_id = ?";
		try {
			Member mDto = (Member) jdbcTemplate.queryForObject(sql,
					new Object[] {memberId},
					new BeanPropertyRowMapper<Member>(Member.class));
			
			return mDto;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	@Override
	public String makeCipherText(String memberPw) {
		String sql = "SELECT SHA2(MD5(?), 512)";
		
		return jdbcTemplate.queryForObject(sql, String.class, memberPw);
	}

	@Override
	public int checkId(HashMap<String, String> params) {
		
		String sql = "SELECT COUNT(member_idx) FROM member WHERE member_id = ?";
		
		return jdbcTemplate.queryForObject(sql, Integer.class, params.get("memberId"));
	}

	
	
}
