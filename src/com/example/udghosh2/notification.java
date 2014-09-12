package com.example.udghosh2;

import java.util.ArrayList;


import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

import com.example.adapters.carder2;


public class notification extends SherlockActivity {

	
	
	
	public static BaseInflaterAdapter<CardItemData> adapter;
	
	String asd;
	
	

	protected void onCreate(Bundle savedInstanceState) {
		
		  super.onCreate(savedInstanceState);
	        setContentView(R.layout.notification_layout);
		
	        
	        
	        
	   
	        
		  ListView  list=(ListView)findViewById(R.id.list_view); 
		
		list.addHeaderView(new View(this));
		list.addFooterView(new View(this));
		 adapter = new BaseInflaterAdapter<CardItemData>(new carder2());
		
		list.setAdapter(adapter);

		
		for (int i = 0; i < 50; i++)
		{
			addData("line "+i);
		}

		
	    
	    
		  getSupportActionBar().setDisplayHomeAsUpEnabled(true);


		
		
	};
	
		
	
	public static void addData(String ragahv)
	{
		
		
		CardItemData data = new CardItemData(" Line 1", " Line 2", ragahv);
		adapter.addItem(data, false);
		
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
