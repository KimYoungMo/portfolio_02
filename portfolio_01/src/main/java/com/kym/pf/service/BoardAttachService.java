package com.kym.pf.service;

import java.util.HashMap;

public interface BoardAttachService {

	public HashMap<String, Object> download(HashMap<String, Object> params);

	public int deleteAttach(int fileIdx);

}
