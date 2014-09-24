package com.sharespirit.udghosh2;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

import com.sharespirit.adapters.carder2;


public class Schedule extends SherlockActivity {
	
	public static contcs_adapter adapter;
	String A="<u><b>DAY 1 :</b></u> <br/><b>Raethe live concert</b>  <i>7:00 PM</i><br/>Fun Zone Registration <i>6:30 PM</i><br/>Roadies Form Distribution <i>7:00 PM</i><br/>Board Games<i>8:00 PM- Midnight</i><br/>Play and Win <i>8:00 PM - Midnight</i><br/>Minute to Minute <i>8:00 PM - Midnight</i>";
//	String B="Day 2 Sportsmania	<i>6:30 PM - 8:00 PM</i><br/>Tambola <i>10:30 PM - 11:30 PM</i><br/>Minute To Minute <i>7:00 PM -Midnight</i><br/>Roadies(Prelims) 8:30 PM - 10:00 PM<br/>Board Games (Air Hockey, Twister) 6:00 PM - Midnight<br/>Play and Win	6:00 PM - Midnight<br/>Free Roll Poker	Midnight<br/>Auction Midnight;"
	String B="<b><u>DAY 2 :</b></u><br/>Sportsmania	<i>6:30 PM - 8:00 PM</i><br/>Tambola	<i>10:30 PM - 11:30 PM</i><br/>Minute To Minute <i>7:00 PM - Midnight</i><br/>Roadies(Prelims) <i>8:30 PM - 10:00 PM</i><br/>Board Games (Air Hockey, Twister) <i>6:00 PM - Midnight</i><br/>Play and Win <i>6:00 PM - Midnight</i><br/>Free Roll Poker	<i>Midnight</i><br/>Auction	<i>Midnight</i><br/><br/><b>DJ Nite-</b> <i>7:30 PM onwards</i>";
	String C="<b><u>DAY 3 :</b></u><br/>Board Games (Twister, Air Hockey) <i>5:00 PM - Midnight</i><br/>Play and Win <i>5:00 PM - Midnight</i><br/>Roadies II Round <i>6:00 PM</i><br/>Lottery	7:00 PM - <i>8:00 PM</i><br/>Betting	7:00 PM - <i>8:00 PM</i><br/>Scavanger Hunt <i>10:00 PM - 11:15 PM</i><br/>Treasure Hunt <i>Midnight</i><br/>Free Roll Poker	<i>Midnight</i><br/>Auction	From <i>Midnight</i>";
	String D1="<b><u>DAY 4 :</b></u><br/>Roadies Round Final <i>6:30 PM onwards</i><br/>Board Games <i>6:00 PM - 9:00 PM</i><br/>Lottery	7:00 PM - <i>8:00 PM</i><br/>Mega Auction <i>From 9:00 PM</i><br/><b>Felicitation ceremony- Dhanraj Pillay</b>- <i>7:00 PM</i>";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification_layout);
		
		
ListView  list=(ListView)findViewById(R.id.list_view); 
		


		
		String s[]={A,B,C,D1};
		 adapter = new contcs_adapter(getApplicationContext(),s);
		
		list.setAdapter(adapter);
		
	
	
		
		
	
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



	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 public class contcs_adapter extends BaseAdapter {

			String[] contacts;
			
			Context context;
			
			public contcs_adapter(Context context,String[] contacts1) {
				
				
			this.context=context;
				contacts=contacts1;
				
			}
			
			
			@Override
			public boolean isEnabled(int position) {
				// TODO Auto-generated method stub
			//	return super.isEnabled(position);
			
			
			return false;
			}
			
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return contacts.length;
			}

			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getItemId(int arg0) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public View getView(int position, View arg1, ViewGroup arg2) {
				// TODO Auto-generated method stub
				
				
				
				
				
				
				
				LayoutInflater inflater = (LayoutInflater) 
						context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				View gridView;


					gridView = new View(context);

					// get layout from mobile.xml
					gridView = inflater.inflate(R.layout.noti_card, null);

					// set value into textview
					TextView textView = (TextView) gridView
							.findViewById(R.id.not_card);
					textView.setText(Html.fromHtml(contacts[position]));

					// set image based on selected text
					TextView textView2 = (TextView) gridView
							.findViewById(R.id.noti_date);

					textView2.setText("OAT");
					
			        //TextView textView3=(TextView)gridView.findViewById(R.id.e);
				
					
					
					
				
			
				return gridView;

				
				
				
				
			
			}
			
			
			
			

		}

	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
	

}
