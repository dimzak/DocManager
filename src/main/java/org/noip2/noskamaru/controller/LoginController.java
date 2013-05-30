package org.noip2.noskamaru.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/")
	public String login()	{
		return("login");
	}
	
	@RequestMapping("/login**")
	public String loginalt()	{
		return("login");
	}

}
