package com.sharespirit.udghosh2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;




import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Startup extends Activity{
	
	
	Button btnGCMRegister;
	Button btnAppShare;
	GoogleCloudMessaging gcm;
	Context context;
	String regId;
	ConnectionDetector cd;
	ShareExternalServer	appUtil;
	AsyncTask shareRegidTask;
	final String SER_REG="ser_reg";
	
	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

	public static final String REG_ID = "regId";
	private static final String APP_VERSION = "appVersion";

	static final String TAG = "Register Activity";
     Intent in=null;//=new Intent(getApplicationContext(), MainActivity.class);

     private static final int SPLASH_TIME_OUT = 3000;
     
	ProgressBar Spinner;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//startActivity(in);
		setContentView(R.layout.splash_screen);
		
		context = getApplicationContext();
		in=new Intent(getApplicationContext(), MainActivity.class);
		 Spinner=(ProgressBar)findViewById(R.id.progressBar3);
		 Spinner.setVisibility(View.VISIBLE);
			cd=new ConnectionDetector(context);
		 
		 Constans_.Started=0;
		 
//		 startActivity(in);
//			finish();
		 
		 
		 
		
		if (checkPlayServices()) {
		        // If this check succeeds, proceed with normal processing.
		        // Otherwise, prompt user to get valid Play Services APK.
		        
				//Toast.makeText(getApplicationContext(),
				//		"Google Play Services present",
				//		Toast.LENGTH_LONG).show();
				//Log.d("Startup", "GP services present");
		    }
		 
		 else
		 {
			 
				Toast.makeText(getApplicationContext(),
						"Please update Googole Play Services",
						Toast.LENGTH_LONG).show();
				startActivity(in);
				finish();
				Log.d("Startup", "GP services not present");
				return;
				
		 }
		
		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
		
	
		
		
		
		
		
		
		
		
			//	shareRegidTask;
			
		
		new Handler().postDelayed(new Runnable() {
			 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
              if(Constans_.Started==1)
              {
            	  return;
              }
            
            	Intent i = new Intent(Startup.this, MainActivity.class);
            	in.putExtra("slow", true);
            	//player.stop();
                startActivity(i);
 
               // overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                
                // close this activity
                finish();
            }
        }, 8000);
		
		//registerInBackground();
		
