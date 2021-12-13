package com.app.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.event.registration.OnRegistrationCompleteEvent;
import com.app.model.User;
import com.app.model.UserRegistration;
import com.app.services.DashboardServices;
import com.app.validator.component.UserValidator;

@Controller
public class RegistrationController {
	 @Autowired
	 private UserValidator userValidator;
	 
	  @Autowired
      private ApplicationEventPublisher eventPublisher;

	  @Autowired
	  private DashboardServices dashboardServices;
	
	
	  @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
	  public String registration(Model model){
			model.addAttribute("user",new UserRegistration());
			return "registration";
	  }

	  @RequestMapping(value = {"/process_register"})
	  public String process_registration(Model model,HttpServletRequest request, @ModelAttribute("user") @Valid UserRegistration user, Errors errors,BindingResult result){
		  String method = request.getMethod();
		  if(result.hasErrors()) {
			  model.addAttribute("user",user);
			  eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, OnRegistrationCompleteEvent.FAIL_REGISTER, "ok"));
			  return "registration";
		  }
		  
		  dashboardServices.insertUser(user);
		  if(user.getUser_id()>0) {
			  Integer regular_user = 2;
			  dashboardServices.insertUserToGroupId(user.getUser_id(), regular_user);
		  }
		  eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, OnRegistrationCompleteEvent.SUCCESS_REGISTER, "ok"));
		  return "redirect:/register?successRegister=1";
	  }
	  
	  @InitBinder
		public void dataBinding(WebDataBinder binder) {
			binder.addValidators(userValidator);
			
			//SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			//dateFormat.setLenient(false);
		//	binder.registerCustomEditor(Date.class, "dob", new CustomDateEditor(dateFormat, true));
		}
		


}
