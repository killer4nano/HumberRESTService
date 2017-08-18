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
	
	@RequestMapping("/sos/{id}/{job}/{desc}/{notes}") 
	@ResponseBody
	String sosTask(@PathVariable("id") int id,@PathVariable("job") String name,@PathVariable("desc") String desc, @PathVariable("notes") String notes){
		TaskScheduler.addSosTask(new SosTasks(name,desc,notes));
		SQLCommunication.sosTask(id, notes);
		TaskScheduler.newTask();
		return "done";
	}
	
	@RequestMapping("/nosos/{id}/{job}")
	@ResponseBody
	String turnOffSos(@PathVariable int id,@PathVariable("job") String job) {
		TaskScheduler.removeFromSos(job);
		SQLCommunication.removeSos(id);
		TaskScheduler.newTask();
		return "done";
	}
	
	
	@RequestMapping("/sostasks")
	@ResponseBody
	ArrayList<SosTasks> getSosTasks() {
		HumberRestServiceApplication.getTaskScheduler();
		return TaskScheduler.getSosTasks();
	}
	
	
	@RequestMapping("/finish/{userid}/{id}/{job}/{desc}/{notes}")
	@ResponseBody
	String finishJob(@PathVariable("desc") String desc,@PathVariable("id") int id,@PathVariable("userid") int userId,@PathVariable("job") String jobName,@PathVariable("notes") String notes) {
		TaskScheduler.finishTask(jobName,notes);
		SQLCommunication.finishTask(jobName, desc, notes, userId, id);
		return "done";
	}
	
	@RequestMapping("/mytask/{id}")
	@ResponseBody
	Tasks getMyTask(@PathVariable("id") int id) {
		return SQLCommunication.getMyTask(id);
	}
	
	@RequestMapping("/sos/{id}/{notes}")
	@ResponseBody
	String sosTask(@PathVariable("id") int id,@PathVariable("notes") String notes) {
		SQLCommunication.sosTask(id,notes);
		return "done";
	}
	
	
	@RequestMapping("/login/{user}/{password}")
	@ResponseBody
	int login(@PathVariable("user") String username, @PathVariable("password") String password) {
		return SQLCommunication.userConnect(username, password);
	}
	
	
	@RequestMapping("/accept/{id}/{name}/{job}") 
	@ResponseBody
	String acceptJob(@PathVariable("id") int id,@PathVariable("name") String name,@PathVariable("job") String jobName) {
		if (TaskScheduler.isTaskAvailable(jobName)) {		
			HumberRestServiceApplication.getTaskScheduler().taskAccept(jobName, name);	
			SQLCommunication.acceptTask(id, jobName);
			TaskScheduler.newTask();
			System.out.println(name + " has just accepted task:"+jobName);
			return "done";
		}else {				
			return "no";
		}
	}
	
	@RequestMapping("/issos/{id}")
	@ResponseBody
	String isSos(@PathVariable("id")int id) {
		if (SQLCommunication.isSos(id) != 0) {
			return "yes";
		}else {
			return "no";
		}
	}
	
	
}
