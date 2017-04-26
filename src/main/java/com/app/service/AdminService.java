package com.app.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.dao.InformationObtainer;
import com.app.model.QuestionAndAnswer;
import com.app.model.QuestionsAnswer;

/**
 * 
 * this bean handles the service of the admin.
 *
 */
public class AdminService {

	@Autowired
	InformationObtainer informationObtainer;

	/**
	 * this method obtains the request parameters, validates whether the answer
	 * is empty and replaces them with a string if empty Later calls insert
	 * records of informationObtainer class to handle it further to DAO.
	 *
	 * @return : the integer value returned by insertRecords()
	 */

	public int insert(HttpServletRequest request) {
		String question = request.getParameter("question");
		String soft = request.getParameter("software");
		String hard = request.getParameter("hardware");

		if ("".equals(soft) || ("".equals(hard))) {
			soft = "no software solution present";
			hard = "no hardware solution present";
		}
		int j = informationObtainer.insertRecords(question, soft, hard);

		return j;
	}

	/**
	 * 
	 * @param delete
	 *            : tries to perform delete service of the admin
	 * @param request
	 *            : calls delete method of informationObtainer class by passing
	 *            the question to delete
	 * @return : returns a int, and a adds a object of {@link QuestionAndAnswer}
	 *         to session object which can be used in admin page if the delete
	 *         was successful
	 */

	public int delete(String delete, HttpServletRequest request) {
		int i = informationObtainer.delete(delete);
		if (i == 1) {
			HttpSession ses = request.getSession();
			List<QuestionAndAnswer> qAndA = informationObtainer.getQuestions();
			ses.setAttribute("Hardware", qAndA);
		}
		return i;
	}

	/**
	 * 
	 * @param request:
	 *            creates a session object and loads the object of
	 *            {@link QuestionAndAnswer} which is returned by getQuestions()
	 *            of {@link InformationObtainer}
	 */
	public void back(HttpServletRequest request) {
		HttpSession ses = request.getSession();
		List<QuestionAndAnswer> qAndA = informationObtainer.getQuestions();
		ses.setAttribute("Hardware", qAndA);
	}

	/**
	 * : logs out of the session by invalidating the session
	 * 
	 * @param request
	 */

	public void logout(HttpServletRequest request) {
		HttpSession ses = request.getSession();
		ses.invalidate();
	}

	/**
	 * 
	 * @param question
	 *            is a string read from Query String called from MainClass it
	 *            calls getHardwareAnswer of {@link InformationObtainer} and
	 *            sets them to the request attribute and returns int.
	 * @param request
	 * @return
	 */
	public int updateHardware(String question, HttpServletRequest request) {
		int i = 0;
		QuestionsAnswer qa = informationObtainer.getHardwareAnswer(question);
		request.setAttribute("id", qa.getId());
		request.setAttribute("question", qa.getQuestion());
		request.setAttribute("answer", qa.getAnswer());
		request.setAttribute("answerSoft", qa.getSoftAns());
		i = 1;
		return i;

	}

	/**
	 * it is called from MainClass it reads the values from the request object
	 * and calls {@link InformationObtainer} updteValues method which returns a
	 * int. based on the value returned this method takes the decision and
	 * places it in the request object
	 * 
	 * @param request
	 * @return
	 */

	public int updateValues(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		String hardAns = (request.getParameter("hardAns"));
		String softAns = (request.getParameter("softAns"));

		int i = informationObtainer.updateValues(id, hardAns, softAns);
		if (i == 1) {
			request.setAttribute("success", "The question has been updated successfully");
		} else {
			request.setAttribute("failurs", "sorry there was a problem in updating the question");
		}
		return i;

	}

}
