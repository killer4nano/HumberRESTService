package com.DeployOST.WebService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HumberRestServiceApplication {
	
	private static TaskScheduler taskScheduler = new TaskScheduler();
 	private static EmailReader emailReader = new EmailReader();

	public static void main(String[] args) {
		SpringApplication.run(HumberRestServiceApplication.class, args);
		startEmailReader();
	}
	
	
	public static void startEmailReader() {
		emailReader = new EmailReader();
		emailReader.start();
	}
	
	public static TaskScheduler getTaskScheduler() {
		return taskScheduler;
	}
}

