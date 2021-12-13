package com.app.controller.extra.easyui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.model.UserRegistration;

@Controller
public class EasyUiController {


	  @RequestMapping(value = {"/extra-easyui"}, method = RequestMethod.GET)
	  public String easyui(Model model){
			model.addAttribute("user",new UserRegistration());
			return "easyui";
	  }

}
