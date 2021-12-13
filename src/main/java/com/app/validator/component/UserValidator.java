package com.app.validator.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.app.model.UserRegistration;
import com.app.services.DashboardServices;

@Component
public class UserValidator implements Validator {
	
	@Autowired
	private DashboardServices dashboardServices;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserRegistration.class.isAssignableFrom(clazz);

	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserRegistration user = (UserRegistration)target;
		
		int total = dashboardServices.selectTotalOfUsername(user.getUsername());
		
		if(user.getEmail() != null) {
			int totalEmail = dashboardServices.selectTotalOfEmail(user.getEmail());
			if(totalEmail > 0) {
				errors.rejectValue("email","", "Email is not available");
			}
		}
		
		
		if(total > 0) {
			errors.rejectValue("username","","Username is not available");
		};
	
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword","","Passwords don't match");
					
		}
		
	}

}
