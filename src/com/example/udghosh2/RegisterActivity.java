package com.example.udghosh2;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class RegisterActivity extends Activity {

	Button btnGCMRegister;
	Button btnAppShare;
	GoogleCloudMessaging gcm;
	Context context;
	String regId;

	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

	public static final String REG_ID = "regId";
	private static final String APP_VERSION = "appVersion";

	static final String TAG = "Register Activity";

	

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		
		
		context = getApplicationContext();
		
		 if (checkPlayServices()) {
		        // If this check succeeds, proceed with normal processing.
		        // Otherwise, prompt user to get valid Play Services APK.
		        
				Toast.makeText(getApplicationContext(),
						"gps present",
						Toast.LENGTH_LONG).show();
			 
			 
		    }
		 
		 else
		 {
			 
				Toast.makeText(getApplicationContext(),
						"not present",
						Toast.LENGTH_LONG).show();
		 }
		
		
//		regId=  registerGCM();
		
		
//		try {
//			regId = gcm.register(Config.GOOGLE_PROJECT_ID);
//			
//			Toast.makeText(getApplicationContext(), "RegId is "+regId,
//					Toast.LENGTH_LONG).show();
//		
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		registerInBackground2();

//		btnGCMRegister = (Button) findViewById(R.id.btnGCMRegister);
//		btnGCMRegister.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				if (TextUtils.isEmpty(regId)) {
//					regId = registerGCM();
//					Log.d("TTTT", regId);
//				} else {
//					Toast.makeText(getApplicationContext(),
//							"Already Registered with GCM Server!",
//							Toast.LENGTH_LONG).show();
//				}
//			}
//		});

//		btnAppShare = (Button) findViewById(R.id.btnAppShare);
//		btnAppShare.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				if (TextUtils.isEmpty(regId)) {
//					Toast.makeText(getApplicationContext(), "RegId is empty!",
//							Toast.LENGTH_LONG).show();
//				} else {
//					Intent i = new Intent(getApplicationContext(),
//							MainActivity.class);
//					i.putExtra("regId", regId);
//					Log.d("RegisterActivity",
//							"onClick of Share: Before starting main activity.");
//					startActivity(i);
//					finish();
//					Log.d("RegisterActivity", "onClick of Share: After finish.");
//				}
//			}
//		});
	}

	public String registerGCM() {

		gcm = GoogleCloudMessaging.getInstance(this);
		regId = getRegistrationId(context);

		if (TextUtils.isEmpty(regId)) {

			
	//cd=new ConnectionDectector(context);
			registerInBackground();

			Log.d("TTTT",
					"registerGCM - successfully registered with GCM server - regId: "
							+ regId);
		} else {
			Toast.makeText(getApplicationContext(),
					"RegId already available. RegId: " + regId,
					Toast.LENGTH_LONG).show();
		}
		return regId;
	}

	private String getRegistrationId(Context context) {
		final SharedPreferences prefs = getSharedPreferences(
				MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
		String registrationId = prefs.getString(REG_ID, "");
		if (registrationId.isEmpty()) {
			Log.i(TAG, "Registration not found.");
			return "";
		}
		int registeredVersion = prefs.getInt(APP_VERSION, Integer.MIN_VALUE);
		int currentVersion = getAppVersion(context);
		if (registeredVersion != currentVersion) {
			Log.i(TAG, "App version changed.");
			return "";
		}
		return registrationId;
	}

	private static int getAppVersion(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager()
					.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			Log.d("RegisterActivity",
					"I never expected this! Going down, going down!" + e);
			throw new RuntimeException(e);
		}
	}

	private void registerInBackground2() {
		Log.v("Startup", "registering  background");
		
		//try {
			new AsyncTask<Void, Void, String>() {
				@Override
				protected String doInBackground(Void... params) {
					String msg = "";
					try {
						if (gcm == null) {
							gcm = GoogleCloudMessaging.getInstance(context);
						}
						regId = gcm.register(Config.GOOGLE_PROJECT_ID);
						Log.d("RegisterActivity", "registerInBackground - regId: "
								+ regId);
						msg = "Device registered, registration ID=" + regId;

						storeRegistrationId(context, regId);
						Log.d("Startup", "Sucessfully found GCm iD :");
						return regId;
						
					} catch (IOException ex) {
						msg = "Error :" + ex.getMessage();
						Log.d("Startup", "Error: " + msg);
					}
					Log.d("RegisterActivity", "AsyncTask completed: " + msg);
					
					msg="0";
					return msg;
				}

				@Override
				protected void onPostExecute(String msg) {
					
					
					Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
					if(msg=="1")
					{
						Log.d("Startup", "Starting Our server Sharer");
						
						//Sharewithserver();
					}
					else if(msg=="0")
					{
						//startActivity(in);
						Log.d("Startup", "Starting Our server Sharer11111111111111");
						finish();
						
					}
					
				}
			}.execute(null,null,null);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			Log.d("Startup", "Timeout GCM registration");
//			//startActivity(in);
//			finish();
//			////////////////////////////////////////////////////////////////////////////////////////homeexit
//			
//			
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			Log.d("Startup", "Timeout GCM registration");
//			//startActivity(in);finish();
////////////////////////////////////////////////////////////////////////////////////////homeexit
//			e.printStackTrace();
//		} catch (TimeoutException e) {
//			// TODO Auto-generated catch block
//			Log.d("Startup", "Timeout GCM registration");
//			//startActivity(in);
//			finish();
//			
////////////////////////////////////////////////////////////////////////////////////////homeexit
//			e.printStackTrace();
//		}
	}

	
	private void registerInBackground() {
		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				String msg = "";
				try {
					
					
					if (gcm == null) {
						gcm = GoogleCloudMessaging.getInstance(context);
					}
					regId = gcm.register(Config.GOOGLE_PROJECT_ID);
					Log.d("RegisterActivity", "registerInBackground - regId: "
							+ regId);
					msg = "Device registered, registration ID=" + regId;

					storeRegistrationId(context, regId);
				} catch (IOException ex) {
					msg = "Error :" + ex.getMessage();
					Log.d("RegisterActivity", "Error: " + msg);
				}
				Log.d("RegisterActivity", "AsyncTask completed: " + msg);
				return msg;
			}

			@Override
			protected void onPostExecute(String msg) {
				Toast.makeText(getApplicationContext(),
						"Registered with GCM Server." + msg, Toast.LENGTH_LONG)
						.show();
			}
		}.execute(null, null, null);
	}

	private void storeRegistrationId(Context context, String regId) {
		final SharedPreferences prefs = getSharedPreferences(
				MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
		int appVersion = getAppVersion(context);
		Log.i(TAG, "Saving regId on app version " + appVersion);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(REG_ID, regId);
		editor.putInt(APP_VERSION, appVersion);
		editor.commit();
	}
	
	
	
	
	
	
	private boolean checkPlayServices() {
	    int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
	    if (resultCode != ConnectionResult.SUCCESS) {
	        if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
	            GooglePlayServicesUtil.getErrorDialog(resultCode, this,
	                    PLAY_SERVICES_RESOLUTION_REQUEST).show();
	        } else {
	            Log.i(TAG, "This device is not supported.");
	            finish();
	        }
	        return false;
	    }
	    return true;
	}	
	
	
	
	
	
	
	
	
	
	
	
	

}
