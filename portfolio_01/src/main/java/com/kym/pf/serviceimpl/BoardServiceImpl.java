package com.kym.pf.serviceimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kym.pf.dao.BoardAttachDao;
import com.kym.pf.dao.BoardDao;
import com.kym.pf.service.BoardService;
import com.kym.pf.util.FileUtil;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao bDao;
	
	@Autowired
	private BoardAttachDao aDao;
	
	@Autowired
	private FileUtil fUtil;
	
	/**
	 * 자유게시판 리스트 화면에 노출하기 위해 DB에서 게시글 전체 불러오기
	 */
	@Override
	public List<HashMap<String, Object>> getBoard(HashMap<String, Object> params) {

		return bDao.getBoard(params);
	}
	
	/**
	 * 보여줄 전체 게시글 수 카운트하기
	 */
	@Override
	public int getTotalArticleCnt(HashMap<String, Object> params) {
		
		return bDao.getTotalArticleCnt(params);
	}
	
	/**
	 * 사용자가 글쓰기를 누르면 게시글 작성
	 */
	@Override
	public int write(HashMap<String, Object> params, List<MultipartFile> mFiles) {
		
		bDao.write(params);
		
		for(MultipartFile mf : mFiles) {
			if(!mf.getOriginalFilename().equals("")) {
				// 난수를 만들어 가짜 이름으로 사용
				String fakename = UUID.randomUUID().toString().replace("-", "");
				params.put("typeSeq", 2);
				params.put("fileName", mf.getOriginalFilename());
				params.put("fileType", mf.getContentType());
				params.put("fileSize", mf.getSize());
				params.put("fakeName", fakename);
				aDao.inputFile(params);
				
				try {
					fUtil.copyFile(mf, fakename);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		return 0;
	}

	/**
	 * 사용자가 선택한 게시글의 데이터를 DB에서 갖고오기
	 */
	@Override
	public HashMap<String, Object> read(HashMap<String, Object> params) {
		bDao.updateHits(params);
		
		return bDao.read(params);
	}
	
	/**
	 * 사용자가 수정한 게시글 내용을 DB에 업데이트
	 */
	@Override
	public int update(HashMap<String, Object> params) {
		
		return bDao.update(params);
	}
	
	/**
	 * 사용자가 게시글 삭제를 눌렀을 때, DB에서 데이터 삭제
	 */
	@Override
	public int delete(HashMap<String, Object> params) {
		
		return bDao.delete(params);
	}
	
	/**
	 * 게시글을 볼때, 사용자가 첨부한 파일을 DB에서 갖고 온다.
	 */
	@Override
	public List<HashMap<String, Object>> getFile(int boardSeq, int typeSeq) {
			
		return aDao.getFile(boardSeq, typeSeq);
	}

	@Override
	public int gListCount(HashMap<String, String> params) {
		// TODO Auto-generated method stub
		return bDao.gListCount(params);
	}

	@Override
	public ArrayList<HashMap<String, Object>> gList(HashMap<String, String> params) {
		// TODO Auto-generated method stub
		return bDao.gList(params);
	}


}
