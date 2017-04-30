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
		String answer =request.getParameter("answer");
		QuestionAndAnswer questionAndAnswer = new QuestionAndAnswer();
		questionAndAnswer.setQuestion(question);
		questionAndAnswer.setAnswer(answer);
		
	int i=	informationObtainer.insertQuetion(questionAndAnswer);
	String page="";
	if(i==1){
		request.setAttribute("success", "The post was done successfully");
		page="create";
	}else{
		request.setAttribute("error", "There was a error in doing the post or the question already exists");
		page="admin";
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
	public String update(String question, HttpServletRequest request) {
		List list = informationObtainer.update(question);
		request.setAttribute("question", question);
		request.setAttribute("answer", list);
		return "update";

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

	

	public  String submitAns(HttpServletRequest request) {
		String ans = request.getParameter("answer");
		String question  = request.getParameter("question");
		QuestionAndAnswer qa = new QuestionAndAnswer();
		qa.setAnswer(ans);
		qa.setQuestion(question);
		int i =informationObtainer.submitAns(qa);
		String page ="";
		if(i==1){
			request.setAttribute("success", "The answer has been submitted to the question");
			page="admin";
		}else{
			request.setAttribute("error", "There was a error in submitting the answer");
			page="admin";
		}
		return page;
	}

	public String choice(HttpServletRequest request) {
		String str = request.getParameter("choice");
		HttpSession ses = request.getSession();
		ses.setAttribute("request", str);
		String page ="";
		if("users".equalsIgnoreCase(str)){
			List<User> list = informationObtainer.getUsers();
			request.setAttribute("user", list);
			page="users";
		}
		if("Question".equalsIgnoreCase(str)){
			List<QuestionGetter> list = informationObtainer.getQuestion();
			System.out.println("unanswered");
			request.setAttribute("question", list);
			page="getQuestion";
		}
		if("questions".equalsIgnoreCase(str)){
			List<QuestionGetter> list = informationObtainer.getQuestionsUnAnswered();
			request.setAttribute("question", list);
			page = "questions";
		}
		return page;
	}

	public String addAnswer(HttpServletRequest request) {
		String question = request.getParameter("question");
		System.out.println(question);
		String answer = request.getParameter("answer");
		System.out.println(answer);
		QuestionAndAnswer qa = new QuestionAndAnswer();
		qa.setQuestion(question);
		qa.setAnswer(answer);
		int i =informationObtainer.addAnswersAdmin(qa);
		String page="";
		if(i==1){
			request.setAttribute("success", "The answer has been successfully posted");
			page="admin";
		}else{
			request.setAttribute("error", "The answer already exists, please try again");
			page="admin";
			
		}
		return page;
	}

	public String delQuestion(HttpServletRequest request, String delete) {
		
		int i = informationObtainer.deleteQues(delete);
		if (i == 1) {
			List<QuestionGetter> list = informationObtainer.getQuestionsUnAnswered();
			request.setAttribute("question", list);
		}
		return "questions";
	}

	public String deleteQues(String delete, HttpServletRequest request) {
		int i = informationObtainer.deleteQuestion(delete);
		if (i == 1) {
			List<QuestionGetter> list = informationObtainer.getQuestion();
			request.setAttribute("question", list);
		}
		return "getQuestion";
	}

	
	public String updateValue(String wanted) {
		
		return "updateAns";
	}

	public String updateAnswers(HttpServletRequest request) {
		int id= Integer.parseInt(request.getParameter("id"));
		String answer = request.getParameter("answer");
		QuestionsAnswer qa = new QuestionsAnswer();
		qa.setId(id);
		qa.setAnswer(answer);
		int i= informationObtainer.updateAnswer(qa);
		String page ="";
		if(i==1){
			request.setAttribute("success", "The answer has been updated successfully");
			page="admin";
		}else{
			request.setAttribute("error", "The answer cannot be updated, please try again");
			page="admin";
			
		}
		return page;
	}

	public String delQuestion(HttpServletRequest request) {
		
		return null;
	}

	public String delAnswer(HttpServletRequest request) {
		QuestionsAnswer qa = new QuestionsAnswer();
		qa.setId(Integer.parseInt(request.getParameter("id")));
		System.out.println(qa.getId());
		qa.setAnswer(request.getParameter("wanted"));
		int  i = informationObtainer.delAnswer(qa);
		String page ="";
		if(i==1){
			request.setAttribute("success", "The answer has been successfully deleted");
			page="admin";
		}else{
			request.setAttribute("error", "The answer cannot be deleted, please try again");
			page="admin";
			
		}
		return "admin";
	}

}
