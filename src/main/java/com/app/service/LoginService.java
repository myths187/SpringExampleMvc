package com.app.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.InformationObtainer;
import com.app.model.Login;
import com.app.model.QuestionAndAnswer;
import com.app.model.Register;
/**
 * 
 * This bean performs login checks such as authorization and registering a user.
 *
 */
@Service
public class LoginService {
	@Autowired
	InformationObtainer informationObtainer;
	/**
	 * 
	 * @param login
	 * @param request
	 * @return : gets the call from mainClass bean and it calls check method of {@link InformationObtainer} and based on the integer returned
	 * it makes a decision and returns the responding page, it adds a list of {@link QuestionAndAnswer} object obtained from getQuestions method
	 *  to the session if the authentication
	 *  is done as admin by authorize method of {@link InformationObtainer}.
	 */
	public String check(Login login, HttpServletRequest request) {
	
		String page = "";

		int i = informationObtainer.check(login);

		if (i == 1) {

			String auth = informationObtainer.authorize(login);
			if ("admin".equalsIgnoreCase(auth)) {
				HttpSession ses = request.getSession();
				List<QuestionAndAnswer> qAndA = informationObtainer.getQuestions();
				ses.setAttribute("Hardware", qAndA);
				page = "admin";
			} else {
				page = "user";
			}

		} else {
			page = "error";
			
		}

		return page;
	}
	/**
	 * 
	 * @param register
	 * @param request
	 * @return : gets request from RegisterValidate bean and calls register method of {@link InformationObtainer} and returns the integer
	 */

	public int register(Register register) {
		int i =informationObtainer.register(register);
		return i;
	}

}
