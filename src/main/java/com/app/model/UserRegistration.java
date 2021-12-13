package com.app.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.app.validator.PasswordMatches;
import com.app.validator.ValidEmail;

public class UserRegistration {
	

		private Integer user_id;
	
		@Pattern(regexp="^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){5,18}[a-zA-Z0-9]$",message="Please input username min 5 and consists of number and letter")  
		private String username;
		

		@Pattern(regexp="^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$",message="Please input username min 5 and consists of number and letter")  
	    private String password;
		

		@Pattern(regexp="^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$",message="Please input username min 5 and consists of number and letter")  
	    private String confirmPassword;
		
        @Positive(message = "You need to aggree")
		private Integer agree;
		
		
		@ValidEmail(message="Please input valid email")
		private String email;
		
		public UserRegistration() {
			this.agree = 0;
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
		public String getConfirmPassword() {
			return confirmPassword;
		}
		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}
		
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public Integer getAgree() {
			return agree;
		}
		public void setAgree(Integer agree) {
			this.agree = agree;
		}
		
		public Integer getUser_id() {
		return user_id;
	}



	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}		

}
