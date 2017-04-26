package com.app.service;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.model.Register;

/**
 * 
 * This bean performs validation service. it returns success page that is login
 * page if the validation is successful else prints list of errors on the
 * register page
 * 
 *
 */
public class RegisterValidate {

	@Autowired
	Register register;

	@Autowired
	LoginService loginService;

	/**
	 * 
	 * @param request
	 *            This method calls loginService register method to register the
	 *            value if the validation is successful if the method returns 1
	 *            this method returns success page that is login else a error
	 *            page if registration was not successful if errros are detected
	 *            they are stored in a Vector of errors and set to request to be
	 *            displayed in register page again
	 * @return :
	 */
	public String doValidate(HttpServletRequest request) {
		String firstName = request.getParameter("firstName".trim());
		String auth = request.getParameter("auth".trim());
		String lastName = request.getParameter("lastName".trim());
		String email = request.getParameter("email".trim());
		String userName = request.getParameter("userName".trim());
		String password = request.getParameter("password".trim());
		@SuppressWarnings("rawtypes")
		Vector errors = new Vector();
		String next = "";

		if (!validfirstName(firstName))
			errors.add("please enter firstName");
		if (!validLastName(lastName))
			errors.add("please enter lastName");
		if (!validEmail(email))
			errors.add("please enter valid email");

		if (errors.size() == 0) {
			register.setLastName(lastName);
			register.setFirstName(firstName);
			register.setAuth(auth);
			register.setEmail(email);
			register.setUserName(userName);
			register.setPwd1(password);

			loginService.register(register);
			next = "login";

		} else {
			if (errors.size() != 0) {
				System.out.println(userName);
				System.out.println(password);
				System.out.println(errors.size());
				String[] errorArray = (String[]) errors.toArray(new String[0]);
				request.setAttribute("errors", errorArray);
				next = "register";
			}

		}

		return next;
	}

	/**
	 * 
	 * @param email
	 * @return : checks for instance of "@"
	 */

	private boolean validEmail(String email) {
		if (email.contains("@"))
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @param lastName
	 * @return : checks if lastname is null, "" or has ","
	 */

	private boolean validLastName(String lastName) {
		if (lastName.equals(null) || (lastName.equals("") || (lastName.contains(","))))
			return false;
		else
			return true;
	}

	/**
	 * 
	 * @param firstName
	 * @return : checks for first name is null, "" or has ","
	 */
	private boolean validfirstName(String firstName) {
		if (firstName.equals(null) || (firstName.equals("") || (firstName.contains(","))))
			return false;
		else
			return true;
	}

}
