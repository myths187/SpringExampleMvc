package com.app.model;

public class QuestionsAnswer {
	private int id;
	private String question;
	private String answer;
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	@Override
	public String toString() {
		return "QuestionsAnswer [id=" + id + ", question=" + question + ", answer=" + answer + 
				"]";
	}
	
	
	
}
