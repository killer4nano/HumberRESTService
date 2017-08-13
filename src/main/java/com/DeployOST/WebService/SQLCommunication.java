package com.DeployOST.WebService;


import com.mysql.cj.api.mysqla.result.Resultset;	 
import java.sql.*;
 
public class SQLCommunication {
 
 
	
public static Connection sqlConnection() {
	
	Connection connect = null;
	try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        connect =(Connection) DriverManager.getConnection("jdbc:mysql://DESKTOP-43VDM4D:3306/deployost","andrew","humberost");
       
        if(connect!=null)
        {
                        System.out.println("Connected successful");
        }
       return connect;
	}catch (Exception e) {
		return null;
	}
}
               
               
public static int userConnect(String name, String password){
    Connection connect = sqlConnection();
    try{
        Statement statement = connect.createStatement();
        String queryString = "select * from users where user = \"" +name + "\" and password = PASSWORD(\"" + password + "\") ";
   
        Resultset result = (Resultset) statement.executeQuery(queryString);
   
    if   (((ResultSet) result).next()){             
    	return ((ResultSet) result).getInt("id");                                                
    }
       
    }
   
    catch(Exception e){
        System.out.println(e);
        return 0;
    }
   
   
	return 0;
}
   
               
   
               
               
}


