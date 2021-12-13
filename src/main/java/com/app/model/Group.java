package com.app.model;

public class Group {
	
	private int groupId;
	private String group_name;
	private String description;
	private int active;
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "Group [groupId=" + groupId + ", group_name=" + group_name + ", description=" + description + ", active="
				+ active + "]";
	}

}
