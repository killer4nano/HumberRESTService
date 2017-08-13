package com.DeployOST.WebService;

import java.io.IOException;
import java.util.ArrayList;

public class TaskScheduler {
	
	
	private static ArrayList<Tasks> listOfTasks = new ArrayList<Tasks>();
	private static ArrayList<Tasks> completedTasks = new ArrayList<Tasks>();
	private static ArrayList<Tasks> acceptedTasks = new ArrayList<Tasks>();
	private static ArrayList<SosTasks> sosTasks = new ArrayList<SosTasks>();
	
	public TaskScheduler() {
	
	}
	
	
	public void taskAccept(String acceptedTask,String tech) {
		Tasks taskAcc = null;
		for (Tasks task : listOfTasks) {
			if (task.getTaskName().equals(acceptedTask)) {
				task.assignTech(tech);
				taskAcc = task;
				break;
			}
				
		}
		acceptedTasks.add(taskAcc);
		listOfTasks.remove(taskAcc);
	}
	
	public static ArrayList<SosTasks> getSosTasks() {
		return sosTasks;
	}
	
	public static void addSosTask(SosTasks task) {
		sosTasks.add(task);
	}
	
	public static void removeSosTask(SosTasks task) {
		sosTasks.remove(task);
	}
	
	public static void finishTask(String task,String notes) {
		for (Tasks cTask : acceptedTasks) {
			if(cTask.getTaskName().equals(task)) { 
				cTask.setCompleted();
				//cTask.addNotes(notes);
				break;
			}
		}
	}
		
	
	
	public void taskComplete(String completedTask, String tech) {
		for (Tasks task : listOfTasks) {
			if (task.getTaskName().equals(completedTask)) {
				completedTasks.add(task);
				listOfTasks.remove(task);
				break;
			}				
		}
	}
	
	public static void removeFromSos(String task) {
		SosTasks sosTaskSent = null;
		for (SosTasks sosTask : sosTasks) {
			if(sosTask.getName().equals(task)) sosTaskSent = sosTask;
		}
		sosTasks.remove(sosTaskSent);
	}
	
	public static Tasks getTaskByName(String name) {
		for (Tasks task : listOfTasks) {
			if (task.getTaskName().equals(name)) return task;
		}
			return null;
	}
	
	public static boolean isTaskAvailable(String task) {
		for (Tasks taskz : listOfTasks) {
			if (taskz.getTaskName().equals(task)){
				return true;
			}
		}
		return false;
	}
	
	
	public static void newTask() {
		try {
			PNHandler.sendPushNotification("Hello!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Tasks> getTasks() {
		return listOfTasks;
	}
	
	
	
	

}
