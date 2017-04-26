package com.app.model;


import org.springframework.stereotype.Component;

@Component
public class QuestionAndAnswer {
private String question;
private String answer;

public String getQuestion() {
	return question;
}
public void setQuestion(String question) {
	this.question = question;
}
public String getAnswer() {
	return answer;
}
public void setAnswer(String answer) {
	this.answer = answer;
}
@Override
public String toString() {
	return "QuestionAndAnswer [question=" + question + ", answer=" + answer + "]";
}

}