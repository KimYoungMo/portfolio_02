package com.kym.pf.serviceimpl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kym.pf.dao.BoardAttachDao;
import com.kym.pf.service.BoardAttachService;

@Service
public class BoardAttachServiceImpl implements BoardAttachService {

	@Autowired
	private BoardAttachDao aDao;
	
	/**
	 * 사용자가 게시글에 첨부한 첨부파일 다운로드
	 */
	@Override
	public HashMap<String, Object> download(HashMap<String, Object> params) {
		
		return aDao.download(params);
	}

	@Override
	public int deleteAttach(int fileIdx) {

		return aDao.deleteAttach(fileIdx);
	}

	
}
