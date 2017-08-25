package com.DeployOST.WebService;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import org.jsoup.Jsoup;


public class EmailReader extends Thread{
	
	
	String host = "smtp.gmail.com";
    String mailStoreType = "imaps";
    String username = "humbertesting780@gmail.com";
    String password = "somethingspecial";
    
	public EmailReader () {
	}
	
	public void run() {
		
	    System.out.println("Reading emails for new tasks now!");   
	    checkForTasks(host, mailStoreType, username, password);
	}
	
	
	public void checkForTasks(String host, String storeType, String user,String password) {
		      
		Properties props = new Properties();
		 
	    try {
	        props.setProperty("mail.imap.host", host);
	        props.setProperty("mail.imap.port", "993");
	        props.setProperty("mail.imap.connectiontimeout", "5000");
	        props.setProperty("mail.imap.timeout", "5000");
           
	        Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore(storeType);
            store.connect(host,user,password);
            Folder inbox = store.getFolder("inbox");
            
	            while(true) {
	    	        inbox.open(Folder.READ_WRITE);
			        if (inbox.getUnreadMessageCount() > 0) {
			            //int messageCount = inbox.getUnreadMessageCount();
			 
			            //System.out.println("Total Messages:- " + messageCount);
			 
			            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
			            //System.out.println("------------------------------");
			 
			            for (int i = 0; i < messages.length; i++) {
			                System.out.println("Mail Subject:- " + messages[i].getSubject());
			                Tasks newTask = new Tasks (messages[i].getSubject(),getTextFromMessage(messages[i]).substring(1));
			                TaskScheduler.getTasks().add(newTask); 
			                newTask.setId(SQLCommunication.newTask(newTask.getTaskName(), newTask.getTaskDescription()));
			                messages[i].setFlag(Flags.Flag.SEEN, true);
			            }
			            TaskScheduler.newTask();
			        }
			        //System.out.println("No new tasks were received.");     
			        inbox.close(true);
			        //System.out.println("Taking a 2 second break.");
					//Thread.sleep(500);
					
	            }
	    	}catch (Exception e) {
	            HumberRestServiceApplication.startEmailReader();
	        }
	   
    }
	
	private String getTextFromMessage(Message message) throws Exception {
	    if (message.isMimeType("text/plain")){
	        return message.getContent().toString();
	    }else if (message.isMimeType("multipart/*")) {
	        String result = "";
	        MimeMultipart mimeMultipart = (MimeMultipart)message.getContent();
	        int count = mimeMultipart.getCount();
	        for (int i = 0; i < count; i ++){
	            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	            if (bodyPart.isMimeType("text/plain")){
	                result = result + " " + bodyPart.getContent();
	                break;  //without break same text appears twice in my tests
	            }
	        }
	        return result;
	    }
	    return "";
	}
}
