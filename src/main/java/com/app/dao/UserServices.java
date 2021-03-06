package com.app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.app.model.QuestionAndAnswer;
import com.app.model.QuestionGetter;

/**
 * 
 * this bean provides user services like obtaining questions and answers
 *
 */
public class UserServices {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	/**
	 * based on the string passed this method obtains the list of {@link QuestionGetter} objects
	 * @param string
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getQuestion() {
		final List<QuestionGetter> list = new ArrayList<QuestionGetter>();

		String query = "select question from question";

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
			for (Map row : rows) {
				QuestionGetter question = new QuestionGetter();
				question.setQuestion((String) row.get("question"));
				list.add(question);
			
		

		}
		return list;
	}
	/**
	 * this method obtains the answer from tables based on the question and type
	 * @param question
	 * @return
	 */

	public List getAnswer(String question) {
		String query = "select answers from  answers where fk = (select id from question where question like '"+question+"')";
		final List<QuestionGetter> list = new ArrayList<QuestionGetter>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
		for (Map row : rows) {
			QuestionGetter answer = new QuestionGetter();
			answer.setQuestion((String) row.get("answers"));
			list.add(answer);
		}
		return list;

	}
	/**
	 * this method helps in inserting question and answer 
	 * @param questionAndAnswer
	 * @return
	 */
	public int insertQuetion(QuestionAndAnswer questionAndAnswer) {
		String question = questionAndAnswer.getQuestion();
		String answer = questionAndAnswer.getAnswer();
		String quest = "select count(*) from question where question like '"+question+"'";
		int res = jdbcTemplate.queryForObject(quest, Integer.class);
		if(res >= 1){
			return 0;
		}else{
			
		int i = 0;
		int j = 0;
		i = jdbcTemplate.update("insert into question (question)values ('" + question + "')");

		if (i != 0) {
			j = jdbcTemplate.update("insert into answers(answers,fk) values ('" + answer
					+ "' , ( select id from question where question like '" + question + "'))");
		}

		return j;
	}
	}
	/**helps in inserting question in queryQuestion
	 * 
	 * @param getQuestion
	 * @return
	 */
	public int insertQuestion(QuestionGetter getQuestion) {
		String question = getQuestion.getQuestion();
		String query1 = "select count(*) from question where question like '"+question+"'";
		int res = jdbcTemplate.queryForObject(query1, Integer.class);
		if (res >=1){
			return 0;
		}
		String query = "select count(*) from queryquestion where question like '"+question+"'";
		 res = jdbcTemplate.queryForObject(query, Integer.class);
		int j =0;
		if(res >= 1){
			return 0;
		}else{
			query = "insert into queryquestion(question) values ('"+question+"')";
			 j =jdbcTemplate.update(query);
		}
		
		return j;
	}
	/**
	 * Helps to add answers to existing question
	 * @param qa
	 * @return
	 */
	public int addAnswers(QuestionAndAnswer qa) {
		String query = "select count(*) from answers where answers like '"+qa.getAnswer()+"' and fk =(select id from question where question like '"+qa.getQuestion()+"')";
		int res = jdbcTemplate.queryForObject(query, Integer.class);
		System.out.println(res);
		int j =0;
		if(res >=1){
			return 0;
		}else{
		j = jdbcTemplate.update("insert into answers(answers,fk) values ('" + qa.getAnswer()
					+ "' , ( select id from question where question like '" + qa.getQuestion() + "'))");
		}
		return j;
	}

}
