package com.app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

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
	public List getQuestion(String string) {
		final List<QuestionGetter> list = new ArrayList<QuestionGetter>();

		String query = "select question from hardware_tab";
		String query1 = "select question from software_tab";

		if ("hardware".equalsIgnoreCase(string)) {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
			for (Map row : rows) {
				QuestionGetter getQuestion = new QuestionGetter();
				getQuestion.setQuestion((String) row.get("question"));
				list.add(getQuestion);
			}
		}
		if ("software".equalsIgnoreCase(string)) {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(query1);
			for (Map row : rows) {
				QuestionGetter getQuestion = new QuestionGetter();
				getQuestion.setQuestion((String) row.get("question"));
				list.add(getQuestion);
			}

		}
		return list;
	}
	/**
	 * this method obtains the answer from tables based on the question and type
	 * @param question
	 * @param type
	 * @return
	 */

	public String getAnswer(String question, String type) {
		String query1 = "select answer from hardware_tab where question like '" + question + "'";
		String query2 = "select answer from software_tab where question=(select question from hardware_tab where question like '"
				+ question + "')";
		String ans = "";
		if ("hardware".equalsIgnoreCase(type)) {
			ans = jdbcTemplate.queryForObject(query1, String.class);
		}
		if ("software".equalsIgnoreCase(type)) {
			ans = jdbcTemplate.queryForObject(query2, String.class);

		}
		return ans;

	}

}
