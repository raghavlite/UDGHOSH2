package com.example.udghosh2;

public interface Config {

	
	
	
	public int Curr_Act=0;
	// used to share GCM regId with application server - using php app server
	static final String IMAGE_URL="http://portal.udghosh.org:3000";
	static final String APP_SERVER_URL = "http://portal.udghosh.org:4000/register";
     
	static final String APP_GET_URL="http://portal.udghosh.org:3000/all";
	public static final boolean DEVELOPER_MODE = false;
	static final String NOTI_PREF="noti_pref";
	static final String IMAGES="images_pager";
	static final String IMAGE_POSITION="image_pos";
	// GCM server using java
	// static final String APP_SERVER_URL =
	// "http://192.168.1.17:8080/GCM-App-Server/GCMNotification?shareRegId=1";

	// Google Project Number
	static final String GOOGLE_PROJECT_ID = "1014311285786";
	static final String MESSAGE_KEY = "message";
	static final String DATE_KEY = "time";
//////////////////////////////////////////////////////////////////////////////////////////////UDGHOSH
}
