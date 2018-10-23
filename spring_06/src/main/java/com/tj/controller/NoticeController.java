package com.tj.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.tj.service.BoardAttachService;
import com.tj.service.NoticeService;
import com.tj.util.FileUtil;


@Controller
public class NoticeController {
	private Logger logger = Logger.getLogger(NoticeController.class);
	RedirectView rv = new RedirectView();
	
   // config.ini 에 설정해둔 값을 찾아서 사용한다.
   @Value("#{config['project.context.path']}")
   private String contextRoot;
   
//   @Value("${project.file.upload.location}")
//   private String saveLocation;
   
	@Autowired 
	private NoticeService nService;
	
	@Autowired
	private BoardAttachService aService;
	
	@Autowired
	private FileUtil fUtil;
	/**
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("/notice/nlist.do")
	public ModelAndView list(@RequestParam HashMap<String, String> params) {
		logger.debug("/notice/list.do params : " + params);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/notice/nlist");
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
		int totalArticleCnt = nService.getTotalArticleCnt(params);
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
		List<Map<String, Object>> allList = nService.bringCont(params);
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
	
	@RequestMapping("/notice/nread.do")
	public ModelAndView read(@RequestParam HashMap<String, String> params) {
		logger.debug("Result read.do params :" + params);
		ModelAndView mv = new ModelAndView();
		int boardSeq = Integer.parseInt(params.get("boardSeq"));
		int typeSeq = Integer.parseInt(params.get("typeSeq"));
		
		String msg = params.get("msg");
		
		Map<String, Object> getBoard = nService.read(boardSeq, typeSeq);
		// 첨부파일(hasFile)이 1이면
		if(getBoard.get("has_file").equals("1")) {
			mv.addObject("file", nService.getFile(boardSeq, typeSeq));
		}
		
		mv.addObject("getBoard", getBoard);
		if(msg != null) {
			mv.addObject("msg", msg);			
		}
		mv.setViewName("notice/nread");
		
		logger.debug("Result getBoard : "+getBoard);
		return mv;
	}
	
	@RequestMapping("/notice/goWritePage.do")
	public ModelAndView goWritePage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/notice/nwrite");
		
		return mv;
	}
	
	@RequestMapping("/notice/nwrite.do")
	public ModelAndView write(@RequestParam HashMap<String, Object> params, MultipartHttpServletRequest mReq) {
		ModelAndView mv = new ModelAndView();
//		mReq.getMultiFileMap();
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

		int result = nService.writeCont(params, mFiles);

		mv.addObject("result", result);
		RedirectView rv = new RedirectView(contextRoot+"/notice/nlist.do");
		mv.setView(rv);
		
		return mv;
	}
	
	@RequestMapping("/notice/delete.do")
	public ModelAndView delete(@RequestParam int boardSeq, int typeSeq, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView();
		
		// 로그인 된 상태에서만 삭제 가능 (세션에 memberID가 있다.)
		if(session.getAttribute("memberId") != null) {
			Map<String, Object> notice = nService.read(boardSeq, typeSeq);
			if(session.getAttribute("memberId").equals(notice.get("member_id"))) {
				int result = nService.delete(boardSeq, typeSeq, (String) notice.get("has_file"));
				if( result == 1) {
					rv = new RedirectView(contextRoot+"/notice/nlist.do");
					mv.addObject("msg", "게시글이 삭제되었습니다.");
					mv.setView(rv);
				} else {
					String url = contextRoot+"/notice/nread.do?boardSeq=";
					url += boardSeq+"&typeSeq="+typeSeq;
					rv = new RedirectView(url);
					mv.addObject("msg", "게시글 삭제 실패!");
					mv.setView(rv);
				}
				
			} else {
				String url = contextRoot+"/notice/nread.do?boardSeq=";
				url += boardSeq+"&typeSeq="+typeSeq;
				rv = new RedirectView(url);
				mv.addObject("msg", "작성자가 아닙니다.");
				mv.setView(rv);
			}
			
		} else {
			mv.setViewName("common/error");
			mv.addObject("msg", "로그인하세요.");
			mv.addObject("nextLocation", "/index.do");
		}

		return mv;
	}
	
	@RequestMapping("/notice/modify.do")
	public ModelAndView modify(int boardSeq, int typeSeq, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Map<String, Object> getBoard = nService.read(boardSeq, typeSeq);
		
		mv.addObject("getBoard", getBoard);
		
		// 로그인 된 상태에서만 수정 가능 (세션 memberId)
		if(session.getAttribute("memberId") != null) {
			// 세션이 있는 상태에서 작성자와 수정자가 같은지 확인
			if(session.getAttribute("memberId").equals(getBoard.get("member_id"))) {
				String url = contextRoot+"/notice/nupdate.do";
//				url += boardSeq + "&typeSeq=" + typeSeq;
//				rv = new RedirectView(url);
				mv.setViewName("notice/nupdate");
				
				// 첨부파일(hasFile)이 1이면
				if(getBoard.get("has_file").equals("1")) {
					mv.addObject("file", nService.getFile(boardSeq, typeSeq));
				} 
				
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
	
	@RequestMapping("/notice/nupdate.do")
	public ModelAndView update(@RequestParam HashMap<String, Object> params, HttpSession session, MultipartHttpServletRequest mReq) {
		ModelAndView mv = new ModelAndView();
		logger.debug("params !! : " + params);
		logger.debug("params session : " + params);
		logger.debug("Result name : " + session.getAttribute("memberNick"));
		logger.debug("Result name : " + params.get("memberNick"));
	
		
		// 로그인 상태에서 수정완료 적용
		if(session.getAttribute("memberId") != null) {
			
			if(session.getAttribute("memberNick").equals(params.get("memberNick"))) {
				//<input type="file" ...> 존재해야만 값이 반환 됨
				List<MultipartFile> files = mReq.getFiles("attFile");
				if(params.get("hasFile").equals("0")) {
					for(MultipartFile m : files) {
						// 첨부한 파일이 있으면
						if(!m.getOriginalFilename().equals("")) {
							params.put("hasFile", "1");
						} else {
							params.put("hasFile", "0");						
						}
					}
				}
				
				nService.update(params, files);
				String url = contextRoot+"/notice/nread.do?boardSeq=";
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
	
	@ResponseBody
	@RequestMapping("/notice/ndownload.do")
	public byte[] download(@RequestParam HashMap<String, String> params, HttpServletResponse rep) {
		
		logger.debug("params fileIdx :" + params.get("fileIdx"));
		Map<String, Object> fileInfo = aService.download(params);
		
		byte[] result = fUtil.readFile(fileInfo);
		
		
		String endcodingName = null;
		// 한글 파일명 인코딩
		try {
		endcodingName = java.net.URLEncoder.encode(fileInfo.get("file_name").toString(), "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 돌려보내기 위해 응답(HttpServletResponse)에 정보를 입력한다.
		// 파일 다운로드를 할 수 있는 정보들을 브라우저에 알려주는 역할(정보전달)
		rep.setHeader("Content-Disposition", "attachment; filename=\"" + endcodingName + "\"");
		rep.setContentType(String.valueOf(fileInfo.get("file_type")));
		rep.setHeader("Pragma", "no-cache");
		rep.setHeader("Cache-Control", "no-cache");
		String tmp = String.valueOf(fileInfo.get("file_size"));
		rep.setContentLength(Integer.parseInt(tmp));
	
		return result;
	}
	
	@RequestMapping("/notice/deleteAttach.do")
	public ModelAndView deleteAttach(@RequestParam int fileIdx, @RequestParam int boardSeq, @RequestParam int typeSeq) {
		logger.debug("Result fileIdx :" + fileIdx );
		ModelAndView mv = new ModelAndView();
		
		// 원 게시글과 첨부파일 정보의 관계를 확인한 후 게시글의 has_file을 바꾼다.
		nService.deleteAttach(fileIdx, boardSeq, typeSeq);
		
		// 삭제가 되든 안되든 /notice/nupdate.do로 Redirect 한다.
		RedirectView rv = new RedirectView(contextRoot+"/notice/modify.do?boardSeq="+boardSeq+"&typeSeq="+typeSeq);
		mv.setView(rv);
		
		
		return mv;
	}
	
}
