package com.example.udghosh2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class PathGoogleMapActivity extends SherlockFragmentActivity {

	private static final LatLng LOWER_MANHATTAN = new LatLng(40.722543,
			-73.998585);
	private static final LatLng BROOKLYN_BRIDGE = new LatLng(40.7057, -73.9964);
	private static final LatLng WALL_STREET = new LatLng(40.7064, -74.0094);

	GoogleMap googleMap;
	final String TAG = "PathGoogleMapActivity";
	ConnectionDetector cd;
	
	
	Marker S,D;
	Polyline P;
	int source,desti;
	
	
	
	  String [][]anArray = {{ "Hall 1", "26.509635709361234", "80.2316951751709" },
              { "Hall 2","26.510557","80.229893" },
              {"Hall 3","26.508138","80.229743"},
            
              { "Hall 4", "26.507063","80.232038" },
              { "Hall 5","26.509636","80.228648" },
              {"GH1"," 26.507792","80.23309"},
              { "Hall 7", "26.507139","80.227897" },
              { "Hall 8", "26.505469","80.228391"},
              {"Hall 9","26.5081","80.226481"},
              { "Hall 10", "26.506429","80.226631"},
              {"Outreach Auditorium","26.509002","80.233583"},
              {"Auditorium", "26.513438","80.236223"},
              {"Hall 1 Parking Lot", "26.509098","80.231888" },
              {"Audi Grounds", " 26.513438","80.235965"},
              {"Tutorial Block", "26.510711","80.232167"},      
              {"SAC", "26.510365","80.235386"},
              {"Hindi Lits Desk","26.509098","80.231888"},                       
              {"Old Sac Parking Lot", "26.510365","80.235386"},   
              {"Street Dance Floor","26.509098","80.231888"}, 
              {"Informals Stage","26.509098","80.231888"},   
              {"Convocation Grounds","26.509098","80.231888"},   
              {"Hall  1 Parking Lot", "26.509098","80.231888"},   
              {"OAT", "26.505085","80.23015"},   
              {"L1", "26.511345","80.233176"},   
              {"L2", "26.511345","80.233176"},   
              {"L3", "26.511345","80.233176"},                         
              {"L4", "26.511345","80.233176"},   
              {"L5", "26.511345","80.233176"},   
              {"L6", "26.511345","80.233176"},   
              {"L7", "26.511345","80.233176"},   
              {"L8", "26.511345","80.233176"},                         
              {"L9", "26.511345","80.233176"},   
              {"L10", "26.511345","80.233176"}, 
              {"L12", "26.511345","80.233176"},   
              {"L13", "26.511345","80.233176"},                         
              {"L14", "26.511345","80.233176"},   
              {"L15", "26.511345","80.233176"},   
              {"L16", " 26.510865","80.233326"},     
              {"L17", " 26.510865","80.233326"},   
              {"GH Road", " 26.507792","80.23309"},   
              {"In front of Hall  3", " 26.508176","80.23073"}, 
              {"Outreach Hall  of Fame", " 26.509079","80.233884"}  
               };
	
	int i;
	String[] names;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_path_google_map);
		
		S=null;
		D=null;
		P=null;
		names=new String[anArray.length+1];
////		
		names[0]="My Current Location";
		////		
		for(i=0;i<anArray.length;i++)
		{
			
			names[i+1]=anArray[i][0];
			
			
		}
		
		source=-1;
		desti=-1;
//		
//		
	
		cd=cd = new ConnectionDetector(getApplicationContext());
		
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
	            BitmapDrawable bg = (BitmapDrawable)getResources().getDrawable(R.drawable.bg_striped);
	            bg.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
	            getSupportActionBar().setBackgroundDrawable(bg);

	            BitmapDrawable bgSplit = (BitmapDrawable)getResources().getDrawable(R.drawable.bg_striped_split_img);
	            bgSplit.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
	            getSupportActionBar().setSplitBackgroundDrawable(bgSplit);
	        }
