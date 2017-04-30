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
		
		for (@SuppressWarnings("rawtypes") Map row : res) {
			QuestionsAnswer qa = new QuestionsAnswer();
			qa.setId((Integer) row.get("id"));
			qa.setAnswer((String) row.get("answers"));
			list.add(qa);
		}
			return list;
	}
	/**
	 *  This method returns the users
	 * @return
	 */
	public List<User> getUsers() {
		List<User> list = new ArrayList();
		String query = "select username from login where auth like 'user'";
		List<Map<String, Object>>  res= jdbcTemplate.queryForList(query);
		
		for (@SuppressWarnings("rawtypes") Map row : res) {
			User user = new User();
			user.setUserName((String) row.get("username"));
		list.add(user);
		}
		
		return list;
	}
	/**
	 * thsi method returns list of qustions
	 * @return
	 */
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
	/**
	 * Adds Question and Answer
	 * @param qa
	 * @return
	 */
	public int addAnswer(QuestionAndAnswer qa) {
		String queryQ = "select count(*) from question where question like ' "+qa.getQuestion()+"'";
		int resQ = jdbcTemplate.queryForObject(queryQ, Integer.class);
		if(resQ >=1){
			return 0;
		}
		int i = jdbcTemplate.update("insert into question (question) values ('" + qa.getQuestion() + "')");
		if(i==0){
			return 0;
		}
		String query = "select count(*) from answers where answers like '"+qa.getAnswer()+"'";
		int res = jdbcTemplate.queryForObject(query, Integer.class);
		int j =0;
		if(res >=1){
			return 0;
		}else{
		j = jdbcTemplate.update("insert into answers(answers,fk) values ('" + qa.getAnswer()
					+ "' , ( select id from question where question like '" + qa.getQuestion() + "'))");
		 query = "delete from queryQuestion where question like '"+qa.getQuestion()+"'";
			j=jdbcTemplate.update(query);
		}
		
			
		
		return j;
	}
	/**
	 * deletes question from questionquery
	 * @param delete
	 * @return
	 */
	public int deleteQues(String delete) {
		String query = "delete from queryQuestion where question like '"+delete+"'";
		int i=jdbcTemplate.update(query);
				
		return i;
	}
	/**
	 * deletes question from question
	 * @param delete
	 * @return
	 */
	public int deleteQuestion(String delete) {
		String query = "delete from Question where question like '"+delete+"'";
		int i=jdbcTemplate.update(query);
		return i;		
	}
	/**
	 * updates answer
	 * @param qa
	 * @return
	 */
	public int updateAnswer(QuestionsAnswer qa) {
		String query = "update answers set answers ='"+qa.getAnswer()+"' where id ='"+qa.getId()+"'";
		int i = jdbcTemplate.update(query);
		return i;
	}
	/**
	 * deletes answer
	 * @param qa
	 * @return
	 */
	public int delAns(QuestionsAnswer qa) {
		String query = " delete from answers where answers like '"+qa.getAnswer()+"' or id ="+qa.getId()+"";
		int i =jdbcTemplate.update(query);
		
		return i;
	}
	

}
