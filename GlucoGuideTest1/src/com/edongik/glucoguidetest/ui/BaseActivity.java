package com.edongik.glucoguidetest.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import com.edongik.glucoguidetest.R;
import com.edongik.glucoguidetest.api.AppProperty;


/**
 * BaseActitivy
 * @author DONG IK LEE
 *
 */
public class BaseActivity extends FragmentActivity  implements OnTouchListener, TextToSpeech.OnInitListener {
	
	
	public static final String BASETAG = "BaseActivity";
	
	public static ArrayList<String> imageList;	
	AlertDialog.Builder dialog;		
	Intent intent;
	AppProperty appProperty = new AppProperty();

	/**********************************
	* Layout
	****************/
	
	public String name;

	String currentActivity = "";
	public void initActivity() {
		ActivityList.activityList.add(this);      
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

	}

	public static final int ACTIVITY_MENU = 26028;
	
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }	

   

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
  
	
	public boolean onOptionsItemSelected(MenuItem item) {
		  switch (item.getItemId()) {
		  case R.id.action_settings :
			  //Log.i("MeaningActivity", "Menu exit");
			  finishAllActivity();
			  System.exit(0);
			  return true; 
		  }
		return false;
	}
	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			
			//HERE
			finishShow();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}    
    
	public void finishShow() {
		dialog = new AlertDialog.Builder(this);
		dialog.setTitle(R.string.app_name);
		dialog.setMessage("Are you sure to exit ?");
		dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				//finish();
				finishAllActivity();
				System.exit(0);
			}
		});
		dialog.setNeutralButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		dialog.setIcon(R.drawable.ic_launcher);
		dialog.show();
	} 	
	
	public void finishAllActivity() {
		  for (int i = 0; i < ActivityList.activityList.size(); i++) {
			  ActivityList.activityList.get(i).finish();  
		  }
	} 	
	
	public Boolean checkNetworkStatus() {
		
		ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
				  
		if(cm.getActiveNetworkInfo()==null){
			 return false;
		}else if(cm.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) {
			  return true;
		}else{
			  return false;
		}
	} 

	
	public void onResume() {
		
	    super.onResume();
	} 	
	
	@Override
	public void onDestroy() {

			
	    super.onDestroy();
	}	
	  

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		
	} 	  
	  

	  
}