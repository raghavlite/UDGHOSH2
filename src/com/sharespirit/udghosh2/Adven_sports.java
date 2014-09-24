package com.sharespirit.udghosh2;

import android.os.Bundle;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.example.udghosh2.R;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class Adven_sports extends SherlockActivity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
	
		setContentView(R.layout.item_pager_image);
		
		
    	ImageView in=(ImageView)findViewById(R.id.image);
    	
    	
    	
    	
    	in.setImageResource(R.drawable.adven_sp);
    	
//    	
//MainActivity.imageLoader.displayImage("drawable://"+R.drawable.adven_sp, in, MainActivity.options, new SimpleImageLoadingListener() {
//			
//			});
	
	
	
	
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
