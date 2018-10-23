package com.tj.controller;

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

import com.tj.dto.Member;
import com.tj.exception.MemberNotFoundException;
import com.tj.exception.PasswordMissMatchException;
import com.tj.service.MemberService;

@Controller
public class MemberController {
	Logger logger = Logger.getLogger(MemberController.class);
	// Service DI 구현 코드
	@Autowired
	private MemberService mService;

	/**
	 * @param memberInfo
	 * @return ModelAndView
	 */
	@RequestMapping("/member/join.do")
	public ModelAndView join(@RequestParam HashMap<String, Object> memberInfo) {
		logger.debug("Result memberInfo : " + memberInfo);
		String msg = (mService.join(memberInfo) == 1) ? "회원가입 성공!!" : "회원가입 실패!!";

		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		mv.addObject("msg", msg);

		return mv;
	}

	/**
	 * @param userId
	 * @param userPw
	 * @return ModelAndView
	 */
	@RequestMapping("/member/login.do")
	public ModelAndView login(String userId, String userPw, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		String page = "login";
		String msg = "시스템 에러!!!";

		if(session.getAttribute("memberId") != null) {
			RedirectView rv = new RedirectView("/s06/index.do");
			mv.setView(rv);
		} else {
			try {
				Member member = mService.login(userId, userPw);
				session.setAttribute("typeSeq", member.getTypeSeq());
				session.setAttribute("memberId", member.getMemberId());
				session.setAttribute("memberName", member.getMemberName());
				session.setAttribute("memberNick", member.getMemberNick());
				
				RedirectView rv = new RedirectView("/s06/index.do");
				mv.setView(rv);
				
				return mv;
				
			} catch (PasswordMissMatchException pme) {		//논리적 에러를 잡기 위한 catch
				msg = pme.getMessage();
				
			} catch (MemberNotFoundException mnfe) {		//논리적 에러를 잡기 위한 catch
				msg = mnfe.getMessage();
				
			} catch (Exception e) {		//런타임 에러를 잡기 위한 catch
				e.printStackTrace();
			}
			
			mv.setViewName(page);
			mv.addObject("msg", msg);
		}
		
		return mv;
	}

	@RequestMapping("/member/goLoginPage.do")
	public ModelAndView goLoginPage(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		if(session.getAttribute("memberId") != null) {
			RedirectView rv = new RedirectView("/s06/index.do");
			mv.setView(rv);
		} else {
			mv.setViewName("login");
		}
		
		return mv;
	}
	
	@RequestMapping("/member/logout.do")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView("/s06/index.do");
		mv.setView(rv);
				
		return mv;
	}
	
	@RequestMapping("/member/checkId.do")
	@ResponseBody
	public int checkId(@RequestParam HashMap<String, String> params) {
		logger.debug("/member/checkId.do params : " + params);
		ModelAndView mv = new ModelAndView();
//		logger.debug("Result : "+result);
		int result = mService.checkId(params);
		
		logger.debug("Result result : " + result);
		
		return result;
	}
}
