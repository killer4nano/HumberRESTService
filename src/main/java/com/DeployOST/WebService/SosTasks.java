package com.DeployOST.WebService;


public class SosTasks {
	
	private String name;
	private String description;
	private String notes;
	
	
	public SosTasks(String taskName,String taskDescription,String notes) {
		name = taskName;
		description = taskDescription;
		this.notes = notes;
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getNotes() {
		return notes;
	}
	
}
