package com.kym.pf.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kym.pf.dao.MemberDao;
import com.kym.pf.exception.MemberNotFoundException;
import com.kym.pf.exception.PasswordMissMatchException;
import com.kym.pf.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	
	Logger logger = Logger.getLogger(MemberServiceImpl.class);
	
	@Autowired
	private MemberDao mDao;
	
	@Override
	public int join(HashMap<String, Object> params) {
		
		return mDao.join(params);
	}

	@Override
	public int checkId(HashMap<String, Object> params) {
		
		return mDao.checkId(params);
	}

	@Override
	public HashMap<String, Object> login(HashMap<String, Object> params) throws Exception {
		
		HashMap<String, Object> member = mDao.findMemberId(params);
		logger.debug("params member : " + member);
		if(member != null) {
			if(member.get("member_pw").equals(mDao.makeCipherText(params))) {
				return member;
			} else {
				throw new PasswordMissMatchException();
			}
		} else {
			throw new MemberNotFoundException();
		}
		
	}

	@Override
	public ArrayList<HashMap<String, Object>> mList(HashMap<String, String> params) {
		
		return mDao.mList(params);
	}

	@Override
	public int mListCount(HashMap<String, String> params) {

		return mDao.mListCount(params);
	}

	@Override
	public int delMember(HashMap<String, String> params) {

		return mDao.delMember(params);
	}

}
