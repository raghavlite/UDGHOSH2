package com.example.udghosh2;

public interface Config {

	
	
	
	public int Curr_Act=0;
	// used to share GCM regId with application server - using php app server
	static final String APP_SERVER_URL = "http://10.42.0.1:3000/register";
     
	static final String APP_GET_URL="http://10.42.0.1:8888/all";
	
	static final String NOTI_PREF="noti_pref";
	// GCM server using java
	// static final String APP_SERVER_URL =
	// "http://192.168.1.17:8080/GCM-App-Server/GCMNotification?shareRegId=1";

	// Google Project Number
	static final String GOOGLE_PROJECT_ID = "1014311285786";
	static final String MESSAGE_KEY = "message";
	static final String DATE_KEY = "time";
//////////////////////////////////////////////////////////////////////////////////////////////UDGHOSH
}
