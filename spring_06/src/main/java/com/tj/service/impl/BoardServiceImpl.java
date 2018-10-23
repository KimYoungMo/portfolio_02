package com.tj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.tj.dao.BoardDao;
import com.tj.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDao bDao;
	
	@Override
	public int writeCont(HashMap<String, Object> params) {
		
		return bDao.writeCont(params);
	}

	@Override
	public List<Map<String, Object>> bringCont(HashMap<String, String> params) {
		
		return bDao.bringCont(params);		
	}

	@Override
	public int getTotalArticleCnt(HashMap<String, String> params) {
		
		return bDao.getTotalArticleCnt(params);
	}

	@Override
	public Map<String, Object> read(int boardSeq, int typeSeq) {
		
		bDao.updateHits(boardSeq, typeSeq);
		
		
		return bDao.getBoard(boardSeq, typeSeq);
	}

	@Override
	public int delete(int boardSeq, int typeSeq) {
		
		return bDao.delete(boardSeq, typeSeq);
	}

	@Override
	public int modify(int boardSeq, int typeSeq) {
		
		return 0;
	}

	@Override
	public int update(HashMap<String, Object> params) {

		return bDao.update(params);
	}


	
	
}
