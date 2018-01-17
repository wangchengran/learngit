package com.aran.entity;

public class LoginBean {

	private int id;
	private String realName;
	private String password;
	
	public LoginBean(int id, String realName, String password) {
		this.id = id;
		this.realName = realName;
		this.password = password;
	}
	
	public LoginBean() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
