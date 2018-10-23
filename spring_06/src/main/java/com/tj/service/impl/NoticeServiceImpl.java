package com.tj.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tj.dao.BoardAttachDao;
import com.tj.dao.NoticeDao;
import com.tj.service.NoticeService;
import com.tj.util.FileUtil;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDao nDao;
	@Autowired
	private BoardAttachDao aDao;
	@Autowired
	private FileUtil fUtil;
	
	@Override
	public int writeCont(HashMap<String, Object> params, List<MultipartFile> mFiles) {
		// 1. 글 등록
		nDao.writeCont(params);
		
		
		System.out.println("boardSeq : "+ params.get("boardSeq"));
		// 2. 첨부파일이 있으면 board_attach 테이블에 등록
		for(MultipartFile mf : mFiles) {
			if(!mf.getOriginalFilename().equals("")) {
				// 난수를 만들어 가짜 이름으로 사용
				String fakename = UUID.randomUUID().toString().replace("-", "");
				params.put("typeSeq", 1);
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

	@Override
	public List<Map<String, Object>> bringCont(HashMap<String, String> params) {
		
		return nDao.bringCont(params);		
	}

	@Override
	public int getTotalArticleCnt(HashMap<String, String> params) {
		
		return nDao.getTotalArticleCnt(params);
	}

	@Override
	public Map<String, Object> read(int boardSeq, int typeSeq) {
		
		nDao.updateHits(boardSeq, typeSeq);
		
		
		return nDao.getBoard(boardSeq, typeSeq);
	}

	@Override
	public int delete(int boardSeq, int typeSeq, String hasFile) {
		int result = 0;
		if(hasFile.equals("1")) {
			// 1. 삭제할 첨부파일 정보를 모두 가지고 온다.
			List<HashMap<String, Object>> files = aDao.getFile(boardSeq, typeSeq);
			// 2. 글번호, 타입으로 첨부파일 삭제하는 DAO 메서드 호출
			aDao.deleteHasFile(boardSeq, typeSeq);
			
			// 3. 물리적 위치에서 첨부파일 삭제
			for(HashMap<String, Object> file : files) {
				fUtil.deleteFile(file);
			}
			return result;
		} 
		
		return nDao.delete(boardSeq, typeSeq);
	}

	@Override
	public int modify(int boardSeq, int typeSeq) {
		
		return 0;
	}

	@Override
	public int update(HashMap<String, Object> params, List<MultipartFile> mFiles) {
		for(MultipartFile mf : mFiles) {
			if(!mf.getOriginalFilename().equals("")) {
				// 난수를 만들어 가짜 이름으로 사용
				String fakename = UUID.randomUUID().toString().replace("-", "");
				params.put("typeSeq", 1);
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
		
		return nDao.update(params);
	}

	@Override
	public List<HashMap<String, String>> getFile(int boardSeq, int typeSeq) {
		
		return aDao.getFile(boardSeq, typeSeq);
	}

	@Override
	public boolean deleteAttach(int fileIdx, int boardSeq, int typeSeq) {
		boolean result = false;
		// 첨부파일 정보를 가져온다.
		HashMap<String, Object> fileInfo = aDao.getFile2(boardSeq, typeSeq);
		// DB에서 첨부파일 정보를 삭제한다.
		result = (aDao.deleteAttach(fileIdx) == 1);
		// 원 게시글과 첨부파일 정보의 관계를 확인한다.
		List<HashMap<String, String>> files = aDao.getFile(boardSeq, typeSeq);
		// 가져온 첨부파일이 없으면(더이상 첨부파일이 없으면)
		if(files == null || files.size() ==0) {
			// 게시글의 has_file을 0으로 바꾼다.
			result = (nDao.updateHasFile(boardSeq, typeSeq) == 1 && result);
		}
		// 물리디스크에서 삭제한다.
		result = (fUtil.deleteFile(fileInfo) == result);
		
		return result;
	}
}
