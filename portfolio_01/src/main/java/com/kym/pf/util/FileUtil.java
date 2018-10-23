package com.kym.pf.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
    @Value("#{config['save.location.path']}")
    private String saveLocation;
	
	public boolean deleteFile(HashMap<String, Object> fileInfo) {
		// 파일 찾기
	      File f = new File(saveLocation, String.valueOf(fileInfo.get("fake_filename")));
	      
	      if(f.exists()) { // 물리적 위치에 존재하면
	    	  return f.delete(); // 지운다.
	      }
	      return false;
	}
	
	private Logger logger = Logger.getLogger(FileUtil.class);
	
	public void copyFile(MultipartFile mf, String fakename) throws IOException {
		// 폴더 지정
		File destDir = new File(this.saveLocation);
		if(!destDir.exists()) {
			// 없으면 만든다.
			destDir.mkdirs();
		}
		
		// 파일 지정
		File destFile = new File(destDir, fakename);
		FileCopyUtils.copy(mf.getBytes(), destFile);
			
		
	}
	
   public byte[] readFile(Map<String, Object> fileInfo) {
	      // 파일 찾기
	      File f = new File(saveLocation, String.valueOf(fileInfo.get("fake_filename")));
	      															   
	      byte[] b = null;
	      logger.debug("Result fileInfo : " + fileInfo);
	      logger.debug("Result f : " + f);
	      if(f.exists()) {   // 물리적 위치에 존재하면
	         try {
	            // byte단위로 파일을 읽어온다.
	            b = FileUtils.readFileToByteArray(f);
	            
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	         
	      } else {   // 물리적 위치에 파일 없음
	         
	      }
	      
	      return b;
	   }
	
}
