package com.example.udghosh2;




import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockActivity;
import com.devspark.sidenavigation.ISideNavigationCallback;
import com.devspark.sidenavigation.SideNavigationView;
import com.devspark.sidenavigation.SideNavigationView.Mode;
import com.example.adapters.CardInflater;

import com.markupartist.android.widget.PullToRefreshListView;
import com.markupartist.android.widget.PullToRefreshListView.OnRefreshListener;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuInflater;

import android.R.integer;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;



import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends SherlockActivity implements ISideNavigationCallback{

	BaseInflaterAdapter<CardItemData> adapter;
	PullToRefreshListView list;
	private SideNavigationView sideNavigationView;
	public static DisplayImageOptions options;
	ConnectionDetector cd;

	private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        
        
        Constans_.Curr_Act=1;
        
        
        
        
        
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
                new GetDataTask().execute();
            }
        });
        
        
		list.addHeaderView(new View(this));
		list.addFooterView(new View(this));
		 adapter = new BaseInflaterAdapter<CardItemData>(new CardInflater());
		
		list.setAdapter(adapter);

		
		for (int i = 0; i < 50; i++)
		{
			CardItemData data = new CardItemData("Item " + i + " Line 1", "Item " + i + " Line 2", "Item " + i + " Line 3");
			adapter.addItem(data, false);
		}

		
        
        
        
        

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


    
    
    private class GetDataTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                ;
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            //mListItems.addFirst("Added after refresh...");

            // Call onRefreshComplete when the list has been refreshed.
            //((PullToRefreshListView) getListView()).onRefreshComplete();
        	
        	CardItemData data = new CardItemData("new item", "new item", "new item");
			adapter.addItem(data, false);
			
			list.onRefreshComplete();

            super.onPostExecute(result);
        }
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
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected String doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(Config.APP_GET_URL, ServiceHandler.GET);

			Log.d("Response: ", "> " + jsonStr);

//			if (jsonStr != null) {
//				try {
//					JSONObject jsonObj = new JSONObject(jsonStr);
//					
//					// Getting JSON Array node
//					contacts = jsonObj.getJSONArray(TAG_CONTACTS);
//
//					// looping through All Contacts
//					for (int i = 0; i < contacts.length(); i++) {
//						JSONObject c = contacts.getJSONObject(i);
//						
//						String id = c.getString(TAG_ID);
//						String name = c.getString(TAG_NAME);
//						String email = c.getString(TAG_EMAIL);
//						String address = c.getString(TAG_ADDRESS);
//						String gender = c.getString(TAG_GENDER);
//
//						// Phone node is JSON Object
//						JSONObject phone = c.getJSONObject(TAG_PHONE);
//						String mobile = phone.getString(TAG_PHONE_MOBILE);
//						String home = phone.getString(TAG_PHONE_HOME);
//						String office = phone.getString(TAG_PHONE_OFFICE);
//
//						// tmp hashmap for single contact
//						HashMap<String, String> contact = new HashMap<String, String>();
//
//						// adding each child node to HashMap key => value
//						contact.put(TAG_ID, id);
//						contact.put(TAG_NAME, name);
//						contact.put(TAG_EMAIL, email);
//						contact.put(TAG_PHONE_MOBILE, mobile);
//
//						// adding contact to contact list
//						contactList.add(contact);
//					}
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//			} else {
//				Log.e("ServiceHandler", "Couldn't get any data from the url");
//			}

			return jsonStr;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			/**
			 * Updating parsed JSON data into ListView
			 * */
			
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
			Log.d("RAGHAV", result);
//			ListAdapter adapter = new SimpleAdapter(
//					MainActivity.this, contactList,
//					R.layout.list_item, new String[] { TAG_NAME, TAG_EMAIL,
//							TAG_PHONE_MOBILE }, new int[] { R.id.name,
//							R.id.email, R.id.mobile });
//
//			setListAdapter(adapter);
		}

	}
    
    
    

}
