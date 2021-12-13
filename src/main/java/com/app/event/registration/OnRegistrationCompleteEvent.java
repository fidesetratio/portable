package com.app.event.registration;

import org.springframework.context.ApplicationEvent;

import com.app.model.UserRegistration;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

	private int type;
	private String msg;
	public static final int SUCCESS_REGISTER = 0;
	public static final int FAIL_REGISTER = 1;
	
	public OnRegistrationCompleteEvent(UserRegistration userRegistration, int type, String message) {
		super(userRegistration);
		this.type = type;
		this.msg = msg;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
