package com.app.dao;

import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.model.Login;
import com.app.model.QuestionAndAnswer;
import com.app.model.QuestionGetter;
import com.app.model.QuestionsAnswer;
import com.app.model.Register;

/**
 * 
 * This bean covers all the database operations
 *
 */
@Repository
public class InformationObtainer {
	@Autowired
	ConnectionEstablisher connectionEstablisher;

	@Autowired
	AdminServices adminServices;

	@Autowired
	UserServices userServices;
	Statement stmt;

	/**
	 * this method obtains authorization of the user through login object and
	 * returns the string
	 * 
	 * @param login
	 * @return : String
	 * 
	 */
	public String authorize(Login login) {

		String auth = connectionEstablisher.authorizeCheck(login);

		return auth;
	}

	/**
	 * This method checks the user login status through login object
	 * @param login
	 * @return : integer
	 */
	public int check(Login login) {
		int i = 0;

		int b = connectionEstablisher.checkLogin(login);

		if (b == 1) {
			i = 1;
		}

		return i;

	}
	
	/**
	 * this method tries to delete a entry through String
	 * @param delete
	 * @return
	 */

	public int delete(String delete) {
		return adminServices.delete(delete);
	}
	
	/**
	 * This method obtains {@link QuestionsAnswer} object from {@link AdminServices}
	 * @param question
	 * @return
	 */

	public List update(String question) {
		return adminServices.update(question);
	}
	/**
	 * this method tries to insert the updated values to the database through {@link AdminServices}
	 * @param id
	 * @param hardAns
	 * @param softAns
	 * @return
	 */

	
	/**
	 * This method obtains questions  based on type of String pased
	 * @param string
	 * @return
	 */

	@SuppressWarnings("rawtypes")
	public List getQuestion() {
		return userServices.getQuestion();
	}
	/**
	 * This method obtains answer in form of String
	 * @param question
	 * @return
	 */

	public List getAnswer(String question) {
		return userServices.getAnswer(question);
	}
	/**
	 * This method tries to register the user into database
	 * @param register
	 * @return
	 */

	public int register(Register register) {
		int i = connectionEstablisher.register(register);
		return i;
	}
	/**
	 * 
	 * @param questionAndAnswer : calls userService insertQuetion method
	 * @return
	 */

	public int insertQuetion(QuestionAndAnswer questionAndAnswer) {
		int i =userServices.insertQuetion(questionAndAnswer) ;
		return i;
	}
	/**
	 * calls user service insertQuestion method
	 * @param getQuestion
	 * @return
	 */

	public int insertQuestion(QuestionGetter getQuestion) {
		int i = userServices.insertQuestion(getQuestion);
		return i;
	}
/**
 *  used to add extra ansers
 * @param qa
 * @return
 */
	public int addAnswers(QuestionAndAnswer qa) {
		int i = userServices.addAnswers(qa);
		return i;
	}
/**
 * obtains list of users
 * @return
 */
	public List getUsers() {
		List list = adminServices.getUsers();
		return list;
	}
/**
 * returns list of questions
 * @return
 */
	public List<QuestionGetter> getQuestionsUnAnswered() {
		
		return adminServices.getQuestions();
	}

/**
 * Add answers made by admin
 * @param qa
 * @return
 */


	public int addAnswersAdmin(QuestionAndAnswer qa) {
		int i = adminServices.addAnswer(qa);
		return i;
	}
/**
 * deletes the existing questions
 * @param delete
 * @return
 */
	public int deleteQues(String delete) {
		int i = adminServices.deleteQues(delete) ;
		return i;
	}
	/**
	 * deletes existing questions
	 * @param delete
	 * @return
	 */

	public int deleteQuestion(String delete) {
		int i = adminServices.deleteQuestion(delete) ;
		return i;
	}
/**
 * helps in updating answer
 * @param qa
 * @return
 */
	public int updateAnswer(QuestionsAnswer qa) {
		int i = adminServices.updateAnswer(qa);
		return i;
	}
/**
 * helps in deleting answer
 * @param qa
 * @return
 */
	public int delAnswer(QuestionsAnswer qa) {
		int i = adminServices.delAns(qa);
		return i;
	}

}
