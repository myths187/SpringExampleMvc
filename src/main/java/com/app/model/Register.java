package com.app.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class Register {
	@NotNull(message = "Select a role")
	private String auth;
	@NotEmpty(message = "First Name should not be blank.")
	@NotBlank
	@Size(min=1, max=10)
	private String firstName;
	@NotEmpty(message = "Last Name should not be blank.")
	@NotBlank
	@Size(min=1, max=10)
	private String lastName;
	@NotEmpty(message = "User Name should not be empty.")
	@NotBlank(message =  "User Name should not be blank.")
	@Size(min=1, max=10)
	private String userName;
	@Email
	@NotEmpty(message = "email should not be blank.")
	@NotBlank
	private String email;
	@NotEmpty(message = "password should not be blank.")
	@NotBlank(message = "password should not be empty.")
	@Size(min=1, max=10)
	private String password;

	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
