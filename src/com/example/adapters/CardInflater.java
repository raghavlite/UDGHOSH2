package com.example.adapters;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.example.udghosh2.BaseInflaterAdapter;

import com.example.udghosh2.IAdapterViewInflater;
import com.example.udghosh2.MainActivity;
import com.example.udghosh2.R;

import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: Justin
 * Date: 10/6/13
 * Time: 12:47 AM
 */
public class CardInflater implements IAdapterViewInflater<HashMap<String, String>>
{
	
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	
	@Override
	public View inflate(final BaseInflaterAdapter<HashMap<String, String>> adapter, final int pos, View convertView, ViewGroup parent)
	{
		ViewHolder holder;

		if (convertView == null)
		{
			
			
			
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.list_item_card, parent, false);
			holder = new ViewHolder(convertView);
		}
		else
		{
			
			Log.d("list debug", "inside covertview != null");
			holder = (ViewHolder) convertView.getTag();
		}

		final HashMap<String, String> item = (HashMap<String, String>) adapter.getTItem(pos);
		holder.updateDisplay(item);

		return convertView;
	}

	private class ViewHolder
	{
		private View m_rootView;
		private ImageView m_text1;
		private TextView m_text2;
		private TextView m_text3;

		public ViewHolder(View rootView)
		{
			m_rootView = rootView;
			m_text1 = (ImageView) m_rootView.findViewById(R.id.image);
			m_text2 = (TextView) m_rootView.findViewById(R.id.text2);
			m_text3 = (TextView) m_rootView.findViewById(R.id.text3);
			rootView.setTag(this);
		}

		public void updateDisplay(HashMap<String,String> item)
		{
			String info;
			String	 a,b;	
			info=item.get("info");
          
			
			try {
 
				 a= info.substring(info.indexOf('<')+1,info.indexOf('>'));
				
			} catch (IndexOutOfBoundsException e) {
				// TODO: handle exception
				a="Team Udghosh";
			}
		
		
		try {
			b=info.substring(0, info.indexOf('<'));
			
		} catch (Exception e) {
			// TODO: handle exception
			
			b=info;
		}
		
			
			
			
		//	info.subSequence(info.indexOf('<')+1, end)
			
		MainActivity.imageLoader.displayImage(item.get("url"),m_text1, MainActivity.options, animateFirstListener);
			
			m_text2.setText(b);
			m_text3.setText(a);
		}
	}
	
	
	
//	
	public static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

		public static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}
	
	
}
