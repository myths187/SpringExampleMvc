package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.app.model.Login;
import com.app.model.Register;
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
	@RequestMapping(value="/choice",method=RequestMethod.GET)
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
	public ModelAndView register(@Valid @ModelAttribute("loginFormBackingObject")Register register, BindingResult result,HttpServletRequest request, HttpServletResponse response){
		String next ="";
		if (result.hasErrors()) {
			next="register";		 
		} else  {
			next=userService.register(register, request);
		}
		
		
		return new ModelAndView(next);
	}
	/**
	 * 
	 * @param request : GET  /getRegister from login page
	 * @param response : provides register page to the user
	 * @return
	 */
	@RequestMapping(value="/getRegister", method=RequestMethod.GET)
	public ModelAndView getRegister(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView("register","loginFormBackingObject",new Register());
	}
	 /**
	  * 
	  * @param request: request is obtained from ask page and returns page returned by userService
	  * @return
	  */
		@RequestMapping(value="/ask", method=RequestMethod.POST)
		public ModelAndView ask(HttpServletRequest request){
			return new ModelAndView(userService.ask(request));
		}
	
/**
 * 
 * @param request: obtains request from question page and returns page returned by userServices
 * @return
 */
	@RequestMapping(value = "/InsertQuestion", method = RequestMethod.POST)
	public ModelAndView insertQuestion(HttpServletRequest request){
		return new ModelAndView(userService.insertQuestion(request));
	}
	/**
	 * 
	 * @param request: obtain request from answer page and returns addAnswer page
	 * @return
	 */

	@RequestMapping(value = "/addAnswer", method = RequestMethod.GET)
	public ModelAndView addAnswer(HttpServletRequest request){
		return new ModelAndView("addAnswer");
	}
	/**
	 * 
	 * @param request: obtains request from addAnswers page and returns page returned by userService
	 * @return
	 */
	@RequestMapping(value = "/addAnswers", method = RequestMethod.GET)
	public ModelAndView addAnswers(HttpServletRequest request){
		return new ModelAndView(userService.addAnswers(request));
	}

}
