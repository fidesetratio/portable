package com.app.model;

public class UserView {
	
	private Integer userId;
	private String username;
	private String email;
	private Integer active;
	private String groupMembers;
	private String photo;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public String getGroupMembers() {
		return groupMembers;
	}
	public void setGroupMembers(String groupMembers) {
		this.groupMembers = groupMembers;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	

}
