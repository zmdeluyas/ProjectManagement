package com.ciexperts.projectmanagement.web;



import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/logout")
public class LogoutController {
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView logout(HttpSession session){
		ModelAndView model = new ModelAndView("/common/login");
		session.invalidate();
		return model;
	}

}