//		
//		
		SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		googleMap = fm.getMap();
		
		
		googleMap.setMyLocationEnabled(true);
		

		// Enable / Disable zooming controls
		googleMap.getUiSettings().setZoomControlsEnabled(true);

		// Enable / Disable my location button
		googleMap.getUiSettings().setMyLocationButtonEnabled(true);

		// Enable / Disable Compass icon
		googleMap.getUiSettings().setCompassEnabled(true);

		// Enable / Disable Rotate gesture
		googleMap.getUiSettings().setRotateGesturesEnabled(true);

		// Enable / Disable zooming functionality
		googleMap.getUiSettings().setZoomGesturesEnabled(true);
		
		

		
		
		
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		
		if(item.getTitle()=="From")
		{
			
			
			
			

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Select Source");
			builder.setItems(names, new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int item) {
			        Toast.makeText(getApplicationContext(), names[item], Toast.LENGTH_SHORT).show();
			       
			        if(item==0)
			        {
			        	source=-3;
			        	 setSourcepointer("Current Location", new LatLng(googleMap.getMyLocation().getLatitude(), googleMap.getMyLocation().getLongitude()));
			        	
			        }
			        else
			        {
			        setSource(item-1);
			        
			        }
			        zoomer();
			    }
			});
			AlertDialog alert = builder.create();
			alert.show();
			
			
			
		}
		else if(item.getTitle()=="To")
		{
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Select Destination");
			builder.setItems(names, new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int item) {
			        Toast.makeText(getApplicationContext(), names[item], Toast.LENGTH_SHORT).show();
			         
			        
			        
			        if(item==0)
			        {
			          desti=-3;
			          setDestipointe("Current Location", new LatLng(googleMap.getMyLocation().getLatitude(), googleMap.getMyLocation().getLongitude()));
			          
			        }
			        else
			        {
			        setDesti(item-1);
			       
			        }
			        
			        zoomer(); 
			    }
			});
			AlertDialog alert = builder.create();
			alert.show();
			
			
		}
		else if(item.getTitle()=="Find")
		{
			if(source==-1||desti==-1)
			{
				Toast.makeText(getApplicationContext(), "Select both, Source & Destination", Toast.LENGTH_SHORT).show();
				
			}
			
		    else if(source>=0&&desti>=0)
			{
		    	if (cd.isConnectingToInternet()) {
					// Internet Connection is not present
					//Toast.makeText(getApplicationContext(), "Not connected to internet", Toast.LENGTH_SHORT).show();
					// stop executing code by return
		    		draw_path();
					zoomer();
				}
				
				else
				{
					
					Toast.makeText(getApplicationContext(), "not connected ", Toast.LENGTH_SHORT).show();
				}
		    	
				
			}
			else if(source==-3||desti==-3)
			{
				if (cd.isConnectingToInternet()) {
				
					draw_path2();
					zoomer();
				}
				
				else
				{
					
					Toast.makeText(getApplicationContext(), "not connected ", Toast.LENGTH_SHORT).show();
				}
				
			}			
			
		}
		
		
		
		return super.onOptionsItemSelected(item);
	}
	
	
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("From")
            
            .setShowAsAction( MenuItem.SHOW_AS_ACTION_ALWAYS);
            
        menu.add("To")
        
        
        .setShowAsAction( MenuItem.SHOW_AS_ACTION_ALWAYS);

        menu.add("Find")
            .setIcon(R.drawable.arrow_right)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

       

        return super.onCreateOptionsMenu(menu);
    }
	
	
	
	
	void setSourcepointer(String name,LatLng l)
	{
		
		
		if(S!=null)
		{S.remove();
		
		if(P!=null)
		{
			P.remove();
		}
		
		
		}
		
	S=googleMap.addMarker(new MarkerOptions()
        .position(l)
        .title(name)
        
        .visible(true)
        .snippet("Source")
        .icon(BitmapDescriptorFactory
            .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
		
		S.showInfoWindow();
		
	}
	
	
	

	public void setSource(int source) {
		this.source = source;
		
		LatLng l=new LatLng(Double.parseDouble(anArray[source][1]), Double.parseDouble(anArray[source][2]));
		
		
		setSourcepointer(anArray[source][0], l);
	}




void setDestipointe(String name,LatLng l)
{
	if(D!=null)
	{
		D.remove();
		if(P!=null)
		{
			P.remove();
		}
	}
	
	
	D=googleMap.addMarker(new MarkerOptions()
    .position(l)
    .title(name)
    .snippet("Destination")
    .visible(true)
    .icon(BitmapDescriptorFactory
        .defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
	
	
D.showInfoWindow();

}



	public void setDesti(int desti) {
		this.desti = desti;
	
LatLng l=new LatLng(Double.parseDouble(anArray[desti][1]), Double.parseDouble(anArray[desti][2]));
		
		setDestipointe(anArray[desti][0], l);
	
	
	}

	
	
	
	
	public void draw_path2()
	{
String url = null;		
		
		if(source==-3)
		{
			if(desti==-3)
			{
				
			}
			else
			{
				String waypoints = "waypoints=optimize:true|"
						+ googleMap.getMyLocation().getLatitude() + "," + googleMap.getMyLocation().getLongitude()
						+ "|" + anArray[desti][1] + ","
						+ anArray[desti][2];

				String sensor = "sensor=false";
				String params = waypoints + "&" + sensor;
				String output = "json";
				 url = "https://maps.googleapis.com/maps/api/directions/"
						+ output + "?" + params;
				
			}
		}
		else
		{
			if(desti==-3)
			{
				String waypoints = "waypoints=optimize:true|"
						+ anArray[source][1] + "," + anArray[source][2]
						+ "|" + googleMap.getMyLocation().getLatitude() + ","
						+ googleMap.getMyLocation().getLongitude();

				String sensor = "sensor=false";
				String params = waypoints + "&" + sensor;
				String output = "json";
				 url = "https://maps.googleapis.com/maps/api/directions/"
						+ output + "?" + params;

				
				
			}
			else
			{
				
			}
			
		}
		
		
		if(url!=null)
		{
		ReadTask downloadTask = new ReadTask();
		downloadTask.execute(url);
		}
		else
		{
			Toast.makeText(getApplicationContext(), "Same positions selected", Toast.LENGTH_SHORT).show();
		}

	}	
	
	
	
	

public void draw_path()
{
	
	String url = getMapsApiDirectionsUrl(source,desti);
	ReadTask downloadTask = new ReadTask();
	downloadTask.execute(url);


}





public void zoomer()
{
	LatLng l;
	
	if(source>=0)
	{
	l=new LatLng(Double.parseDouble(anArray[source][1]), Double.parseDouble(anArray[source][2]));
	}
	else if(desti>=0)
	{
		 l=new LatLng(Double.parseDouble(anArray[desti][1]), Double.parseDouble(anArray[desti][2]));
		
	}
	else
	{
		l=new LatLng(googleMap.getMyLocation().getLatitude(),googleMap.getMyLocation().getLongitude());
		
	}
	
	googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(l, new Float("15.5")));
	


}




	private String getMapsApiDirectionsUrl(int source,int destination) {
		
		
		
		
		
		
		String waypoints = "waypoints=optimize:true|"
				+ anArray[source][1] + "," + anArray[source][2]
				+ "|" + anArray[destination][1] + ","
				+ anArray[destination][2];

		String sensor = "sensor=false";
		String params = waypoints + "&" + sensor;
		String output = "json";
		String url = "https://maps.googleapis.com/maps/api/directions/"
				+ output + "?" + params;
		return url;
	}

	private void addMarkers() {
		if (googleMap != null) {
			googleMap.addMarker(new MarkerOptions().position(BROOKLYN_BRIDGE)
					.title("First Point"));
			googleMap.addMarker(new MarkerOptions().position(LOWER_MANHATTAN)
					.title("Second Point"));
			googleMap.addMarker(new MarkerOptions().position(WALL_STREET)
					.title("Third Point"));
		}
	}

	private class ReadTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... url) {
			String data = "";
			try {
				HttpConnection http = new HttpConnection();
				data = http.readUrl(url[0]);
			} catch (Exception e) {
				Log.d("Background Task", e.toString());
			}
			return data;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			new ParserTask().execute(result);
		}
	}

	private class ParserTask extends
			AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

		@Override
		protected List<List<HashMap<String, String>>> doInBackground(
				String... jsonData) {

			JSONObject jObject;
			List<List<HashMap<String, String>>> routes = null;

			try {
				jObject = new JSONObject(jsonData[0]);
				PathJSONParser parser = new PathJSONParser();
				routes = parser.parse(jObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return routes;
		}

		@Override
		protected void onPostExecute(List<List<HashMap<String, String>>> routes) {
			ArrayList<LatLng> points = null;
			PolylineOptions polyLineOptions = null;

			try {
				// traversing through routes
				for (int i = 0; i < routes.size(); i++) {
					points = new ArrayList<LatLng>();
					polyLineOptions = new PolylineOptions();
					List<HashMap<String, String>> path = routes.get(i);

					for (int j = 0; j < path.size(); j++) {
						HashMap<String, String> point = path.get(j);

						double lat = Double.parseDouble(point.get("lat"));
						double lng = Double.parseDouble(point.get("lng"));
						LatLng position = new LatLng(lat, lng);

						points.add(position);
					}

					polyLineOptions.addAll(points);
					polyLineOptions.width(2);
					polyLineOptions.color(Color.BLUE);
				}

				
				if(P!=null){
					P.remove();
				}
				
				P=googleMap.addPolyline(polyLineOptions);
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				Toast.makeText(getApplicationContext(), "Unable to build path", Toast.LENGTH_SHORT).show();
				
			}
		}
	}
}
