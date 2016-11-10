package com.spring.web.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.net.httpserver.Authenticator.Success;

@Controller
public class AuthenticationController {

//	@RequestMapping
//	public String Success(){
//		System.out.println("success");
//		return "success";
//	}
	
	@RequestMapping("/login")
	public String login(){
		System.out.println("LOGIN");
		return "login";
	}

	@RequestMapping("/loginSuccess")
	public String loginSuccess(){
		System.out.println("loginSuccess");
		return "success";
	}
	
	@RequestMapping("/logoutSuccess")
	public String logoutSuccess(){
		System.out.println("logout success");
		
		return "logout";
	}
}
