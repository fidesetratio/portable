package com.app.event.registration.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.app.event.registration.OnRegistrationCompleteEvent;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		// TODO Auto-generated method stub
		
		switch(event.getType()) {
		case OnRegistrationCompleteEvent.SUCCESS_REGISTER:
			System.out.println("success register");
		break;
		case OnRegistrationCompleteEvent.FAIL_REGISTER:
			System.out.println("fail register");
		break;
		}
		
	}

}
