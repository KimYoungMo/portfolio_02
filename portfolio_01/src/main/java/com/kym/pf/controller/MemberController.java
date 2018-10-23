package com.kym.pf.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.kym.pf.exception.MemberNotFoundException;
import com.kym.pf.exception.PasswordMissMatchException;
import com.kym.pf.service.MemberService;

@Controller
public class MemberController {

	Logger logger = Logger.getLogger(MemberController.class);
	
	@Autowired
	private MemberService mService;
	
	@RequestMapping("/member/join.do")
	public ModelAndView join(@RequestParam HashMap<String, Object> params ) {
		ModelAndView mv = new ModelAndView();
		String msg = (mService.join(params) == 1) ? "회원가입 성공!!" : "회원가입 실패 ㅜ_ㅜ";
		
		mv.setViewName("login");
		mv.addObject("msg", msg);
		RedirectView rv = new RedirectView("/login.do");
		
		return mv;
	}
	
	@RequestMapping("/member/checkId.do")
	@ResponseBody
	public int checkId(@RequestParam HashMap<String, Object> params) {
		ModelAndView mv = new ModelAndView();
		
		int result = mService.checkId(params);
		
		return result;
	}
	
	
	@RequestMapping("/member/login.do")
	public ModelAndView login(@RequestParam HashMap<String, Object> params , HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		String page = "login";
		String msg = "System Error";
		logger.debug("params : " + params);
		
		if(session.getAttribute((String) params.get("memberId")) != null) {
			RedirectView rv = new RedirectView("/index.do");
			mv.setView(rv);
		} else {
			try { // 난 DTO를 쓰고 싶지 않다..... DB에서 회원 정보를 어떻게 불러올 것이냐
				HashMap<String, Object> member = mService.login(params);
				logger.debug("params : " + member);
				session.setAttribute("typeSeq", member.get("type_seq"));
				session.setAttribute("memberId", member.get("member_id"));
				session.setAttribute("memberIdx", member.get("member_idx"));
				session.setAttribute("memberName", member.get("member_name"));
				session.setAttribute("memberNick", member.get("member_nick"));			
				RedirectView rv = new RedirectView("/portfolio_01/index.do");
				mv.setView(rv);				
				return mv;	
				
			} catch (PasswordMissMatchException pme) {
				msg = pme.getMessage();
				
			} catch (MemberNotFoundException mnfe) {
				msg = mnfe.getMessage();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			mv.setViewName(page);
			mv.addObject("msg", msg);	
		}
	
		return mv;
	}
	
	@RequestMapping("/member/logout.do")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView("/portfolio_01/index.do");
		mv.setView(rv);
		
		return mv;
	}
	
	@RequestMapping("/member/mListPage.do")
	public ModelAndView mList(@RequestParam HashMap<String, Object> params) {
		logger.debug("params mListPage : "+params);
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/mList");
		return mv;
	}
	
	@RequestMapping("/member/mList.do")
	@ResponseBody
	public HashMap<String, Object> mListPage(@RequestParam HashMap<String, String> params) {
		logger.debug("params mList : "+params);
		ModelAndView mv = new ModelAndView();
		// 한 페이지 보여줄 게시글 수
		int rows = Integer.parseInt(params.get("rows"));
		// 현재 페이지
		int currentPage = Integer.parseInt(params.get("page"));
		// 시작 인덱스
		int startIdx = (currentPage - 1) * rows;		
		params.put("startIdx", String.valueOf(startIdx));
		// 전체 회원 수 구하기
		int totalMember = mService.mListCount(params);
		// 총 페이지 수
		int totalPageCnt = (int) Math.ceil((double) totalMember / rows);
		
		ArrayList<HashMap<String, Object>> mList = mService.mList(params);
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("page", params.get("page")); // 현재 페이지 
		result.put("total", totalPageCnt); // 총 페이지 수
		result.put("rows", mList); // 데이터(목록)
		result.put("records", totalMember); // 총 회원수
		return result;
		
	}
	
	@RequestMapping("/member/delMember.do")
	@ResponseBody
	public HashMap<String, String> delMember(@RequestParam HashMap<String, String> params) {
		logger.debug("params delMember : "+params);
		HashMap<String, String> map = new HashMap<String, String>();
		int cnt = mService.delMember(params);
		map.put("msg", (cnt==1)?"삭제되었습니다.":"삭제 싥패!");
		map.put("result", String.valueOf(cnt));
		return map;
	}
	
}
