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
		String query = "select count(*) from question";
		int a = jdbcTemplate.queryForObject(query, Integer.class);
		a = a + 1;
		i = jdbcTemplate.update("insert into question values (" + a + " ,'" + question + "')");

		if (i != 0) {
			String query1 = "select count(*) from answers";
			int b = jdbcTemplate.queryForObject(query1, Integer.class);
			b = b + 1;
			j = jdbcTemplate.update("insert into answers values (" + b + " ,'" + answer
					+ "' , ( select id from question where question like '" + question + "'))");
		}

		return j;
	}
	}
	public int insertQuestion(QuestionGetter getQuestion) {
		String question = getQuestion.getQuestion();
		String query = "select count(*) from queryquestion where question like '"+question+"'";
		int res = jdbcTemplate.queryForObject(query, Integer.class);
		int j =0;
		if(res >= 1){
			return 0;
		}else{
			query = "insert into queryquestion(question) values ('"+question+"')";
			 j =jdbcTemplate.update(query);
		}
		
		return j;
	}
	public int addAnswers(QuestionAndAnswer qa) {
		String query = "select count(*) from answers where answers like '"+qa.getAnswer()+"'";
		int res = jdbcTemplate.queryForObject(query, Integer.class);
		System.out.println(res);
		int j =0;
		if(res >=1){
			return 0;
		}else{
			String query1 = "select count(*) from answers";
			int b = jdbcTemplate.queryForObject(query1, Integer.class);
			b = b + 1;
		j = jdbcTemplate.update("insert into answers values (" + b + " ,'" + qa.getAnswer()
					+ "' , ( select id from question where question like '" + qa.getQuestion() + "'))");
		}
		return j;
	}

}
