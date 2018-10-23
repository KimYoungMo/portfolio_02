package com.kym.pf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/index.do")
	public String index() {
		return "index";	
	}
	
	@RequestMapping("/goJoin.do")
	public String goJoin() {
		return "goJoin";	
	}
	
	@RequestMapping("/login.do")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/devNotes.do")
	public String devNotes() {
		return "devNotes";
	}
}
