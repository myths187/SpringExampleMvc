package com.app.service;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
/**
 * 
 *this bean validates the login credentials passed from login page to the main controller and provides
 *the boolean type of weather or not the login was successful.
 *
 */
@Service
public class Validate {
	/**
	 * 
	 * @param userName
	 * @param password
	 * @param request
	 * @return : returns a boolean if validation was success else returns false if it fails
	 * it adds the errors into a vector of errors and stores in request attribute. to be displayed in 
	 * the jsp
	 */

	@SuppressWarnings("unchecked")
	public boolean validate(String userName, String password, HttpServletRequest request) {
		@SuppressWarnings("rawtypes")
		Vector errors = new Vector();
		
			if (!validName(userName))
				errors.add("please enter valid user name");

			if (!validPassword(password))
				errors.add("please enter valid password");

			if (errors.size() == 0) {
				return true;
			} else {
				
				String[] errorArray = (String[]) errors.toArray(new String[0]);
				request.setAttribute("errors", errorArray);
				return false;
			}

		
	}
	/**
	 * 
	 * @param password
	 * @return : validation fails when password contains "." or ""
	 */
			private boolean validPassword(String password) {
				if ((password.contains(".") == false)|| ("".equals(password)))
					return true;
				else
					return false;
			}
/**
 * 
 * @param userName
 * @return : validation fails if the user name contains "," or ""
 */
			private boolean validName(String userName) {
				if ((userName.contains(",") == false)|| ("".equals(userName)))
					return true;
				else
					return false;
			}


}
