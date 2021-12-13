package com.app.model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private Integer userId;
	private String username;
	private String password;
	private String email;
	private int active;
	private List<Group> groups;
	private String photo;
	
	public User() {
		this.userId = 0;
		this.username = "";
		this.password = "";
		this.email = "";
		this.active = 0;
		groups = new ArrayList<Group>();
		this.photo = "";
	}
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
	public List<Group> getGroups() {
		return groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", active=" + active + ", groups=" + groups + ", photo=" + photo + "]";
	}
	
}
