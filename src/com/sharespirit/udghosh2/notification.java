package com.sharespirit.udghosh2;

import java.util.ArrayList;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

import com.sharespirit.adapters.carder2;


public class notification extends SherlockActivity {

	
	
	
	public static BaseInflaterAdapter<CardItemData> adapter;
	
	String asd;
	
	SharedPreferences sharedpreferences;
	String[] noti_arr;
	public static Handler h;
	static String b;

	static String a;

	protected void onCreate(Bundle savedInstanceState) {
		
		  super.onCreate(savedInstanceState);
	        setContentView(R.layout.notification_layout);
		
	        if(Constans_.Curr_Act==2)
	        {
	        	
	        	finish();
	        	
	        }
	        
	        
	        h=new Handler(){
	        	
	        	@Override
	        	public void handleMessage(Message msg) {
	        		// TODO Auto-generated method stub
	        		super.handleMessage(msg);
	        		
	        		
	        		//Toast.makeText(getApplicationContext(), "received", Toast.LENGTH_SHORT).show();
	        		
	        		addData(msg.obj.toString());
	        		
	        		
	        	}
	        	
	        	
	        };
	        
	        
	        
	       Constans_.Curr_Act=2;
	        
	        
	        sharedpreferences = getSharedPreferences(Config.NOTI_PREF, Context.MODE_PRIVATE);
			
			String noti=sharedpreferences.getString("Notifications", "Tester Notification <Day 1>;");
	        
	        
	  noti_arr=noti.split(";");
			
			
			
	        
		ListView  list=(ListView)findViewById(R.id.list_view); 
		
		list.addHeaderView(new View(this));
		list.addFooterView(new View(this));
		 adapter = new BaseInflaterAdapter<CardItemData>(new carder2());
		
		list.setAdapter(adapter);

		int l;
		l=noti_arr.length;
		for (int i = 0; i < l; i++)
		{
			addData(noti_arr[l-i-1]);
		}

		
	    
	    
		  getSupportActionBar().setDisplayHomeAsUpEnabled(true);


		
		
	};
	
		
	
	public static void addData(String ragahv)
	{try {
		 a= ragahv.substring(ragahv.indexOf('<')+1,ragahv.indexOf('>'));
		 b=ragahv.substring(0, ragahv.indexOf('<')-1);
		
		
		
		
			
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
			
			
		}
	CardItemData data = new CardItemData(" Line 1",a, b);
		adapter.addItem(data, true);
		
	}
	
	
	
	
	

	
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	
	
	Constans_.Curr_Act=1;
	
	
	}
	
	
	
	
	
	
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle action bar item clicks here. The action bar will
	        // automatically handle clicks on the Home/Up button, so long
	        // as you specify a parent activity in AndroidManifest.xml.
	        int id = item.getItemId();
	        if (id == android.R.id.home) {
	        	
	        onBackPressed();
	        }
	        return super.onOptionsItemSelected(item);
	    }










	
	
	
	
	
	

}
