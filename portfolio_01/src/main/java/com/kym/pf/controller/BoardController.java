package com.kym.pf.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.kym.pf.service.BoardAttachService;
import com.kym.pf.service.BoardService;
import com.kym.pf.util.FileUtil;

@Controller
public class BoardController {
	Logger logger = Logger.getLogger(BoardController.class);
	
	@Autowired
	private BoardService bService;
	
	@Autowired
	private BoardAttachService aService;
	
	@Autowired
	private FileUtil fUtil;
	
	@RequestMapping("/board/list.do")
	public ModelAndView list(@RequestParam HashMap<String, Object> params) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/board/boardList");
		
		String searchType = (String) params.get("searchType");
		String searchText = (String) params.get("searchText");
		
		// 현재 페이지
		int currentPage = params.containsKey("currentPage")?Integer.parseInt((String) params.get("currentPage")):1;
		// 보여줄 게시글 수
		int pageArticleSize = params.containsKey("pageArticleSize")?Integer.parseInt((String) params.get("pageArticleSize")):10;
		// 총 게시글 수
		int totalArticleCnt = bService.getTotalArticleCnt(params);
		// 총 페이지 수
		int totalPageCnt = (int) Math.ceil((double) totalArticleCnt / pageArticleSize);
		// 시작 인덱스
		int startIdx = (currentPage - 1) * pageArticleSize;
		// 끝 인덱스
		int endIdx = currentPage * totalArticleCnt;
		// 페이지 블럭 수
		int pageBlockSize = 10;	
		// 시작 페이지 설정
		int pageBlockStart = (currentPage - 1) / pageBlockSize * pageBlockSize + 1;
		// 종료 페이지 설정
		int pageBlockEnd = (currentPage - 1) / pageBlockSize * pageBlockSize + pageBlockSize;
		pageBlockEnd = (pageBlockEnd >= totalPageCnt) ? totalPageCnt : pageBlockEnd; 
		
		params.put("startIdx", String.valueOf(startIdx));
		params.put("pageArticleSize", String.valueOf(pageArticleSize));
		
		List<HashMap<String, Object>> allList = bService.getBoard(params);
		mv.addObject("allList", allList);
		mv.addObject("currentPage", currentPage);
		mv.addObject("pageBlockStart", pageBlockStart);
		mv.addObject("pageBlockEnd", pageBlockEnd);
		mv.addObject("pageBlockSize", pageBlockSize);
		mv.addObject("totalPageCnt", totalPageCnt);
		mv.addObject("searchType", searchType);
		mv.addObject("searchText", searchText);
		
