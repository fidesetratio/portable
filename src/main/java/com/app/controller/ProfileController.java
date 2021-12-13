package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.model.UserRegistration;

@Controller
public class ProfileController {
	  @RequestMapping(value = {"/profile"}, method = RequestMethod.GET)
	  public String registration(Model model){
			model.addAttribute("user",new UserRegistration());
			return "profile";
	  }

	  
	  
}
