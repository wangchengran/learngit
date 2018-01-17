package com.aran.entity;

public class Phone {

	private int id;
	private String name;
	private int phonenumber;
	private int age;
	private String company;
	private String email;
	private int groupId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public Phone(int id, String name, int phonenumber, int age, String company, String email, int groupId) {
		super();
		this.id = id;
		this.name = name;
		this.phonenumber = phonenumber;
		this.age = age;
		this.company = company;
		this.email = email;
		this.groupId = groupId;
	}
	public Phone() {
		super();
	}
	@Override
	public String toString() {
		return "Phone [id=" + id + ", name=" + name + ", phonenumber=" + phonenumber + ", age=" + age + ", company="
				+ company + ", email=" + email +  ", groupId=" + groupId + "]";
	}
	
}
