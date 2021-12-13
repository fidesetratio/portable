package com.app.rests;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.RestResponse;
import com.app.model.User;
import com.app.model.UserRegistration;
import com.app.services.DashboardServices;
import com.app.validator.EmailValidator;

@RestController
@CrossOrigin(origins = "*")
public class ProfileRestController {

	  @Autowired
	  private DashboardServices dashboardServices;
	
	  
	  
	  
	  
	  
	  @RequestMapping(value= {"/submitpictureprofile"}, method=RequestMethod.POST)
      public RestResponse submitpictureprofile(@RequestBody User user) {
		  RestResponse restResponse = new RestResponse("Your picture has been saved!", 0, null);
		  
		  if(user.getUserId()!= null) {
			  if(user.getUserId()>0) {
				  if(user.getPhoto()!=null) {
					  if(!user.getPhoto().equals("")) {
						  	dashboardServices.updatePhotoByUserId(user);
							Authentication auth = SecurityContextHolder.getContext().getAuthentication();
					    	Authentication auth2 = ((Authentication)auth.getPrincipal());
					    	((User)auth2.getDetails()).setPhoto(user.getPhoto());
					    
					    	
					  }
				  }
			  
			  }
		  }
		  return restResponse;
		   
	  }
	  
	  @RequestMapping(value = {"/submitprofile"}, method = RequestMethod.POST)
	  public RestResponse submitprofile(@RequestBody User user){
		    RestResponse restResponse = new RestResponse("Your profile has been saved!", 0, null);
		    int error = 0;
		    String message = "error";
		    List<Fields> err = new ArrayList<Fields>();
		    if(user == null) {
		    	error = 1;
		    	restResponse.setMessage(message);
		    	restResponse.setErrors(error);
		    }else {
		    	Pattern pattern   = Pattern.compile(EmailValidator.EMAIL_PATTERN);
		    	Matcher matcher = pattern.matcher(user.getEmail());
		        Boolean ret =  matcher.matches();
		        if(!ret) {
		        	err.add(new Fields("email","Please complete email with real email "));
			    }
		    }
		    
		    
		    if(err.size()>0) {
		    	restResponse.setErrors(1);
		    	restResponse.setError(err);
		    	restResponse.setMessage("error");
		    }else {
		    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    	dashboardServices.updateEmailByUserId(user);
		    	Authentication auth2 = ((Authentication)auth.getPrincipal());
		    	((User)auth2.getDetails()).setEmail(user.getEmail());
		    }
		  
		    
			return restResponse;
	  }
	  
	  private class Fields{
		  private String field;
		  private String errorMsg;
		  
		  public Fields(String field, String errorMsg) {
			  this.field = field;
			  this.errorMsg = errorMsg;
		  }
		  
		public String getField() {
			return field;
		}
		public void setField(String field) {
			this.field = field;
		}
		public String getErrorMsg() {
			return errorMsg;
		}
		public void setErrorMsg(String errorMsg) {
			this.errorMsg = errorMsg;
		}
		  
	  }
}