//		gcm = GoogleCloudMessaging.getInstance(context);
//	
//	try {
//		regId = gcm.register(Config.GOOGLE_PROJECT_ID);
//		Toast.makeText(getApplicationContext(), regId, Toast.LENGTH_SHORT).show();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	Log.d("raghav", "raghav6666");
//		
				regId = registerGCM();
				
				
				
				
				
		
		 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public String registerGCM()
	{
		
		Log.v("Startup", "into Regisster GCM");
		
		/////////////////////////
		/////////////////////////////////////////////////////////////

		gcm = GoogleCloudMessaging.getInstance(this);
		regId = getRegistrationId(context);

		if (TextUtils.isEmpty(regId)) {
//            
			Log.d("Startup", "No Shared pref reg id present");
			
			cd=new ConnectionDetector(context);
			
//			
			if(cd.isConnectingToInternet())
			{
				//Toast.makeText(getApplicationContext(), "register gcm", Toast.LENGTH_SHORT).show();
				
				Log.w("Startup", "Internet available,start register in background ");
			registerInBackground();
			}
			else
			{Log.d("Startup", "Internet not avalable hence bye");
				Toast.makeText(context, "Internet not available", Toast.LENGTH_SHORT).show();
				startActivity(in);
				finish();
			}
			
			
			Log.d("TTTT",
					"registerGCM - successfully registered with GCM server - regId: "
							+ regId);
		} else {
			//Toast.makeText(getApplicationContext(),
				//	"RegId already available. RegId: " + regId,
					//Toast.LENGTH_LONG).show();
			
			///////////////////////////////////////////check if server registered
			Log.d("Startup", "Since gcm id present in SP cheking for server storage");
			if(Check_ifserved())
			{
//				Log.d("Startup", "Server stored already starting main activity");
//				startActivity(in);
//				finish();
				
				
				
				new Handler().postDelayed(new Runnable() {
					 
		            /*
		             * Showing splash screen with a timer. This will be useful when you
		             * want to show case your app logo / company
		             */
		 
		            @Override
		            public void run() {
		                // This method will be executed once the timer is over
		                // Start your app main activity
		              
		            
		            	Intent i = new Intent(Startup.this, MainActivity.class);
		            	
		            	//player.stop();
		                startActivity(i);
		 
		               // overridePendingTransition(R.anim.fadein,R.anim.fadeout);
		                
		                // close this activity
		                finish();
		            }
		        }, 1500);
				
				
				
			}
			else
			{Log.d("Startup", "ID not shared with server,hence sharing");
				Sharewithserver(Startup.this);
			}
//			
//			
		}
		return regId;
	}

	
	
	private String getRegistrationId(Context context) {
		
		
		
		final SharedPreferences prefs = getSharedPreferences(
				MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
		String registrationId = prefs.getString(REG_ID, "");
		if (registrationId=="") {
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
	
	
	
	
	
	
	
	
	
	private void registerInBackground() {
		Log.v("Startup", "registering  background");
		
		
		AsyncTaskActivity asta=new AsyncTaskActivity(Startup.this);
		
		asta.execute();
		
//			new AsyncTask<Void, Void, String>() {
//				@Override
//				protected String doInBackground(Void... params) {
//					String msg = "";
//					try {
//						if (gcm == null) {
//							gcm = GoogleCloudMessaging.getInstance(context);
//						}
//						regId = gcm.register(Config.GOOGLE_PROJECT_ID);
//						Log.d("RegisterActivity", "registerInBackground - regId: "
//								+ regId);
//						msg = "Device registered, registration ID=" + regId;
//
//						storeRegistrationId(context, regId);
//						Log.d("Startup", "Sucessfully found GCm iD :");
//						return "1";
//						
//					} catch (IOException ex) {
//						msg = "Error11 :" + ex.getMessage();
//						Log.d("Startup", "Error: " + msg);
//					}catch(NullPointerException nl)
//					{
//						msg="0";
//					}
//					
//					
//					Log.d("RegisterActivity", "AsyncTask completed: " + msg);
//					
//					msg="0";
//					return msg;
//				}
//
//				@Override
//				protected void onPostExecute(String msg) {
//					
//					if(msg=="1")
//					{
//						Log.d("Startup", "Starting Our server Sharer");
//						
//						Sharewithserver();
//					}
//					else if(msg=="0")
//					{
//						startActivity(in);
//						finish();
//						
//					}
//					
//				}
//			}.execute(null,null,null);
		
	}
	
	
	
	
	
	
	private class AsyncTaskActivity extends AsyncTask<String, Void, String> {

		Activity mActivity;
		    public AsyncTaskActivity(Activity activity)
		    {
		         super();
		         this.mActivity=activity;
		    }
			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				
				
				
				
				
				
				
				
				
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
					return "1";
					
				} catch (IOException ex) {
					msg = "Error11 :" + ex.getMessage();
					Log.d("Startup", "Error: " + msg);
				}catch(NullPointerException nl)
				{
					msg="0";
				}
				
				
				Log.d("RegisterActivity", "AsyncTask completed: " + msg);
				
				msg="0";
				return msg;
				
				
				
				
				
				//return null;
			}
			
			
			@Override
			protected void onPostExecute(String msg) {
				
				Intent in1=new Intent(mActivity, MainActivity.class);
				
				if(msg=="1")
				{
					Log.d("Startup", "Starting Our server Sharer");
					
					Sharewithserver(mActivity);
				}
				else if(msg=="0")
									
				{Toast.makeText(getApplicationContext(), "Check Network Authentication", Toast.LENGTH_SHORT).show();
				
			       if(Constans_.Started==1)
		              {
		            	  return;
		              }
		            
				
				 
				    
				    startActivity(in);
					this.mActivity.finish();
					
				}
				
			}	
			
			
			
			
			
		}
	
	
	
	

	private void storeRegistrationId(Context context, String regId) {
		final SharedPreferences prefs = getSharedPreferences(
				MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
		int appVersion = getAppVersion(context);
		Log.i(TAG, "Saving regId on app version " + appVersion);
		
		Log.d("Startup", "regiD :"+regId);
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
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//server functions	
	
	
	
	public boolean Check_ifserved()
	{
		
		final SharedPreferences prefs = getSharedPreferences(
				MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
		
		
		Log.d("raghav", ""+prefs.getBoolean(SER_REG, false));
		
		return prefs.getBoolean(SER_REG, false);
		
		
		
	}
	
	
	
	
	
	public void Sharewithserver(Activity mactivity)
	{
		
		Log.d("Startup", "into our S with Server func");
		
		if(Check_ifserved())
		{
			
			Log.v("raghav", "server already served");
			startActivity(in);
			finish();
			mactivity.finish();
			return ;
		}
		
		
		
		
		
		if(cd.isConnectingToInternet())
		{
			//Log.w("Startup", "Internet available,start register in background ");
		//registerInBackground();
		}
		else
		{Log.d("Startup", "Internet not available hence bye");
			Toast.makeText(context, "Internet not available", Toast.LENGTH_SHORT).show();
			startActivity(in);
			mactivity.finish();
			return;
		}
		
		
		
		
		
		
		final Context context = this;
     	regId=getRegistrationId(context);

     	
     	AsyncTaskActivity2 asyc2 =new AsyncTaskActivity2(mactivity);
     	asyc2.execute(Config.APP_SERVER_URL);
     	
		
		Log.d("Startup", "RegId refound :"+regId);

		
		//new .execute(Config.APP_SERVER_URL);
			
	////////////////need to execute a async task to end activity here;;;;;*******************
		
		
		
		
		
		
	}
	
	
	
	

	private class AsyncTaskActivity2 extends AsyncTask<String, Void, String> {

		Activity mActivity;
		    public AsyncTaskActivity2(Activity activity)
		    {
		         super();
		         this.mActivity=activity;
		    }
		    
		    
		    
		    
				@Override
			protected String doInBackground(String... params) {
				
				String response = null;
				
				
				HttpEntity httpEntity = null;
				HttpResponse httpResponse = null;
				
				 HttpClient httpclient = new DefaultHttpClient();
				    HttpPost httppost = new HttpPost(params[0]);
	
				    try {
				        // Add your data
				        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				        nameValuePairs.add(new BasicNameValuePair("gcm_id", regId));
				        
				        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	
				        // Execute HTTP Post Request
				        httpResponse = httpclient.execute(httppost);
				        
				        httpEntity = httpResponse.getEntity();
						response = EntityUtils.toString(httpEntity);
				        
				    } catch (ClientProtocolException e) {
				        // TODO Auto-generated catch block
				    	e.printStackTrace();
				    } catch (IOException e) {
				        // TODO Auto-generated catch block
				    	e.printStackTrace();
				    }
				
				
				
			
				
				return response;
	
			}
		    
		    
				@Override
				protected void onPostExecute(String result) {
					
					
					Log.d("raghav rocks", ""+result);
					Intent in1=new Intent(mActivity, MainActivity.class);
					
					if(result==null)
					{Toast.makeText(getApplicationContext(), "Check Network Authentication", Toast.LENGTH_SHORT).show();
						startActivity(in1);
						this.mActivity.finish();
						return;
					}
					
					
					if(result.equals("1op0"))
					{
						Log.d("Null pointer", "result from server"+result);
						
					StoreinSP();	
					
					
					
					 if(Constans_.Started==1)
		              {
		            	  return;
		              }
					
					
						startActivity(in1);
						this.mActivity.finish();
//						
					}
					else 
					{
						Log.d("Startup", "Server Reg not done");
						
						
						 if(Constans_.Started==1)
			              {
			            	  return;
			              }
						
						
						startActivity(in1);
						this.mActivity.finish();
					}
					
					
					//shareRegidTask = null;
					//Toast.makeText(getApplicationContext(), result,
						//	Toast.LENGTH_LONG).show();
				}
		    
		    
		    
		}
	
	
	
	//private class AsyncTaskActivity extends AsyncTask<String, String, String>() {
		
		
		
//		Activity mActivity;
//		
//		  public AsyncTaskActivity(Activity activity)
//		    {
//		         super();
//		         this.mActivity=activity;
//		    }
//		
//		
//		
//		
	//	@Override
//		protected String doInBackground(String... params) {
//			
//			String response = null;
//			
//			
//			HttpEntity httpEntity = null;
//			HttpResponse httpResponse = null;
//			
//			 HttpClient httpclient = new DefaultHttpClient();
//			    HttpPost httppost = new HttpPost(params[0]);
//
//			    try {
//			        // Add your data
//			        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
//			        nameValuePairs.add(new BasicNameValuePair("gcm_id", regId));
//			        
//			        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//
//			        // Execute HTTP Post Request
//			        httpResponse = httpclient.execute(httppost);
//			        
//			        httpEntity = httpResponse.getEntity();
//					response = EntityUtils.toString(httpEntity);
//			        
//			    } catch (ClientProtocolException e) {
//			        // TODO Auto-generated catch block
//			    } catch (IOException e) {
//			        // TODO Auto-generated catch block
//			    }
//			
//			
//			
//		
//			
//			return response;
//
//		}
//
//		@Override
//		protected void onPostExecute(String result) {
//			
//			
//			Log.d("raghav rocks", ""+result);
//			
//			
//			
//			if(result.equals("1op0"))
//			{
//				Log.d("Null pointer", "result from server"+result);
//				
//			StoreinSP();	
//				startActivity(in);
//				finish();
//				
//			}
//			else 
//			{
//				Log.d("Startup", "Server Reg not done");
//				
//				startActivity(in);
//				finish();
//			}
//			
//			
//			//shareRegidTask = null;
//			Toast.makeText(getApplicationContext(), result,
//					Toast.LENGTH_LONG).show();
//		}

//	}
	
	
	
	
	
	public void StoreinSP()
	{
		
		Log.d("Startup", "Storing Server reg in Sp and finnishing");
		
		final SharedPreferences prefs = getSharedPreferences(
				MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
		
		
		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean(SER_REG, true);
		
		editor.commit();
		
		
//		startActivity(in);
//		finish();
	}
	
	
	
	
	
	
	
	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	
	
	
	

}
