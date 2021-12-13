package com.app.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.app.model.UserRegistration;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
	

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        //
    }

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		
		final UserRegistration user = (UserRegistration) obj;
	    return user.getPassword().equals(user.getConfirmPassword());
	}

}
