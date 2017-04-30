package com.app.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.dao.InformationObtainer;
import com.app.model.QuestionAndAnswer;
import com.app.model.QuestionGetter;
import com.app.model.QuestionsAnswer;
import com.app.model.User;

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

	public String insert(HttpServletRequest request) {
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		QuestionAndAnswer questionAndAnswer = new QuestionAndAnswer();
		questionAndAnswer.setQuestion(question);
		questionAndAnswer.setAnswer(answer);

		int i = informationObtainer.insertQuetion(questionAndAnswer);
		String page = "";
		if (i == 1) {
			request.setAttribute("success", "The post was done successfully");
			page = "create";
		} else {
			request.setAttribute("error", "There was a error in doing the post or the question already exists");
			page = "admin";
		}

		return page;
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
			List user = informationObtainer.getUsers();
			ses.setAttribute("user", user);
		}
		return i;
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
	 *            calls getsAnswer of {@link InformationObtainer} and sets them
	 *            to the request attribute and returns int.
	 * @param request
	 * @return
	 */
	public String update(String question, HttpServletRequest request) {
		List list = informationObtainer.update(question);
		request.setAttribute("question", question);
		request.setAttribute("answer", list);
		return "update";

	}

	/**
	 * this method is called from mainClass and it returns the page of the
	 * choice made
	 * 
	 * @param request
	 * @return
	 */

	public String choice(HttpServletRequest request) {
		String str = request.getParameter("choice");
		HttpSession ses = request.getSession();
		ses.setAttribute("request", str);
		String page = "";
		if ("users".equalsIgnoreCase(str)) {
			List<User> list = informationObtainer.getUsers();
			request.setAttribute("user", list);
			page = "users";
		}
		if ("Question".equalsIgnoreCase(str)) {
			List<QuestionGetter> list = informationObtainer.getQuestion();
			System.out.println("unanswered");
			request.setAttribute("question", list);
			page = "getQuestion";
		}
		if ("questions".equalsIgnoreCase(str)) {
			List<QuestionGetter> list = informationObtainer.getQuestionsUnAnswered();
			request.setAttribute("question", list);
			page = "questions";
		}
		return page;
	}

	/**
	 * This method creates a model object and calls informationObtainer and
	 * returns a page
	 * 
	 * @param request
	 * @return
	 */

	public String addAnswer(HttpServletRequest request) {
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		QuestionAndAnswer qa = new QuestionAndAnswer();
		qa.setQuestion(question);
		qa.setAnswer(answer);
		int i = informationObtainer.addAnswersAdmin(qa);
		String page = "";
		if (i == 1) {
			request.setAttribute("success", "The answer has been successfully posted");
			page = "admin";
		} else {
			request.setAttribute("error", "The answer already exists, please try again");
			page = "admin";

		}
		return page;
	}

	/**
	 * This method obtains the question to be deleted and deleted the question
	 * 
	 * @param request
	 * @param delete
	 * @return
	 */

	public String delQuestion(HttpServletRequest request, String delete) {

		int i = informationObtainer.deleteQues(delete);
		if (i == 1) {
			List<QuestionGetter> list = informationObtainer.getQuestionsUnAnswered();
			request.setAttribute("question", list);
		}
		return "questions";
	}

	/**
	 * This method obtains the question to be deleted and deleted the question
	 * 
	 * @param delete:
	 * @param request
	 * @return
	 */

	public String deleteQues(String delete, HttpServletRequest request) {
		int i = informationObtainer.deleteQuestion(delete);
		if (i == 1) {
			List<QuestionGetter> list = informationObtainer.getQuestion();
			request.setAttribute("question", list);
		}
		return "getQuestion";
	}

	/**
	 * This method returns updateAns page
	 * 
	 * @param wanted
	 * @return
	 */

	public String updateValue(String wanted) {

		return "updateAns";
	}

	/**
	 * This method gets request and creates a model class object and calls
	 * informationObtainer
	 * 
	 * @param request
	 * @return
	 */
	public String updateAnswers(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		String answer = request.getParameter("answer");
		QuestionsAnswer qa = new QuestionsAnswer();
		qa.setId(id);
		qa.setAnswer(answer);
		int i = informationObtainer.updateAnswer(qa);
		String page = "";
		if (i == 1) {
			request.setAttribute("success", "The answer has been updated successfully");
			page = "admin";
		} else {
			request.setAttribute("error", "The answer cannot be updated, please try again");
			page = "admin";

		}
		return page;
	}

	/**
	 * 
	 * @param request
	 *            obtains request object from MainClass and adds the request
	 *            parameters to model object and calls
	 *            {@link InformationObtainer} to delete answer
	 * @return
	 */

	public String delAnswer(HttpServletRequest request) {
		QuestionsAnswer qa = new QuestionsAnswer();
		qa.setId(Integer.parseInt(request.getParameter("id")));
		System.out.println(qa.getId());
		qa.setAnswer(request.getParameter("wanted"));
		int i = informationObtainer.delAnswer(qa);
		String page = "";
		if (i == 1) {
			request.setAttribute("success", "The answer has been successfully deleted");
			page = "admin";
		} else {
			request.setAttribute("error", "The answer cannot be deleted, please try again");
			page = "admin";

		}
		return "admin";
	}

}
