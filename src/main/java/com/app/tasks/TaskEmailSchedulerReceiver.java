package com.app.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskEmailSchedulerReceiver {

	@Scheduled(fixedRate = 2000)
	public void scheduleTaskWithFixedRate() {
		System.out.println("data");
	}

}
