package com.DeployOST.WebService;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestController {

	@RequestMapping("/tasks")
	@ResponseBody
	ArrayList<Tasks> getTasks() {
		HumberRestServiceApplication.getTaskScheduler();
		return TaskScheduler.getTasks();
	}
	
	@RequestMapping("/sos/{job}/{desc}/{notes}") 
	@ResponseBody
	String sosTask(@PathVariable("job") String name,@PathVariable("desc") String desc, @PathVariable("notes") String notes){
		TaskScheduler.addSosTask(new SosTasks(name,desc,notes));
		TaskScheduler.newTask();
		return "done";
	}
	
	@RequestMapping("/nosos/{job}")
	@ResponseBody
	String turnOffSos(@PathVariable("job") String job) {
		TaskScheduler.removeFromSos(job);
		TaskScheduler.newTask();
		return "done";
	}
	
	
	@RequestMapping("/sostasks")
	@ResponseBody
	ArrayList<SosTasks> getSosTasks() {
		HumberRestServiceApplication.getTaskScheduler();
		return TaskScheduler.getSosTasks();
	}
	
	
	@RequestMapping("/finish/{name}/{job}/{notes}")
	@ResponseBody
	String finishJob(@PathVariable("name")String name,@PathVariable("job") String jobName,@PathVariable("notes") String notes) {
		TaskScheduler.finishTask(jobName,notes);
		System.out.println(name + " has just finished "+ jobName+"\n"+notes);
		return "done";
	}
	
	
	@RequestMapping("/login/{user}/{password}")
	@ResponseBody
	String login(@PathVariable("user") String username, @PathVariable("password") String password) {
		if (SQLCommunication.userConnect(username, password) > 0) {
			return "done";
		}else {
			return "no";
		}
	}
	
	
	@RequestMapping("/accept/{name}/{job}") 
	@ResponseBody
	String acceptJob(@PathVariable("name") String name,@PathVariable("job") String jobName) {
		if (TaskScheduler.isTaskAvailable(jobName)) {
			TaskScheduler.newTask();
			HumberRestServiceApplication.getTaskScheduler().taskAccept(jobName, name);		
			System.out.println(name + " has just accepted task:"+jobName);
			return "done";
		}else {				
			return "no";
		}
	}
	
	
}
