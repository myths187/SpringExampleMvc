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
	 * This method obtains a list of {@link QuestionAndAnswer} from {@link ConnectionEstablisher}
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<QuestionAndAnswer> getQuestions() {
		return connectionEstablisher.getQuestions();

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
	 * This methods tries to insert records into the database
	 * @param question
	 * @param soft
	 * @param hard
	 * @return
	 */

	public int insertRecords(String question, String soft, String hard) {
		return adminServices.insertRecords(question, soft, hard);
	}
	/**
	 * This method obtains {@link QuestionsAnswer} object from {@link AdminServices}
	 * @param question
	 * @return
	 */

	public QuestionsAnswer getHardwareAnswer(String question) {
		return adminServices.update(question);
	}
	/**
	 * this method tries to insert the updated values to the database through {@link AdminServices}
	 * @param id
	 * @param hardAns
	 * @param softAns
	 * @return
	 */

	public int updateValues(int id, String hardAns, String softAns) {
		return adminServices.updateValues(id, hardAns, softAns);
	}
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

	public int insertQuetion(QuestionAndAnswer questionAndAnswer) {
		int i =userServices.insertQuetion(questionAndAnswer) ;
		return i;
	}

	public int insertQuestion(QuestionGetter getQuestion) {
		int i = userServices.insertQuestion(getQuestion);
		return i;
	}

	public int addAnswers(QuestionAndAnswer qa) {
		int i = userServices.addAnswers(qa);
		return i;
	}

	public List getUsers() {
		List list = adminServices.getUsers();
		return list;
	}

	public List<QuestionGetter> getQuestionsUnAnswered() {
		
		return adminServices.getQuestions();
	}

}
