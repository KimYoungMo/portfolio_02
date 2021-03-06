package com.kym.pf.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileController {
	
	@Autowired
	ApplicationContext context;
	
	@RequestMapping("/file/downloadErd.do")
	@ResponseBody
	public byte[] erd(HttpServletResponse resp) {
		
		try {
			File file = context.getResource("classpath:/files/erd.mwb").getFile();
			String encodingName = java.net.URLEncoder.encode(file.getName(),"UTF-8");
			resp.setHeader("Content-Disposition", "attachment; filename=\""+encodingName +"\"");
			resp.setHeader("pragma", "no-cache");
			resp.setHeader("Cache-Control", "no-cache");
			resp.setContentLength((int) file.length());
			return FileUtils.readFileToByteArray(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
