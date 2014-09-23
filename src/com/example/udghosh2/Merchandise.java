package com.example.udghosh2;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.example.udghosh2.Schedule.contcs_adapter;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class Merchandise extends SherlockActivity{
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.merchandise);
		
		
        	//ImageView in=(ImageView)findViewById(R.id.image);
        	
        	
        	
        	
        	
////MainActivity.imageLoader.displayImage("drawable://"+R.drawable.adven_sp, in, MainActivity.options, new SimpleImageLoadingListener() {
				
//				});
        	
        	
        	
        	
	
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
