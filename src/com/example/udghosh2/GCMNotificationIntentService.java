package com.example.udghosh2;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GCMNotificationIntentService extends IntentService {

	public static final int NOTIFICATION_ID = 1;
	private NotificationManager mNotificationManager;
	NotificationCompat.Builder builder;
	SharedPreferences sharedpreferences;
	Handler h;
	
	public GCMNotificationIntentService() {
		super("GcmIntentService");
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	
	h=notification.h;
	}
	
	public static final String TAG = "GCMNotificationIntentService";

	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle extras = intent.getExtras();
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

		String messageType = gcm.getMessageType(intent);

		if (!extras.isEmpty()) {
			if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR
					.equals(messageType)) {
				sendNotification("Send error: " + extras.toString());
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED
					.equals(messageType)) {
				sendNotification("Deleted messages on server: "
						+ extras.toString());
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE
					.equals(messageType)) {

//				for (int i = 0; i < 3; i++) {
//					Log.i(TAG,
//							"Working... " + (i + 1) + "/5 @ "
//									+ SystemClock.elapsedRealtime());
//					try {
//						Thread.sleep(5000);
//					} catch (InterruptedException e) {
//					}
//
//				}
//				Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime());

				sendNotification(""+extras.get(Config.MESSAGE_KEY));
				Log.i(TAG, "Received: " + extras.toString());
			}
		}
		GcmBroadcastReceiver.completeWakefulIntent(intent);
	}
	
	
	
	
	
	
	
	
	
	
	

	private void sendNotification(String msg) {
		
		sharedpreferences = getSharedPreferences(Config.NOTI_PREF, Context.MODE_PRIVATE);
		
		String noti=sharedpreferences.getString("Notifications", "Tester Notification <Day 1>;");
		
		noti=msg+";"+noti;
		
		
		SharedPreferences.Editor editor = sharedpreferences.edit();
		editor.putString("Notifications", noti);
		
		editor.commit();
		
		
		
		
		if(Constans_.Curr_Act==2)
		{
		
			Message msg1 = Message.obtain();
			msg1.obj=msg;
			h.sendMessage(msg1);
			
		}
		Log.d(TAG, "Preparing to send notification...: " + msg);
		mNotificationManager = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, MainActivity.class), 0);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this).setSmallIcon(R.drawable.gcm_cloud)
				.setContentTitle("NEWS Udghosh")
				.setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
				.setContentText(msg);
		

		mBuilder.setContentIntent(contentIntent);
		mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
		Log.d(TAG, "Notification sent successfully.");
	}
}