		return mv;
	}
	
	@RequestMapping("/board/list2.do")
	public ModelAndView list2(@RequestParam HashMap<String, Object> params) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/board/gList");
		
		String searchType = (String) params.get("searchType");
		String searchText = (String) params.get("searchText");
		
		// 현재 페이지
		int currentPage = params.containsKey("currentPage")?Integer.parseInt((String) params.get("currentPage")):1;
		// 보여줄 게시글 수
		int pageArticleSize = params.containsKey("pageArticleSize")?Integer.parseInt((String) params.get("pageArticleSize")):10;
		// 총 게시글 수
		int totalArticleCnt = bService.getTotalArticleCnt(params);
		// 총 페이지 수
		int totalPageCnt = (int) Math.ceil((double) totalArticleCnt / pageArticleSize);
		// 시작 인덱스
		int startIdx = (currentPage - 1) * pageArticleSize;
		// 끝 인덱스
		int endIdx = currentPage * totalArticleCnt;
		// 페이지 블럭 수
		int pageBlockSize = 10;	
		// 시작 페이지 설정
		int pageBlockStart = (currentPage - 1) / pageBlockSize * pageBlockSize + 1;
		// 종료 페이지 설정
		int pageBlockEnd = (currentPage - 1) / pageBlockSize * pageBlockSize + pageBlockSize;
		pageBlockEnd = (pageBlockEnd >= totalPageCnt) ? totalPageCnt : pageBlockEnd; 
		
		params.put("startIdx", String.valueOf(startIdx));
		params.put("pageArticleSize", String.valueOf(pageArticleSize));
		
		List<HashMap<String, Object>> allList = bService.getBoard(params);
		mv.addObject("allList", allList);
		mv.addObject("currentPage", currentPage);
		mv.addObject("pageBlockStart", pageBlockStart);
		mv.addObject("pageBlockEnd", pageBlockEnd);
		mv.addObject("pageBlockSize", pageBlockSize);
		mv.addObject("totalPageCnt", totalPageCnt);
		mv.addObject("searchType", searchType);
		mv.addObject("searchText", searchText);
		
		return mv;
	}
	
	@RequestMapping("/board/goWrite.do")
	public ModelAndView goWrite() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/board/write");
		
		return mv;
	}
	
	@RequestMapping("/board/write.do")
	public ModelAndView write(@RequestParam HashMap<String, Object> params, MultipartHttpServletRequest mReq, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		logger.debug("params write params : "+params);
		List<MultipartFile> mFiles = mReq.getFiles("attFile");
		for(MultipartFile mf : mFiles) {
			if(!mf.getOriginalFilename().equals("")) {
				params.put("hasFile", "1");
				break;
			} else params.put("hasFile", "0");
			
			System.out.println("fileName : " + mf.getOriginalFilename());
			System.out.println("type : " + mf.getContentType());
			System.out.println("size : " + mf.getSize());
		}
		
		
		int result = bService.write(params, mFiles);
		mv.addObject("result", result);
		RedirectView rv = new RedirectView("/portfolio_01/board/list.do");
		mv.setView(rv);
		return mv;
	}
	
	@RequestMapping("/board/read.do")
	public ModelAndView read(@RequestParam HashMap<String, Object> params) {
		ModelAndView mv = new ModelAndView();
		int boardSeq = Integer.parseInt((String) params.get("boardSeq"));
		int typeSeq = Integer.parseInt((String) params.get("typeSeq"));
		
		HashMap<String, Object> getBoard = bService.read(params);
		logger.debug("params read : " + params);
		logger.debug("params cnt : " + getBoard);
		mv.addObject("cnt", getBoard);
		mv.addObject("title", getBoard.get("title"));
		mv.addObject("createDate", getBoard.get("create_date"));
		mv.addObject("hits", getBoard.get("hits"));
		mv.addObject("updateDate", getBoard.get("update_date"));
		mv.addObject("content", getBoard.get("content"));
		mv.addObject("msg", params.get("msg"));
		
		if(getBoard.get("has_file").equals("1")) {
			mv.addObject("file", bService.getFile(boardSeq, typeSeq));
			logger.debug("params file : "+bService.getFile(boardSeq, typeSeq));
		}
		
		mv.setViewName("board/read");
		
		return mv;
	}

	@RequestMapping("/board/modify.do")
	public ModelAndView modify(@RequestParam HashMap<String, Object> params, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		int boardSeq = Integer.parseInt((String) params.get("boardSeq"));
		int typeSeq = Integer.parseInt((String) params.get("typeSeq"));
		HashMap<String, Object> getBoard = bService.read(params);
		logger.debug("params modify : " + params);
		
		if(session.getAttribute("memberId") != null) {
			if(session.getAttribute("memberId").equals(getBoard.get("member_id"))) {
				mv.setViewName("/board/update");
				
				if(getBoard.get("has_file").equals("1")) {
					mv.addObject("file", bService.getFile(boardSeq, typeSeq));
				}
			} else {
				mv.setViewName("error/error");
				mv.addObject("msg", "본인이 작성한 게시글이 아닙니다.");
				mv.addObject("nextLocation", "/board/boardList.do");
			}
		} else {
			mv.setViewName("error/error");
			mv.addObject("msg", "로그인해주세요.");
			mv.addObject("nextLocation", "/login.do");
		}
		mv.addObject("getBoard", getBoard);
		
		return mv;
	}
	
	@RequestMapping("/board/update.do")
	public ModelAndView update(@RequestParam HashMap<String, Object> params, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView();
		logger.debug("params update : " + params);
		
		if(session.getAttribute("memberId").equals(params.get("memberId"))) {
			int result = bService.update(params);
			String url = "/portfolio_01/board/read.do?boardSeq=";
			url += params.get("boardSeq")+"&typeSeq="+params.get("typeSeq");
			rv = new RedirectView(url);
			mv.setView(rv);
		}
		
		return mv;
	}
	
	@RequestMapping("/board/delete.do")
	public ModelAndView delete(@RequestParam HashMap<String, Object> params, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView();
		logger.debug("params delete : " + params);
		
		if(session.getAttribute("memberId") != null) {
			HashMap<String, Object> getBoard = bService.read(params);
			logger.debug("params getBoard : "+getBoard);
			if(session.getAttribute("memberId").equals(getBoard.get("member_id"))) {
				int result = bService.delete(params);
				logger.debug("params result : "+result);
				if(result == 1) {
					rv = new RedirectView("/portfolio_01/board/list.do");
					mv.addObject("msg", "게시글이 삭제되었습니다.");
					mv.setView(rv);
				} else {
					String url = "/portfolio_01/board/read.do?boardSeq=";
					url += getBoard.get("board_seq")+"&typeSeq="+getBoard.get("type_seq");
					rv = new RedirectView(url);
					mv.addObject("msg", "게시글 삭제를 실패했습니다.");
					mv.setView(rv);
				}
			} else {
				logger.debug("params else : ");
				String url = "/portfolio_01/board/read.do?boardSeq=";
				url += getBoard.get("board_seq")+"&typeSeq="+getBoard.get("type_seq");
				mv.addObject("msg", "작성자가 아닙니다.");
				rv = new RedirectView(url);
				mv.setView(rv);
			}
		} else {
			mv.setViewName("error/error");
			mv.addObject("msg", "로그인해주세요.");
			mv.addObject("nextLocation", "/login.do");
		}

		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/board/download.do")
	public byte[] download(@RequestParam HashMap<String, Object> params, HttpServletResponse rep) {
		HashMap<String, Object> fileInfo = aService.download(params);
		
		byte[] result = fUtil.readFile(fileInfo);
		
		String endcodingName = null;
		try {
		endcodingName = java.net.URLEncoder.encode(fileInfo.get("file_name").toString(), "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		rep.setHeader("Content-Disposition", "attachment; filename=\"" + endcodingName + "\"");
		rep.setContentType(String.valueOf(fileInfo.get("file_type")));
		rep.setHeader("Pragma", "no-cache");
		rep.setHeader("Cache-Control", "no-cache");
		String tmp = String.valueOf(fileInfo.get("file_size"));
		rep.setContentLength(Integer.parseInt(tmp));
		
		return result;
	}
	
	@RequestMapping("/board/deleteAttach.do")
	public ModelAndView deleteAttach(@RequestParam HashMap<String, Object> params, @RequestParam int fileIdx) {
		ModelAndView mv = new ModelAndView();

		logger.debug("params fileIdx1 : "+params);
		logger.debug("params fileIdx2 : "+fileIdx);
		
		aService.deleteAttach(fileIdx);
		RedirectView rv = new RedirectView("/portfolio_01/board/modify.do?boardSeq="+params.get("boardSeq")+"&typeSeq="+params.get("typeSeq"));
		mv.setView(rv);
		return mv;
		
	}
	
	@RequestMapping("/member/gListPage.do")
	public ModelAndView gList(@RequestParam HashMap<String, Object> params) {
		logger.debug("params gListPage : "+params);
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/gList");
		return mv;
	}
	
	@RequestMapping("/board/gList.do")
	@ResponseBody
	public HashMap<String, Object> gListPage(@RequestParam HashMap<String, String> params) {
		logger.debug("params gList : "+params);
		ModelAndView mv = new ModelAndView();
		// 한 페이지 보여줄 게시글 수
		int rows = Integer.parseInt(params.get("rows"));
		// 현재 페이지
		int currentPage = Integer.parseInt(params.get("page"));
		// 시작 인덱스
		int startIdx = (currentPage - 1) * rows;		
		params.put("startIdx", String.valueOf(startIdx));
		// 전체 회원 수 구하기
		int totalBoard = bService.gListCount(params);
		// 총 페이지 수
		int totalPageCnt = (int) Math.ceil((double) totalBoard / rows);
		
		ArrayList<HashMap<String, Object>> gList = bService.gList(params);
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("page", params.get("page")); // 현재 페이지 
		result.put("total", totalPageCnt); // 총 페이지 수
		result.put("rows", gList); // 데이터(목록)
		result.put("records", totalBoard); // 총 회원수
		return result;
		
	}
	
	
} // class
