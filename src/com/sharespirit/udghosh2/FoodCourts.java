package com.sharespirit.udghosh2;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.example.udghosh2.R;
import com.sharespirit.adapters.CardInflater;

public class FoodCourts extends SherlockActivity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification_layout);
		
		
		ArrayList<HashMap<String, String>> al=new ArrayList<HashMap<String,String>>();
		
		
		    HashMap<String, String> myMap = new HashMap<String, String>();
	        myMap.put("info", "$.Indian Cuisine\n Enjoy delicious indian traditional food<New Sac>");
	        myMap.put("url", "drawable://" + R.drawable.da_rec);
		
		    al.add(myMap);
	        
	        
	
	        
	        myMap = new HashMap<String, String>();
	        myMap.put("info", "$.China Town\n For all the chicken lovers<New Sac>");
	        myMap.put("url", "drawable://" + R.drawable.honey);
	        
	        al.add(myMap);
	        
	        myMap = new HashMap<String, String>();
	        myMap.put("info", "$.Crown Burger\n Cheeseburgers and Pastrami<New Sac>");
	        myMap.put("url", "drawable://" + R.drawable.burger);

	        al.add(myMap);
	        
	        myMap = new HashMap<String, String>();
	        myMap.put("info", "$.Dominos\n Crispy,Tender and Spicy Pizza<New Sac>");
	        myMap.put("url", "drawable://" + R.drawable.pizza);
		
	        al.add(myMap);
		
		
	        myMap = new HashMap<String, String>();
	        myMap.put("info", "$.CreamBell\n Cones,Kulfi and Bars<New Sac>");
	        myMap.put("url", "drawable://" + R.drawable.da_rec2); 
	        
	        al.add(myMap);
		
		
		
		ListView lv=(ListView)findViewById(R.id.list_view);
		
		 BaseInflaterAdapter<HashMap<String, String>> adapter = new BaseInflaterAdapter<HashMap<String, String>>(new CardInflater());
			
		 
		 adapter.addItems(al, true);
		lv.setAdapter(adapter);
		
	
	
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
