package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.model.UserRegistration;
import com.app.services.DashboardServices;

@Controller
public class UserManagementController {

	
	@Autowired 
	private DashboardServices ds;
	 
	
	  @RequestMapping(value = {"/um-users"}, method = RequestMethod.GET)
	  public String usermanagement(Model model){
		  	model.addAttribute("tables", ds.selectAllUserNameAndGroup());
		  	return "listusers";
	  }


	  @RequestMapping(value = {"/um-add-user"}, method = RequestMethod.GET)
	  public String userAdd(Model model){
		  	model.addAttribute("tables", ds.selectAllUserNameAndGroup());
		  	return "adduser";
	  }

}
