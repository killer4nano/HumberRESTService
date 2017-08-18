package com.DeployOST.WebService;


import com.mysql.cj.api.mysqla.result.Resultset;	 
import java.sql.*;
import java.util.ArrayList;
 
public class SQLCommunication {
 
 
	
public static Connection sqlConnection() {
	
	Connection connect = null;
	try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        connect =(Connection) DriverManager.getConnection("jdbc:mysql://NB-00314-CLSCAL:3306/deployost?autoreconnect=true&useSSL=false","andrew","humberost");
       
       return connect;
	}catch (Exception e) {
		System.out.println(e);
		return null;
	}
}

public static ArrayList<Tasks> loadTasks() {
	Connection connect = sqlConnection();
	ArrayList<Tasks> listOfTasks = new ArrayList<Tasks>();
	try {
		Statement statement = connect.createStatement();
		String qs = "select * from atasks where assignedTech is null";
		
		ResultSet result = statement.executeQuery(qs);
		
		while (result.next()) {
			listOfTasks.add(new Tasks(result.getInt(1),result.getString(2),result.getString(3)));
		}
		
		return listOfTasks;
	}catch (Exception e) {
		System.out.println(e);
		return listOfTasks;
	}
}

public static void sosTask(int id, String notes) {
	Connection connect = sqlConnection();
	try {
		Statement statement = connect.createStatement();
		String qs = "insert into stasks (refID, notes) values ("+id+",\""+notes+"\")";
		
		statement.executeUpdate(qs);
	}catch (Exception e) {
		System.out.println(e);
	}
}

public static int newTask(String name, String description) {
	Connection connect = sqlConnection();
	try {
		Statement statement = connect.createStatement();
		String qs = "insert into atasks (jobname,description) value (\""+name+"\",\""+description+"\")";
		int result = statement.executeUpdate(qs,Statement.RETURN_GENERATED_KEYS);
		return result;
	}catch(Exception e) {
		e.printStackTrace();
		return 0;
	}
}


public static Tasks getMyTask(int id) {
	Connection connect = sqlConnection();
	Tasks task;
	try {
		Statement statement = connect.createStatement();
		String qs = "select * from atasks where assignedTech = "+id;
		
		ResultSet result = statement.executeQuery(qs);
		if (result.next()) {
			task = new Tasks(result.getInt(1),result.getString(2),result.getString(3));
			return task;
		}else {
			return null;
		}
	}catch (Exception e)  {
		System.out.println(e);
		return null;
	}
}

public static void finishTask(String name, String desc, String notes, int tech,int id) {
	Connection connect = sqlConnection();
	try {
		Statement statement = connect.createStatement();
		String qs1 = "delete from atasks where jobID =" +id;
		statement.executeUpdate(qs1);
		String qs2 = "insert into ctasks (jobname,description,notes,completeTech) values (\""+name+"\",\""+desc+"\",\""+notes+"\","+tech+")";
		statement.executeUpdate(qs2);
	}catch (Exception e) {
		System.out.println(e);
	}
}

public static void acceptTask(int id,String name) {
	Connection connect = sqlConnection();
	try {
		Statement statement = connect.createStatement();
		String qs = "update atasks set assignedTech ="+id+" where jobname = \""+name+"\"";
		statement.executeUpdate(qs);
	}catch (Exception e) {
		System.out.println(e);
	}
}

public static int isSos(int id) {
	Connection connect = sqlConnection();
	try {
		Statement statement = connect.createStatement();
		String qs = "select * from stasks where refID =" +id;
		ResultSet result = statement.executeQuery(qs);
		if (result.next()) {
			return result.getInt(1);
		}else {
			return 0;
		}
	}catch(Exception e) {
		System.out.println(e);
		return 0;
	}
}

public static void removeSos(int id) {
	Connection connect = sqlConnection();
	try {
		Statement statement = connect.createStatement();
		String qs = "delete from stasks where refID = "+id;
		statement.executeUpdate(qs);
	}catch (Exception e) {
		System.out.println(e);
	}
}

public static ArrayList<SosTasks> loadSosTasks() {
	Connection connect = sqlConnection();
	ArrayList<SosTasks> listOfTasks = new ArrayList<SosTasks>();
	try {
		Statement statement = connect.createStatement();
		String qs = "select * from atasks inner join stasks on atasks.jobId = stasks.refID";
		
		ResultSet result = statement.executeQuery(qs);
		
		while (result.next()) {
			listOfTasks.add(new SosTasks(result.getString(2),result.getString(3),result.getString(6)));
		}
		
		return listOfTasks;
	}catch (Exception e) {
		System.out.println(e);
		return listOfTasks;
	}
}
               
               
public static int userConnect(String name, String password){
    Connection connect = sqlConnection();
    try{
        Statement statement = connect.createStatement();
        String queryString = "select * from users where user = \"" +name + "\" and password = PASSWORD(\"" + password + "\") ";
   
        ResultSet result = statement.executeQuery(queryString);
   
    if   (result.next()){             
    	return (result).getInt("id");                                                
    }
       
    }
   
    catch(Exception e){
        System.out.println(e);
        return 0;
    }
   
   
	return 0;
}
   
               
   
               
               
}


