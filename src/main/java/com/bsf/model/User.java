package com.bsf.model;

import java.sql.Date;

public class User {

	private int userId;
	private String userName;
	private String email;
	private String password;
	private Date creationTime;
	
	public User(final int userId, final String userName, final String email, final String password, final Date creationTime) {
		
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.creationTime = creationTime;
	}

	public int getUserId() {
		return userId;
	}
	
	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Date getCreationTime() {
		return creationTime;
	}
}
