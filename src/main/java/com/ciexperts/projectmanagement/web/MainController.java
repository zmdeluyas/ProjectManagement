package com.ciexperts.projectmanagement.web;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ciexperts.projectmanagement.entity.AppUser;
import com.ciexperts.projectmanagement.service.UserService;

import ch.qos.logback.classic.Logger;

@Controller
@SessionAttributes("appUser")
public class MainController {

	private final Logger logger = (Logger) LoggerFactory.getLogger(MainController.class);
	private UserService userService = new UserService();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView model = new ModelAndView("index");
		return model;
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main(){
		ModelAndView model = new ModelAndView("/common/main");
		return model;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	public String login(@RequestParam String username, @RequestParam String password, HttpSession session){
		String result = "failed";
		//ARVIC
		if(username != null && password != null){
			HashMap<String, Object> userInfo = userService.authenticateUser(username, password);
				if (userInfo.get("result").equals("success") && userInfo.get("result") != null){
					String name = (String) userInfo.get("fullName");
					String access = (String) userInfo.get("access");
					session.setAttribute("appUser", new AppUser(username, name,access));
					result = "success";
				}
		}
		return result;
	}
	
}
