package com.app.model;


import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
public class Login {
	@NotEmpty(message = "User Name should not be blank.")
	@NotBlank
	@Size(min=1,max=10)
	private String userName;
	@NotEmpty(message = "Password should not be blank.")
	@NotBlank
	@Size(min=1,max=10)
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
