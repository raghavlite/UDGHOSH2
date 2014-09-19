package com.example.udghosh2;




import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockActivity;
import com.devspark.sidenavigation.ISideNavigationCallback;
import com.devspark.sidenavigation.SideNavigationView;
import com.devspark.sidenavigation.SideNavigationView.Mode;
import com.example.adapters.CardInflater;

import com.markupartist.android.widget.PullToRefreshListView;
import com.markupartist.android.widget.PullToRefreshListView.OnRefreshListener;



import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuInflater;

import android.R.integer;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;



import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends SherlockActivity implements ISideNavigationCallback{

	BaseInflaterAdapter<HashMap<String, String>> adapter;
	PullToRefreshListView list;
	private SideNavigationView sideNavigationView;
	public static DisplayImageOptions options;
	ConnectionDetector cd;
	File myInternalFile;
	private String filepath = "MyFileStorage";
	private String filename = "MySampleFile.txt";
	private String TAG_URL="url";
	private String TAG_INFO="info";
	ArrayList<HashMap<String, String>> contactList;
	ArrayList<HashMap<String, String>> oldList;
	private ProgressDialog pDialog;
	public static ImageLoader imageLoader;
   
	
	
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        
        
        Constans_.Curr_Act=1;
        
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File directory = contextWrapper.getDir(filepath, Context.MODE_PRIVATE);
        myInternalFile = new File(directory , filename);
        
        contactList = new ArrayList<HashMap<String, String>>();
        try {
			oldList=get_file();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       // Toast.makeText(getApplicationContext(), "old list is  "+oldList, Toast.LENGTH_SHORT).show();
        
		cd = new ConnectionDetector(getApplicationContext());

		// Check if Internet present
		if (!cd.isConnectingToInternet()) {
			// Internet Connection is not present
			Toast.makeText(getApplicationContext(), "Not connected to internet", Toast.LENGTH_SHORT).show();
			// stop executing code by return
			
		}
		
		else
		{
			
			new GetContacts().execute();
			
			
			Toast.makeText(getApplicationContext(), "connected ", Toast.LENGTH_SHORT).show();
		}
		
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
		.threadPriority(Thread.NORM_PRIORITY - 2)
		.denyCacheImageMultipleSizesInMemory()
		.diskCacheFileNameGenerator(new Md5FileNameGenerator())
		.diskCacheSize(50 * 1024 * 1024) // 50 Mb
		.tasksProcessingOrder(QueueProcessingType.LIFO)
		.writeDebugLogs() // Remove for release app
		.build();
// Initialize ImageLoader with configuration.
ImageLoader.getInstance().init(config);
        
		 imageLoader = ImageLoader.getInstance();
        
        
	
        
    	options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.displayer(new RoundedBitmapDisplayer(20))
		.build();
        
        
        
		//setContentView(R.layout.listview);

		//ListView list = (ListView)findViewById(R.id.list_view);

        
         list=(PullToRefreshListView)findViewById(android.R.id.list); 
        
        
        list.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Do work to refresh the list here.
              
            	if (!cd.isConnectingToInternet()) {
        			// Internet Connection is not present
        			Toast.makeText(getApplicationContext(), "Not connected to internet", Toast.LENGTH_SHORT).show();
        			// stop executing code by return
        			
        		}
        		
        		else
        		{
        			
        			new GetContacts().execute();
        			
        			
        			//Toast.makeText(getApplicationContext(), "connected ", Toast.LENGTH_SHORT).show();
        		}
            	
            }
        });
        
        
		list.addHeaderView(new View(this));
		list.addFooterView(new View(this));
		 adapter = new BaseInflaterAdapter<HashMap<String, String>>(new CardInflater());
		
		list.setAdapter(adapter);

		
	//	list.onRefresh();
		
		HashMap<String, String> contact = new HashMap<String, String>();
		//
								// adding each child node to HashMap key => value
								contact.put(TAG_URL, "drawable://" + R.drawable.ic_stub);
								contact.put(TAG_INFO, "Welcome to Udghosh<Team Udghosh>");
		
								int i;
								for(i=0;i<10;i++)
								{
									adapter.addItem(contact, true);
									
								}
								
								
								Toast.makeText(contextWrapper, "old is "+oldList, Toast.LENGTH_SHORT).show();
								for(i=0;i<oldList.size()-1;i++)
								{
									adapter.addItem(oldList.get(i), false);
								}
								
								
								try {
									adapter.addItem(oldList.get(oldList.size()-1), true);
								} catch (ArrayIndexOutOfBoundsException e) {
									// TODO: handle exception
								}
								
		
	   list.onRefreshComplete();
	   


        sideNavigationView = (SideNavigationView) findViewById(R.id.side_navigation_view);
        ArrayList<Integer> a=new ArrayList<Integer>();
        a.add(4);
        a.add(7);
        a.add(9);
        a.add(14);
        
        
        sideNavigationView.setHeads(a);
        
        sideNavigationView.setMenuItems(R.menu.main);
        sideNavigationView.setMenuClickCallback(this);
        sideNavigationView.setMode(Mode.LEFT);
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
    }

    
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
	
		CardInflater.AnimateFirstDisplayListener.displayedImages.clear();
		super.onDestroy();
	}
	
    
    public void updateUI(int p){
    	int i,j;
    	
    	
    	j=contactList.size()-1;
    	for(i=p;i<j;i++)
    	{
    		adapter.addItem(contactList.get(i), false);
    	}
    	try {
    		adapter.addItem(contactList.get(j), true);
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
		}
    	
    	
    	oldList=contactList;
    	
    }
    
    

    
    public void put_file()
    {
    	
    	
    	try {
			FileOutputStream fos = new FileOutputStream(myInternalFile);
			 ObjectOutputStream of = new ObjectOutputStream(fos);
	            of.writeObject(contactList);
	            of.flush();
	            of.close();
	            fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		  	
    	
    	
    }
    
    
    public ArrayList<HashMap<String, String>> get_file() throws ClassNotFoundException
    {
    	ArrayList<HashMap<String, String>> toReturn=new ArrayList<HashMap<String,String>>();
    	
   try {
		FileInputStream fis = new FileInputStream(myInternalFile);
		DataInputStream in = new DataInputStream(fis);
		ObjectInputStream oi = new ObjectInputStream(fis);

		
		toReturn = (ArrayList<HashMap<String, String>>) oi.readObject();
	       oi.close();
	} catch (IOException e) {
		e.printStackTrace();
		return toReturn;
	}
   
   return toReturn;

    	
    }
    
    
    
    
    
    

//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
        	
        	sideNavigationView.toggleMenu();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */


    @Override
    public void onSideNavigationItemClick(int itemId) {
        switch (itemId) {
            case R.id.item1:
               
            	Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
            	// invokeActivity(getString(R.string.title1), R.drawable.ic_android1);
                break;

            case R.id.item2:
            	Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
            	//invokeActivity(getString(R.string.title2), R.drawable.ic_android2);
                break;

            case R.id.item3:
            	Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();
                //invokeActivity(getString(R.string.title3), R.drawable.ic_android3);
                break;

            case R.id.item4:
            	Toast.makeText(getApplicationContext(), "4", Toast.LENGTH_SHORT).show();
                
                break;

            case R.id.item5:
            	Toast.makeText(getApplicationContext(), "5", Toast.LENGTH_SHORT).show();
                //invokeActivity(getString(R.string.title5), R.drawable.ic_android5);
//            	Intent in=new Intent(getApplicationContext(), PathGoogleMapActivity.class);
//            	startActivity(in);
            	
                break;

            case R.id.item6:
            	Toast.makeText(getApplicationContext(), "5", Toast.LENGTH_SHORT).show();
                //invokeActivity(getString(R.string.title5), R.drawable.ic_android5);
//            	Intent in2=new Intent(getApplicationContext(), PathGoogleMapActivity.class);
//            	startActivity(in2);
            	
                break;
                
            case R.id.item7:
            	Toast.makeText(getApplicationContext(), "5", Toast.LENGTH_SHORT).show();
                //invokeActivity(getString(R.string.title5), R.drawable.ic_android5);
//            	Intent in3=new Intent(getApplicationContext(), PathGoogleMapActivity.class);
//            	startActivity(in3);
            	
                break;
                
                
            case R.id.item8:
            	Toast.makeText(getApplicationContext(), "5", Toast.LENGTH_SHORT).show();
                //invokeActivity(getString(R.string.title5), R.drawable.ic_android5);
//            	Intent in4=new Intent(getApplicationContext(), PathGoogleMapActivity.class);
//            	startActivity(in4);
            	
                break; 
              
            case R.id.item9:
            	Toast.makeText(getApplicationContext(), "5", Toast.LENGTH_SHORT).show();
                //invokeActivity(getString(R.string.title5), R.drawable.ic_android5);
            	Intent in5=new Intent(getApplicationContext(), PathGoogleMapActivity.class);
            	startActivity(in5);
            	
                break;
                
            case R.id.item11:
            
                //invokeActivity(getString(R.string.title5), R.drawable.ic_android5);
            	Intent in11=new Intent(getApplicationContext(), notification.class);
            	startActivity(in11);
            	
            	
                break;
                
                
            case R.id.item13:
            	
                //invokeActivity(getString(R.string.title5), R.drawable.ic_android5);
            	Intent in6=new Intent(getApplicationContext(), contacts.class);
            	startActivity(in6);
            	
                break;
                
                
                
                
            default:
                return;
        }
       // finish();
    }
    
    
    
    
    
    
    private class GetContacts extends AsyncTask<Void, Void, String> {


		@Override
		protected String doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(Config.APP_GET_URL, ServiceHandler.GET);

			Log.d("Response: ", "> " + jsonStr);

			if (jsonStr != null) {
				try {
					//JSONObject jsonObj = new JSONObject(jsonStr);
					JSONArray jsonArr=new JSONArray(jsonStr);
					// Getting JSON Array node
//					contacts = jsonObj.getJSONArray(TAG_CONTACTS);
//
					if(jsonArr.length()==oldList.size())
					{
						return "0";
					}
				contactList=new ArrayList<HashMap<String,String>>();	
				// looping through All Contacts
					for (int i = 0; i < jsonArr.length(); i++) {
						JSONObject c = jsonArr.getJSONObject(i);
						
						String url= c.getString(TAG_URL);
						String info = c.getString(TAG_INFO);

						HashMap<String, String> contact = new HashMap<String, String>();

						contact.put(TAG_URL, Config.IMAGE_URL+url);
						contact.put(TAG_INFO, info);
                          
						contactList.add(contact);
						
					}
				} catch (JSONException e) {
					e.printStackTrace();
					
					return "0";
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
				
				return "0";
			}

			return "1";
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog

			
			list.onRefreshComplete();
			/**
			 * Updating parsed JSON data into ListView
			 * */
			if(result.contains("1"))
			{
				//Toast.makeText(getApplicationContext(), "new updates found  "+contactList, Toast.LENGTH_SHORT).show();
				Log.v("raghav",""+contactList);
				
				updateUI(oldList.size());
				
				put_file();
				
				//Toast.makeText(getApplicationContext(), ""+contactList, Toast.LENGTH_SHORT).show();
				
			}
			else
			{
				
				Log.v("raghav", "no new updates");
				
			}
			
			
		//	Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
			Log.d("RAGHAV", result);

		}

	}
    
    
    

}
