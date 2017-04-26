package com.app.model;

public class QuestionGetter {
	private String question;

	@Override
	public String toString() {
		return "QuestionGetter [question=" + question + "]";
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}
