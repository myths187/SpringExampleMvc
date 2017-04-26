package com.app.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.dao.InformationObtainer;
/**
 * 
 * This bean obtains the choices made by the user, makes back decision, gets Answers to the user,
 * registers the user
 *
 */
public class UserService {

	@Autowired
	InformationObtainer informationObtainer;
	
	@Autowired
	RegisterValidate registerValidate;
	/**
	 * 
	 * @param request
	 * @return: decides on the choice made by the user and obtains he list of Questions from 
	 * {@link InformationObtainer} and returns respective pages to the user by placing the questions in request.
	 */

	public String getChoice(HttpServletRequest request) {
		String str = request.getParameter("choice");
		HttpSession ses = request.getSession();
		ses.setAttribute("request", str);
		String page = "";

		if ("user_hardware".equalsIgnoreCase(str)) {
			@SuppressWarnings("rawtypes")
			List list = informationObtainer.getQuestion("hardware");
			request.setAttribute("question", list);
			page = "UserHardware";
		}

		if (("user_software".equalsIgnoreCase(str))) {
			@SuppressWarnings("rawtypes")
			List soft_list = informationObtainer.getQuestion("software");
			request.setAttribute("question", soft_list);
			request.setAttribute("request", "software");
			page = "UserSoftware";

		}

		return page;
	}
	/**
	 * 
	 * @param request
	 * @return : obtains the answers based on the selected question, first this method reads the choice made 
	 * by the user and then the question choice took, later it assigns the answer to request
	 * ans then returns the answer page which renders the answer
	 */

	public String getAnswer(HttpServletRequest request) {
		HttpSession ses = request.getSession();
		String str = (String) ses.getAttribute("request");
		String page = "";
		if ("user_hardware".equalsIgnoreCase(str)) {
			String question = request.getParameter("wanted");
			String list2 = informationObtainer.getAnswer(question, "hardware");
			request.setAttribute("answer", list2);
			page = "answer";
		}

		if (("user_software".equalsIgnoreCase(str))) {
			String question = request.getParameter("wanted");
			String soft_list2 = informationObtainer.getAnswer(question, "software");
			request.setAttribute("answer", soft_list2);
			page = "answer";
		}
		return page;
	}
	/**
	 * 
	 * @param request
	 * this method shifts the control to user.jsp by calling getChoice() 
	 */

	public void back(HttpServletRequest request) {
		getChoice(request);
	}
	/**
	 * 
	 * @param request
	 * @return : calls validate method of registerValidate class and returns the page in string format
	 */
	public String register(HttpServletRequest request) {
		
		return registerValidate.doValidate(request);
	}

}
