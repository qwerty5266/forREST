package com.mvc.forrest.controller.common;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.forrest.config.auth.LoginUser;
import com.mvc.forrest.service.domain.User;
import com.mvc.forrest.service.firebase.FCMService;



@Controller
@RequestMapping("/firebase/*")
public class FirebaseController {
	
	@Autowired
	public FCMService fcmService;	
	
	@GetMapping("message")
	public String getAccessToken() throws Exception{
		
		return "common/firebase";
	} 
	
	@GetMapping("messageTest")
	public String messageTest(Model model, HttpSession session ) throws Exception {
		LoginUser loginUser= (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId= loginUser.getUser().getUserId();
		fcmService.sendMessage(userId);
		System.out.println("메세지보냄");
		 return "common/firebase";
	}	
	
}
