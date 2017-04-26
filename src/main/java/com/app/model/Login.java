package com.app.model;


import org.springframework.stereotype.Component;

@Component
public class Login {
	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		if(userName != null){
		this.userName = userName;
		}
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if(password != null){
		this.password = password;
		}
	}
	
	

}
