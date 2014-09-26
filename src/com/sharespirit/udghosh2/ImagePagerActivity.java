/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.sharespirit.udghosh2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class ImagePagerActivity extends SherlockActivity {

	private static final String STATE_POSITION = "STATE_POSITION";
    
	DisplayImageOptions options;
	ImageView imageView;
	ViewPager pager;
	public static SharedPreferences prefs;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_image_pager);
		
		
		
		 prefs = getSharedPreferences(MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
		
		
		

		Intent in=getIntent();
		//assert bundle != null;
		ArrayList<HashMap<String, String>> imageUrls = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra(Config.IMAGES);
		int pagerPosition = (Integer) in.getSerializableExtra(Config.IMAGE_POSITION);

		if (savedInstanceState != null) {
			pagerPosition = savedInstanceState.getInt(STATE_POSITION);
		}

		options = new DisplayImageOptions.Builder()
			.showImageForEmptyUri(R.drawable.ic_empty)
			.showImageOnFail(R.drawable.ic_error)
			.resetViewBeforeLoading(true)
			.cacheOnDisk(true)
			.imageScaleType(ImageScaleType.EXACTLY)
			.bitmapConfig(Bitmap.Config.RGB_565)
			.considerExifParams(true)
			.displayer(new FadeInBitmapDisplayer(300))
			.build();

		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(new ImagePagerAdapter(imageUrls));
		pager.setCurrentItem(pagerPosition);
		pager.setDrawingCacheEnabled(true);
		
		pager.buildDrawingCache(true);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	
	
	
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Used to put dark icons on light action bar
      

        menu.add("Download")
            .setIcon( R.drawable.download )
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);



        return true;
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
	        
	        
	        if (item.getTitle()=="Download")
	        {
	        	
	        	save_image();
	        	
	        }	        
	        
	        
	        return super.onOptionsItemSelected(item);
	    }

	
	 public void  save_image()
	 {
		 
		 int p=prefs.getInt("storer", 1);
		 
								
         OutputStream output;
		 
	       pager.setDrawingCacheEnabled(true);

	        pager.buildDrawingCache(true);
	        pager.setDrawingCacheEnabled(true);

	        Bitmap bitmap = pager.getDrawingCache(true);
		 
         //Bitmap bitmap =get;
		 
         if(bitmap==null)
         {
        	 Toast.makeText(getApplicationContext(), "Falied", Toast.LENGTH_SHORT).show();
        	 return;
         }
         
		 
		 File filepath = Environment.getExternalStorageDirectory();
		 
         // Create a new folder in SD Card
         File dir = new File(filepath.getAbsolutePath()
                 + "/Udghosh/");
         dir.mkdirs();

         // Create a name for the saved image
         File file = new File(dir,p+".png");

         // Show a toast message on successful save
     
         try {

             output = new FileOutputStream(file);

             // Compress into png format image from 0% - 100%
             bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
             output.flush();
             output.close();
             Toast.makeText(getApplicationContext(), "Image Saved to /sdcard/Udghosh",Toast.LENGTH_SHORT).show();
             
         }

         catch (Exception e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
             
             Toast.makeText(getApplicationContext(), "Error Storing file", Toast.LENGTH_SHORT).show();
         }
		 
		 
         SharedPreferences.Editor editor = prefs.edit();
			editor.putInt("storer",p+1);
			
			editor.commit();
		 
		 
		 
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt(STATE_POSITION, pager.getCurrentItem());
	}

	private class ImagePagerAdapter extends PagerAdapter {

		private ArrayList<HashMap<String, String>> images;
		private LayoutInflater inflater;

		ImagePagerAdapter(ArrayList<HashMap<String, String>> images) {
			this.images = images;
			inflater = getLayoutInflater();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public int getCount() {
			return images.size();
		}

		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			View imageLayout = inflater.inflate(R.layout.item_pager_image, view, false);
			assert imageLayout != null;
			 imageView = (ImageView) imageLayout.findViewById(R.id.image);
			final ProgressBar spinner = (ProgressBar) imageLayout.findViewById(R.id.loading);


			
			
			MainActivity.imageLoader.displayImage(images.get(position).get("url"), imageView, options, new SimpleImageLoadingListener() {
				
				
				
				
				
				
				
				
				@Override
				public void onLoadingStarted(String imageUri, View view) {
					spinner.setVisibility(View.VISIBLE);
				}

				@Override
				public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
					String message = null;
					switch (failReason.getType()) {
						case IO_ERROR:
							message = "Input/Output error";
							break;
						case DECODING_ERROR:
							message = "Image can't be decoded";
							break;
						case NETWORK_DENIED:
							message = "Downloads are denied";
							break;
						case OUT_OF_MEMORY:
							message = "Out Of Memory error";
							break;
						case UNKNOWN:
							message = "Unknown error";
							break;
					}
					Toast.makeText(ImagePagerActivity.this, message, Toast.LENGTH_SHORT).show();

					spinner.setVisibility(View.GONE);
				}

				@Override
				public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
					spinner.setVisibility(View.GONE);
				}
			});
			
			
			
			
			TextView tv2=(TextView)imageLayout.findViewById(R.id.noti_date2);
			TextView tv3=(TextView)imageLayout.findViewById(R.id.noti_date);
			
			
			
			String info;
			String	 a,b;	
			info=images.get(position).get("info");
          
			
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
			
			
			
			tv2.setText(b);
			tv3.setText(a);
			

			view.addView(imageLayout, 0);
			return imageLayout;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view.equals(object);
		}

		@Override
		public void restoreState(Parcelable state, ClassLoader loader) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}
	}
}