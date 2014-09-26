package com.sharespirit.udghosh2;




import org.w3c.dom.Text;

import com.actionbarsherlock.app.SherlockActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class contacts extends Activity{
	
	String[] phone1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
	setContentView(R.layout.contacts);
		
	Typeface tf = Typeface.createFromAsset(getAssets(),
	        "fonts/synct.ttf");
	
	
	Toast.makeText(getApplicationContext(), "Click To Call",Toast.LENGTH_SHORT).show();
	TextView tv=(TextView) findViewById(R.id.ourteam);
	tv.setTypeface(tf);
	
	String[] fstc1=getResources().getStringArray(R.array.coordinators_sync);
	 phone1=getResources().getStringArray(R.array.phone_sync);
	String[] email=getResources().getStringArray(R.array.email_sync);
	
	ListView lv1=(ListView)findViewById(R.id.cntcs_lv2);
	  lv1.setItemsCanFocus(true);
	
	lv1.setAdapter(new contcs_adapter(this, fstc1, phone1,email));
	
		
		lv1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				
				TextView tv=(TextView) view.findViewById(R.id.txtv1);
				
				TextView tv2=(TextView)view.findViewById(R.id.txtv2);
				
				
				Toast.makeText(getApplicationContext(), "Calling "+tv.getText().toString(), Toast.LENGTH_SHORT).show();
				
				
				
				try {
			        Intent callIntent = new Intent(Intent.ACTION_CALL);
			        callIntent.setData(Uri.parse("tel:"+tv2.getText().toString()));
			        startActivity(callIntent);
			    } catch (ActivityNotFoundException e) {
			        Log.e("helloandroid dialing example", "Call failed", e);
			    }
				
				
			}
		});
	
	
	
	}
	
	
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		
		//activityswitcher.animationIn(findViewById(R.id.id_contacts), getWindowManager());
		
		super.onResume();
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
	
	

//		final Intent intent = new Intent(getApplicationContext(), mainactivity2.class);
//		// disable default animation for new intent
//		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//		activityswitcher.animationOut(findViewById(R.id.id_contacts), getWindowManager(), new activityswitcher.AnimationFinishedListener() {
//			@Override
//			public void onAnimationFinished() {
//				finish();
//				startActivity(intent);
//			}
//		});
//	
	
	
	}
	
	
	/*
	 * 
	 * 
	 * 
	 * 
				
				
				try {
			        Intent callIntent = new Intent(Intent.ACTION_CALL);
			        callIntent.setData(Uri.parse("tel:123456789"));
			        startActivity(callIntent);
			    } catch (ActivityNotFoundException e) {
			        Log.e("helloandroid dialing example", "Call failed", e);
			    }
	 * 
	 * 
	 */
	
	

}
