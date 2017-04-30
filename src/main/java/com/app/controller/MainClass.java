package com.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.app.model.Login;
import com.app.service.AdminService;
import com.app.service.LoginService;

/**
 * 
 * This Controller bean maps the requests for like Login, insert, delete,
 * update, logout, it enables mvc configurations and maps request for
 * /TroubleShootingDesk/*
 * 
 *
 */
@EnableWebMvc
@ComponentScan(basePackages = "com.app")
@Controller
@RequestMapping("/TroubleShootingDesk/*")
public class MainClass {
	@Autowired
	LoginService loginService;

	@Autowired
	AdminService adminService;

	@Autowired
	Login login;

	/**
	 * This method returns login page with login object to validate
	 * 
	 * @return
	 */

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public ModelAndView getLoginPage() {
		return new ModelAndView("login", "loginFormBackingObject", new Login());
	}

	/**
	 * 
	 * @param request
	 *            : POST Maps request for Login from login page--- validates the
	 *            input by calling validate method
	 * @return : if validation is successful it returns the next page which is
	 *         either user or admin by calling LoginService check message method
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public ModelAndView login(@Valid @ModelAttribute("loginFormBackingObject") Login login, BindingResult result,
			HttpServletRequest request) throws ServletException, IOException {

		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String next = "";
		if (result.hasErrors()) {
			next = "login";
		} else {
			System.out.println("no");
			HttpSession ses = request.getSession();
			ses.setAttribute("name", userName);
			next = loginService.check(login, request);
		}
		return new ModelAndView(next);

	}

	/**
	 * 
	 * @param request
	 *            : POST Maps request for Insert, from Create page, calls
	 *            insert() of AdminService class
	 * @return : if the method returns 0 --> fail else success
	 * @throws ServletException
	 * @throws IOException
	 */

	@RequestMapping(value = "/Insert", method = RequestMethod.POST)
	public ModelAndView insertQuestion(HttpServletRequest request) throws ServletException, IOException {
		return new ModelAndView(adminService.insert(request));
	}

	@RequestMapping(value = "/choice", method = RequestMethod.GET)
	public ModelAndView choice(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView(adminService.choice(request));
	}

	/**
	 * This method deletes the user specified
	 * 
	 * @param delete
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public ModelAndView deleteQuestion(
			@RequestParam(value = "wanted", defaultValue = "", required = false) String delete,
			HttpServletRequest request) throws ServletException, IOException {
		int i = adminService.delete(delete, request);
		if (i == 1) {
			request.setAttribute("success", "the user is deleted successfully");
		} else {
			request.setAttribute("faliure", "could not delete the user");
		}
		return new ModelAndView("admin");

	}

	/**
	 * 
	 * @param request
	 *            : GET gets request from admin page create
	 * @return returns create page
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request) throws ServletException, IOException {

		return new ModelAndView("create");

	}

	/**
	 * 
	 * @param update
	 * @param request
	 *            : GET obtains request from admin page
	 * @return : returns update page
	 * @throws ServletException
	 * @throws IOException
	 */

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam(value = "wanted", defaultValue = "", required = false) String update,
			HttpServletRequest request) throws ServletException, IOException {
		return new ModelAndView(adminService.update(update, request));

	}

	/**
	 * 
	 * @param delete
	 *            :GET : Maps request for delete from admin page, calls delete()
	 *            of AdminService class by passing the question which is read
	 *            from Query String
	 * @return the refreshed page of admin and also the message of success or
	 *         failure
	 * @throws ServletException
	 * @throws IOException
	 */

	@RequestMapping(value = "/deleteQuestion", method = RequestMethod.GET)
	public ModelAndView deleteQues(@RequestParam(value = "wanted", defaultValue = "", required = false) String delete,
			HttpServletRequest request) throws ServletException, IOException {
		return new ModelAndView(adminService.deleteQues(delete, request));

	}

	/**
	 * 
	 * @param request
	 *            : GET from various pages
	 * @return returns login page after invalidating session.
	 * @throws ServletException
	 * @throws IOException
	 */

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) throws ServletException, IOException {
		adminService.logout(request);
		return new ModelAndView("index");
	}

	/**
	 * request: POST it maps the request for /updateValue from Update page calls
	 * updateValues() of adminService class
	 */
	@RequestMapping(value = "/updateValue", method = RequestMethod.GET)
	public ModelAndView updateValue(@RequestParam(value = "wanted", defaultValue = "", required = false) String wanted,
			HttpServletRequest request) throws ServletException, IOException {
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("Answer", wanted);
		return new ModelAndView(adminService.updateValue(wanted));

	}

	/**
	 * 
	 * @param request
	 *            gets request from questions page and returns addAnswerAdmin
	 *            page
	 * @return
	 */
	@RequestMapping(value = "/submitAns", method = RequestMethod.GET)
	public ModelAndView submitAns(HttpServletRequest request) {
		String question = request.getParameter("question");
		HttpSession session = request.getSession();
		session.setAttribute("question", question);
		return new ModelAndView("addAnswerAdmin");
	}

	/**
	 * 
	 * @param request
	 *            : returnd admin page
	 * @return
	 */

	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public ModelAndView back(HttpServletRequest request) {
		return new ModelAndView("admin");
	}

	/**
	 * 
	 * @param request
	 *            : gets request from updateAns page and returns page from
	 *            adminService
	 * @return
	 */
	@RequestMapping(value = "/updateAnswers", method = RequestMethod.GET)
	public ModelAndView updateAnswers(HttpServletRequest request) {

		return new ModelAndView(adminService.updateAnswers(request));
	}

	/**
	 * 
	 * @param request:
	 *            get request from addAnswer and returns page from adminService
	 * @return
	 */

	@RequestMapping(value = "/addAnswers", method = RequestMethod.POST)
	public ModelAndView addAnswers(HttpServletRequest request) {
		return new ModelAndView(adminService.addAnswer(request));
	}

	/**
	 * 
	 * @param request
	 *            : request from questions page and returns page from
	 *            adminService
	 * @param delete
	 * @return
	 */

	@RequestMapping(value = "/delQues", method = RequestMethod.GET)
	public ModelAndView delQues(HttpServletRequest request,
			@RequestParam(value = "question", defaultValue = "", required = false) String delete) {
		return new ModelAndView(adminService.delQuestion(request, delete));
	}

	/**
	 * 
	 * @param request:
	 *            obtains request from update page and returns page returned
	 *            from adminService
	 * @return
	 */
	@RequestMapping(value = "/deleteAns", method = RequestMethod.GET)
	public ModelAndView deleteAns(HttpServletRequest request) {
		return new ModelAndView(adminService.delAnswer(request));
	}

}
