package com.example.udghosh2;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockActivity;
import com.devspark.sidenavigation.ISideNavigationCallback;
import com.devspark.sidenavigation.SideNavigationView;
import com.devspark.sidenavigation.SideNavigationView.Mode;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuInflater;


import android.R.integer;

import android.content.Intent;
import android.os.Bundle;



import android.widget.Toast;
import android.os.Build;

public class MainActivity extends SherlockActivity implements ISideNavigationCallback{

	
	private SideNavigationView sideNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        sideNavigationView = (SideNavigationView) findViewById(R.id.side_navigation_view);
        ArrayList<Integer> a=new ArrayList<Integer>();
        a.add(1);
        a.add(2);
        a.add(4);
        
        sideNavigationView.setHeads(a);
        
        sideNavigationView.setMenuItems(R.menu.main);
        sideNavigationView.setMenuClickCallback(this);
        sideNavigationView.setMode(Mode.LEFT);
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
    }



//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
        	
        	sideNavigationView.toggleMenu();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */


    @Override
    public void onSideNavigationItemClick(int itemId) {
        switch (itemId) {
            case R.id.side_navigation_menu_item1:
               
            	Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
            	// invokeActivity(getString(R.string.title1), R.drawable.ic_android1);
                break;

            case R.id.side_navigation_menu_item2:
            	Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
            	//invokeActivity(getString(R.string.title2), R.drawable.ic_android2);
                break;

            case R.id.side_navigation_menu_item3:
            	Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();
                //invokeActivity(getString(R.string.title3), R.drawable.ic_android3);
                break;

            case R.id.side_navigation_menu_item4:
            	Toast.makeText(getApplicationContext(), "4", Toast.LENGTH_SHORT).show();
                
                break;

            case R.id.side_navigation_menu_item5:
            	Toast.makeText(getApplicationContext(), "5", Toast.LENGTH_SHORT).show();
                //invokeActivity(getString(R.string.title5), R.drawable.ic_android5);
            	Intent in=new Intent(getApplicationContext(), PathGoogleMapActivity.class);
            	startActivity(in);
            	
                break;

            default:
                return;
        }
       // finish();
    }

}
