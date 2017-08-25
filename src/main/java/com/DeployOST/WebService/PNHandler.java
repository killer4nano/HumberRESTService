package com.DeployOST.WebService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;


public class PNHandler {
	public final static String AUTH_KEY_FCM = "AAAA9vlZNYM:APA91bEqFJBSMDsGhtR1lt10sHaVTJVisxisq_L1-3UoXHod3z-dHNLc3i95ttim6i6p3TQwltNu20PiFC7QVuW6OA-q3yi2IIAvn5W4vK1PwkDW-D6EgVAjX9r3W4L_rEpfzpP99P3_";
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	public static String sendPushNotification(String message)
	        throws IOException {
	    String result = "";
	    URL url = new URL(API_URL_FCM);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	    conn.setUseCaches(false);
	    conn.setDoInput(true);
	    conn.setDoOutput(true);

	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
	    conn.setRequestProperty("Content-Type", "application/json");

	    JSONObject json = new JSONObject();

	    json.put("to", "/topics/tasks");
	    JSONObject info = new JSONObject();
	    info.put("message", message); // Notification title
	                                                            // body
	    json.put("data", info);
	    try {
	        OutputStreamWriter wr = new OutputStreamWriter(
	        conn.getOutputStream());
	        wr.write(json.toString());
	        wr.flush();

	        BufferedReader br = new BufferedReader(new InputStreamReader(
	                (conn.getInputStream())));

	        String output;
	        //System.out.println("Output from Server .... \n");
	        while ((output = br.readLine()) != null) {
	            //System.out.println(output);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    //System.out.println("GCM Notification is sent successfully");

	    return result;

	}
	
	public static String sendPushNotificationIOS(String message)
	        throws IOException {
	    String result = "";
	    URL url = new URL(API_URL_FCM);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	    conn.setUseCaches(false);
	    conn.setDoInput(true);
	    conn.setDoOutput(true);

	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
	    conn.setRequestProperty("Content-Type", "application/json");

	    JSONObject json = new JSONObject();

	    json.put("to", "/topics/iostasks");
	    json.put("priority", "high");
	    JSONObject info = new JSONObject();
	    info.put("body", message); // Notification title                                                          // body
	    info.put("title", "New Task");
	    json.put("notification", info);
	    try {
	        OutputStreamWriter wr = new OutputStreamWriter(
	        conn.getOutputStream());
	        wr.write(json.toString());
	        wr.flush();

	        BufferedReader br = new BufferedReader(new InputStreamReader(
	                (conn.getInputStream())));

	        String output;
	        //System.out.println("Output from Server .... \n");
	        while ((output = br.readLine()) != null) {
	            //System.out.println(output);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    //System.out.println("GCM Notification is sent successfully");

	    return result;

	}

}
