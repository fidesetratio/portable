package com.app.model;

public class Role {
		private Integer roleId;
		private String role_name;
		private String description;
		private int active;
		public Integer getRoleId() {
			return roleId;
		}
		public void setRoleId(Integer roleId) {
			this.roleId = roleId;
		}
		public String getRole_name() {
			return role_name;
		}
		public void setRole_name(String role_name) {
			this.role_name = role_name;
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
			return "Role [roleId=" + roleId + ", role_name=" + role_name + ", description=" + description + ", active="
					+ active + "]";
		}
		

}
