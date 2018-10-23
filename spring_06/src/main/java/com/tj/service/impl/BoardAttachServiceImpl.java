package com.tj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tj.dao.BoardAttachDao;
import com.tj.service.BoardAttachService;

@Service
public class BoardAttachServiceImpl implements BoardAttachService{

	@Autowired
	private BoardAttachDao aDao;
	
	public Map<String, Object> download(HashMap<String, String> params){
		
		return aDao.download(params);
	}

	@Override
	public HashMap<String, Object> getFile2(int boardSeq, int typeSeq) {

		return aDao.getFile2(boardSeq, typeSeq);
	}
	
	@Override
	public int deleteAttach(int fileIdx) {
		
		return aDao.deleteAttach(fileIdx);
	}

}
