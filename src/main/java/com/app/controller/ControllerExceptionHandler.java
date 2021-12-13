package com.app.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	  @ExceptionHandler(Exception.class)
	  public void internalServerError(Exception e,HttpServletRequest request, HttpServletResponse response) {
		  
		  if(e.getMessage().contains("HV000028") && request.getRequestURI().contains("/process_register")) {
			  try {
				response.sendRedirect("/register");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  }
		  
	  
	  
	  
	  }
	  
	  

}
