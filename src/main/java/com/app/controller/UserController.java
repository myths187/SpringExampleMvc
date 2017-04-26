package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.app.service.UserService;
/**
 * this controller bean maps request for UserTroubleShootingDesk and performs functions of the user like register,
 * to send answers to the request, or to validate the choices made by the user.
 * 
 *
 */
@EnableWebMvc
@Controller
@RequestMapping("/UserTroubleShootingDesk/*")
public class UserController {
	@Autowired
	UserService userService;
	/**
	 * 
	 * @param request : POST /choice
	 * @param response : returns the page decided by getChoice() of the UserService class
	 * @return
	 */
	@RequestMapping(value="/choice",method=RequestMethod.POST)
	public ModelAndView question(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView(userService.getChoice(request));
	}
	/**
	 * 
	 * @param request : POST /answer.. answers the selected question from the user
	 * @param response : returns the answer which will be posted in the page returned by the 
	 * getAnswer() of the UserService class
	 * @return
	 */
	
	@RequestMapping(value="/answer", method=RequestMethod.POST)
	public ModelAndView answer(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView(userService.getAnswer(request));
	}
	/**
	 * 
	 * @param request : GET /back. returns home page of the user from any request of back
	 * @return
	 */
	
	@RequestMapping(value="/back", method=RequestMethod.GET)
	public ModelAndView back(HttpServletRequest request){
			userService.back(request);
		return new ModelAndView("user");
	}
	/**
	 * 
	 * @param request : POST 
	 * @param response : calls register() of UserService class
	 * @return : returns the page returned by the userService method.
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView(userService.register(request));
	}
	/**
	 * 
	 * @param request : GET  /getRegister from login page
	 * @param response : provides register page to the user
	 * @return
	 */
	@RequestMapping(value="/getRegister", method=RequestMethod.GET)
	public ModelAndView getRegister(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView("register");
	}

}
