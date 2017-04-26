package com.app.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.dao.InformationObtainer;
import com.app.model.QuestionAndAnswer;
import com.app.model.QuestionGetter;
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
		String page ="";
		if("question".equalsIgnoreCase(str)){
			page="question";
		}
		if("ask".equalsIgnoreCase(str)){
			page="ask";
		}
		if("obtain".equalsIgnoreCase(str)){
			List<QuestionAndAnswer> list = informationObtainer.getQuestion();
			request.setAttribute("question", list);
			page="obtain";
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
		
			String question = request.getParameter("wanted");
			
		List list = informationObtainer.getAnswer(question);
		HttpSession session = request.getSession();
			session.setAttribute("question", question);
			request.setAttribute("answer", list);
			page = "answer";
		
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
	public String insertQuestion(HttpServletRequest request) {
		String question = request.getParameter("question");
		String answer =request.getParameter("answer");
		QuestionAndAnswer questionAndAnswer = new QuestionAndAnswer();
		questionAndAnswer.setQuestion(question);
		questionAndAnswer.setAnswer(answer);
		
	int i=	informationObtainer.insertQuetion(questionAndAnswer);
	String page="";
	if(i==1){
		request.setAttribute("success", "The post was done successfully");
		page="question";
	}else{
		request.setAttribute("error", "There was a error in doing the post or the question already exists");
		page="user";
	}
		return page;
	}
	public String ask(HttpServletRequest request) {
		String question = request.getParameter("question");
		System.out.println(question);
		
		QuestionGetter getQuestion = new QuestionGetter();
		getQuestion.setQuestion(question);
	int i =	informationObtainer.insertQuestion(getQuestion);
	String page ="";
	if(i ==1){
		request.setAttribute("success", "The question is posted for answer");
		page = "ask";
	}else{
		request.setAttribute("error", "The question already exists, please wait until answer is posted");
		page="user";
	}
		return page;
	}
	public String addAnswers(HttpServletRequest request) {
		String question = request.getParameter("question");
		System.out.println(question);
		String answer = request.getParameter("answer");
		System.out.println(answer);
		QuestionAndAnswer qa = new QuestionAndAnswer();
		qa.setQuestion(question);
		qa.setAnswer(answer);
		int i =informationObtainer.addAnswers(qa);
		String page="";
		if(i==1){
			request.setAttribute("success", "The answwer has been successfully posted");
			page="user";
		}else{
			request.setAttribute("error", "The answer already exists, please try again");
			page="user";
			
		}
		return page;
	}

}
