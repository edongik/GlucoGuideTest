/**
 * Title : loading activity
 * Create Date : July. 9.2015
 * Author : Dong Ik Lee
 */
package com.edongik.glucoguidetest.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.edongik.glucoguidetest.R;

/**
 * Loading Activity
 * @author DONG IK LEE
 *
 */
public class LoadingActivity extends BaseActivity {
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		
		imageList = new ArrayList<String>() ;	
		
		Runnable run = new Runnable()
		{
			@Override
			public void run()
			{
				readImagesUrl();
			}
		};
		Handler handler = new Handler();
		handler.postDelayed(run, 500);	
    }
	
	public void readImagesUrl() {
		//adsf
		AssetManager am = getApplicationContext().getAssets();
		InputStream inputStream;
		BufferedReader reader;
		
		try {
			inputStream = am.open("imageurls.txt");
			reader = new BufferedReader(new InputStreamReader(inputStream));
			
			String line;
            while ((line = reader.readLine()) != null) {
            	imageList.add(line);
            	//Log.i("loading...", line);
            }
            reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            goToMainActivity();
        }
	}   
	
	public void goToMainActivity(){
		
		Intent it = new Intent(getBaseContext(), MainActivity.class);
		startActivity(it);
		finish();		
	}	
	
    public void onResume() {
	    super.onResume();
	} 	
	@Override
	public void onDestroy() {
	    super.onDestroy();
	}
}