package com.app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.model.Login;
import com.app.model.QuestionAndAnswer;
import com.app.model.Register;

/**
 * 
 * This bean checksLogin, checks authorization, obtains questions and answers,
 * ans registers a user.
 *
 */
@Repository
public class ConnectionEstablisher {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	/**
	 * This method obtains information from login object ans adds it to the database
	 * @param login
	 * @return : integer
	 */

	public int checkLogin(Login login) {
		String query = "select count(*) from login where username like '" + login.getUserName()
				+ "' and password like '" + login.getPassword() + "'";
		return jdbcTemplate.queryForObject(query, Integer.class);
	}
	/**
	 * this method checks the authorization of the user login object
	 * @param login
	 * @return : integer
	 */

	public String authorizeCheck(Login login) {
		String query = "select auth from login where username like '" + login.getUserName() + "' and password like '"
				+ login.getPassword() + "'";
		return jdbcTemplate.queryForObject(query, String.class);
	}
	/**
	 * This method obtains the question and answers from the database
	 * and adds the object obtained to the list and returns the list object
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getQuestions() {
		final List<QuestionAndAnswer> list = new ArrayList<QuestionAndAnswer>();
		String query = "select question,answer from hardware_tab ";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
		for (Map row : rows) {
			QuestionAndAnswer q = new QuestionAndAnswer();
			q.setQuestion((String) row.get("question"));
			q.setAnswer((String) row.get("answer"));
			list.add(q);
		}
		return list;
	}
	/**
	 * This method obtains information from the register object and inserts the data into the table,
	 * if there is any problem in inserting the values it deletes the process of insert.
	 * @param register
	 * @return
	 */
	public int register(Register register) {
		int res = 0;
		int i = jdbcTemplate.update("insert into customer(firstname, lastName, email, username, password) values ('"
				+ register.getFirstName() + "' ,'" + register.getLastName() + "' , '" + register.getEmail() + "','"
				+ register.getUserName() + "','" + register.getPassword() + "')");
		if (i == 1) {
			int id = jdbcTemplate.queryForObject(
					"select id from customer where username like '" + register.getUserName() + "'", Integer.class);
			int j = jdbcTemplate
					.update("insert into login(username, password, auth, userid) values('" + register.getUserName()
							+ "','" + register.getPassword() + "','" + register.getAuth() + "'," + id + ")");
			if (j == 1) {
				res = 1;

			} 
		} else {
			jdbcTemplate.update("delete from customer where username like '" + register.getUserName() + "'");
			res = 0;

		}
		System.out.println("res from register"+res);
		return res;
	}

}
