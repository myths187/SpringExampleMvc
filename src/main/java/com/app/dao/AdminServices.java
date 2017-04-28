package com.app.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.model.QuestionAndAnswer;
import com.app.model.QuestionGetter;
import com.app.model.QuestionsAnswer;
import com.app.model.User;
/**
 * 
 * This bean inserts records, delete records, get the information to update and updates records
 *
 */
@Repository
public class AdminServices {
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
/**
 * 
 * @param question
 * @param soft
 * @param hard
 * this method is called from information obtainer.
 * @return  : integer
 */
	public int insertRecords(String question, String soft, String hard) {
		String quest = "select count(*) from hardware_tab where question like '"+question+"'";
		int res = jdbcTemplate.queryForObject(quest, Integer.class);
		if(res == 1){
			return 0;
		}else{
			
		int i = 0;
		int j = 0;
		String query = "select count(*) from hardware_tab";
		int a = jdbcTemplate.queryForObject(query, Integer.class);
		a = a + 1;
		i = jdbcTemplate.update("insert into hardware_tab values (" + a + " ,'" + question + "' , '" + hard + "')");

		if (i != 0) {
			String query1 = "select count(*) from software_tab";
			int b = jdbcTemplate.queryForObject(query1, Integer.class);
			b = b + 1;
			j = jdbcTemplate.update("insert into software_tab values (" + b + " ,'" + question + "' , '" + soft
					+ "' , ( select id from hardware_tab where question like '" + question + "'))");
		}

		return j;
	}
	}
	/**
	 * this method deletes the records
	 * @param delete
	 * @return : integer
	 */

	public int delete(String delete) {
		int i = 0;
	
			i = jdbcTemplate.update("delete from login where username like '"+delete+"'");
		if(i==1){
		
			i=1;
		}
		
		return i;
	}
	/**
	 * This method returns {@link QuestionsAnswer} object and sets the values obtained from the database
	 * to the object
	 * @param question
	 * @return : {@link QuestionsAnswer}
	 */
	
	public List update(String question){
		String query1 =  "select id,answers from answers where fk = ( select id from question where question like '"+question+"')";
		List list = new ArrayList();
		List<Map<String, Object>>  res= jdbcTemplate.queryForList(query1);
		QuestionsAnswer qa = new QuestionsAnswer();
		for (@SuppressWarnings("rawtypes") Map row : res) {
			
			qa.setId((Integer) row.get("id"));
			qa.setAnswer((String) row.get("answers"));
			System.out.println(qa.getAnswer());
			list.add(qa);
		}
			return list;
	}
	/**
	 * This method updates the values in the database 
	 * @param id
	 * @param hardAns
	 * @param softAns
	 * @return : integer
	 */
	public int updateValues(int id, String hardAns, String softAns) {
		int i = 0;
		int j =0;
		
			i = jdbcTemplate.update("update hardware_tab set answer='"+hardAns+"' where id="+id);
			if(i ==1){
				j = jdbcTemplate.update("update software_tab set answer='"+softAns+"' where link="+id);
			}
		
		return j;

	}
	public List<User> getUsers() {
		List<User> list = new ArrayList();
		String query = "select username from login where auth like 'user'";
		List<Map<String, Object>>  res= jdbcTemplate.queryForList(query);
		
		for (@SuppressWarnings("rawtypes") Map row : res) {
			User user = new User();
			user.setUserName((String) row.get("username"));
			System.out.println(user.getUserName());
		list.add(user);
		}
		
		return list;
	}
	public List<QuestionGetter> getQuestions() {
		List<QuestionGetter> list = new ArrayList();
		String query = "select question from queryquestion";
		List<Map<String, Object>>  res= jdbcTemplate.queryForList(query);
		
		for (@SuppressWarnings("rawtypes") Map row : res) {
			QuestionGetter question = new QuestionGetter();
			question.setQuestion((String) row.get("question"));
			list.add(question);
		}
		
		return list;
	}
	public int submitAns(QuestionAndAnswer qa) {
		
		return 0;
	}
	public int addAnswer(QuestionAndAnswer qa) {
		String queryQ = "select count(*) from question where question like ' "+qa.getQuestion()+"'";
		int resQ = jdbcTemplate.queryForObject(queryQ, Integer.class);
		if(resQ >=1){
			return 0;
		}
		queryQ = "select count(*) from question";
		int a = jdbcTemplate.queryForObject(queryQ, Integer.class);
		a = a + 1;
		int i = jdbcTemplate.update("insert into question values (" + a + " ,'" + qa.getQuestion() + "')");
		if(i==0){
			return 0;
		}
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
		 query = "delete from queryQuestion where question like '"+qa.getQuestion()+"'";
			j=jdbcTemplate.update(query);
			System.out.println(j+"****");
		}
		
			
		
		return j;
	}
	public int deleteQues(String delete) {
		String query = "delete from queryQuestion where question like '"+delete+"'";
		int i=jdbcTemplate.update(query);
				
		return i;
	}
	public int deleteQuestion(String delete) {
		String query = "delete from Question where question like '"+delete+"'";
		int i=jdbcTemplate.update(query);
		return i;		
	}
	

}
