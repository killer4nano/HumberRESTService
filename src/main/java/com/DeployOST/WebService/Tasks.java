package com.DeployOST.WebService;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Created by kille on 2017-07-16.
 */
public class Tasks {

    private String name;
    @JsonProperty("id")
    private int id;
    private String description;
    private String tech;
	@JsonProperty("notes")
    private String notes;
    private boolean completed;
    private boolean sos = false;

    public Tasks (String taskName, String taskDescription) {
        name = taskName;
        description = taskDescription;
        tech = "none";
        this.notes = "none";
        completed = false;
    }
    
    public Tasks (int id,String taskName, String taskDescription) {
        name = taskName;
        description = taskDescription;
        tech = "none";
        this.notes = "none";
        completed = false;
        this.id = id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    

    public Tasks(boolean isSos,String name,String tech,boolean completed, String description,String notes) {
        this.sos = isSos;
        this.name = name;
        this.tech = tech;
        this.completed = completed;
        this.description = description;

    }


    public void setCompleted() {

        completed = true;
    }

    public String getTaskDescription() {
        return description;
    }

    public String getTaskName() {
        return name;
    }

    public boolean isItCompleted() {
        return completed;
    }

    public String getTech() {
        return tech;
    }

    public void assignTech(String tech) {
        this.tech = tech;
    }

    public void setSos(boolean bool){
        sos = bool;
    }

    public boolean getSos() {
        return sos;
    }


}
