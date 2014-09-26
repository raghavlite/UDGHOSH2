package com.sharespirit.udghosh2;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class contcs_adapter extends BaseAdapter {

	String[] contacts;
	String[] no;
	String[] email;
	Context context;
	
	
	public contcs_adapter(Context cntxt,String[] contacts1,String[] phone,String[] emal) {
		
		
		context=cntxt;
		contacts=contacts1;
		no=phone;
		email=emal;
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
		
		
		
		
		
		
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View gridView;

	

			gridView = new View(context);

			// get layout from mobile.xml
			gridView = inflater.inflate(R.layout.contactsspalser, null);
              
			
			
			
			
			
			// set value into textview
			TextView textView = (TextView) gridView
					.findViewById(R.id.txtv1);
			textView.setText(contacts[position]);

			// set image based on selected text
			TextView textView2 = (TextView) gridView
					.findViewById(R.id.txtv2);

			textView2.setText(no[position]);
			
	        //TextView textView3=(TextView)gridView.findViewById(R.id.e);
		
			TextView tvr=(TextView)gridView.findViewById(R.id.txtv3);
	
			tvr.setText(email[position]);
			
			
//			ImageView iv=(ImageView)gridView.findViewById(R.id.imagev_con);
//			
//			iv.setImageResource(R.drawable.ic_launcher);
			
			
			
			
			
			
		

		return gridView;

		
		
		
		
	
	}
	
	
	

}
