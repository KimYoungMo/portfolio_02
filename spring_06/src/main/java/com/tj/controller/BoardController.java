package com.tj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.tj.service.BoardService;


@Controller
public class BoardController {
	private Logger logger = Logger.getLogger(BoardController.class);
	RedirectView rv = new RedirectView();
	
	@Autowired 
	private BoardService bService;

	@RequestMapping("/board/list.do")
	public ModelAndView list(@RequestParam HashMap<String, String> params) {
		logger.debug("/board/list.do params : " + params);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/board/list");
		params.get("msg");
		if(params.containsKey("msg")) {
			 mv.addObject("msg", params.get("msg"));
		} 
		
		String searchType = params.get("searchType");
		String searchText = params.get("searchText");
		
		// * 페이징 처리 *
		// 현재 페이지
		int currentPage = params.containsKey("currentPage")?Integer.parseInt(params.get("currentPage")):1;
		
		// 보여줄 게시글 수
		int pageArticleSize = params.containsKey("pageArticleSize")?Integer.parseInt(params.get("pageArticleSize")):10;
		
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
		
		// 게시글 전체를 가져온다.
		List<Map<String, Object>> allList = bService.bringCont(params);
		mv.addObject("list", allList);
		mv.addObject("currentPage", currentPage);
		mv.addObject("pageBlockStart", pageBlockStart);
		mv.addObject("pageBlockEnd", pageBlockEnd);
		mv.addObject("pageBlockSize", pageBlockSize);
		mv.addObject("totalPageCnt", totalPageCnt);
		mv.addObject("searchType", searchType);
		mv.addObject("searchText", searchText);
		
		return mv;		
	}
	
	@RequestMapping("/board/read.do")
	public ModelAndView read(@RequestParam HashMap<String, String> params) {
		logger.debug("Result read.do params :" + params);
		ModelAndView mv = new ModelAndView();
		int boardSeq = Integer.parseInt(params.get("boardSeq"));
		int typeSeq = Integer.parseInt(params.get("typeSeq"));
		
		String msg = params.get("msg");
		
		Map<String, Object> getBoard = bService.read(boardSeq, typeSeq);
		
		mv.addObject("getBoard", getBoard);
		if(msg != null) {
			mv.addObject("msg", msg);			
		}
		mv.setViewName("/board/read");
		
		logger.debug("Result getBoard : "+getBoard);
		return mv;
	}
	
	@RequestMapping("/board/goWritePage.do")
	public ModelAndView goWritePage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/board/write");
		
		return mv;
	}
	
	@RequestMapping("/board/write.do")
	public ModelAndView write(@RequestParam HashMap<String, Object> params) {
		ModelAndView mv = new ModelAndView();
		
//		String contents = (String)params.get("contents");
//		contents = contents.replaceAll("\\r", "<br>");
//		contents = contents.replaceAll("\\n", "\n");
//		contents = contents.replaceAll("\\r\\n", "<br>");
//		params.put("contents", contents);
		
		int result = bService.writeCont(params);

		mv.addObject("result", result);
		RedirectView rv = new RedirectView("/s06/board/list.do");
		mv.setView(rv);
		
		return mv;
	}
	
	@RequestMapping("/board/delete.do")
	public ModelAndView delete(int boardSeq, int typeSeq, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView();
		int result = bService.delete(boardSeq, typeSeq);
		
		// 로그인 된 상태에서만 삭제 가능 (세션에 memberID가 있다.)
		if(session.getAttribute("memberId") != null) {
			if( result == 1) {
				rv = new RedirectView("/s06/board/list.do");
				mv.addObject("msg", "게시글이 삭제되었습니다.");
				mv.setView(rv);
			} else {
				String url = "/s06/board/read.do?boardSeq=";
				url += boardSeq+"&typeSeq="+typeSeq;
				rv = new RedirectView(url);
				mv.addObject("msg", "게시글 삭제 실패!");
				mv.setView(rv);
			}
			
		} else {
			mv.setViewName("common/error");
			mv.addObject("msg", "로그인하세요.");
			mv.addObject("nextLocation", "/index.do");
		}

		return mv;
	}
	
	@RequestMapping("/board/modify.do")
	public ModelAndView modify(int boardSeq, int typeSeq, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Map<String, Object> getBoard = bService.read(boardSeq, typeSeq);
		
		mv.addObject("getBoard", getBoard);
		
		// 로그인 된 상태에서만 수정 가능 (세션 memberId)
		if(session.getAttribute("memberId") != null) {
			// 세션이 있는 상태에서 작성자와 수정자가 같은지 확인
			if(session.getAttribute("memberId").equals(getBoard.get("member_id"))) {
				String url = "/s06/board/update.do";
//				url += boardSeq + "&typeSeq=" + typeSeq;
//				rv = new RedirectView(url);
				mv.setViewName("board/update");
			} else {
				logger.debug("Result sMemberId : " + session.getAttribute("memberId"));
				logger.debug("Result gMemberId : " + getBoard.get("member_id"));
				mv.setViewName("common/error");
				mv.addObject("msg", "본인이 작성한 게시물이 아닙니다.");
				mv.addObject("nextLocation", "/index.do");
			}			
		} else {
			mv.setViewName("common/error");
			mv.addObject("msg", "로그인하세요.");
			mv.addObject("nextLocation", "/index.do");
		}

		return mv;
	}
	
	@RequestMapping("/board/update.do")
	public ModelAndView update(@RequestParam HashMap<String, Object> params, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		logger.debug("params !! : " + params);
		logger.debug("params session : " + params);
		logger.debug("Result name : " + session.getAttribute("memberNick"));
		logger.debug("Result name : " + params.get("memberNick"));
		
		// 로그인 상태에서 수정완료 적용
		if(session.getAttribute("memberId") != null) {
			if(session.getAttribute("memberNick").equals(params.get("memberNick"))) {
				bService.update(params);
				String url ="/s06/board/read.do?boardSeq=";
				url += params.get("boardSeq") + "&typeSeq=" + params.get("typeSeq");
				rv = new RedirectView(url);
//				mv.setViewName(url);
				mv.setView(rv);
			} else {
				mv.setViewName("common/error");
				mv.addObject("msg", "본인이 작성한 게시물이 아닙니다.");
				mv.addObject("nextLocation", "/index.do");
			}			
		} else {
			mv.setViewName("common/error");
			mv.addObject("msg", "로그인하세요.");
			mv.addObject("nextLocation", "/index.do");
		}
		
		
		return mv;
	}
	
}
