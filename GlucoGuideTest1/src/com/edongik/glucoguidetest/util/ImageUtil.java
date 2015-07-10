package com.edongik.glucoguidetest.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;


/**
 * @author Anthony Eden
 */
public class ImageUtil {

    private static final int DEFAULT_IMAGE_TYPE = 0;

    public ImageUtil() {
    	StrictMode.ThreadPolicy policy = new
    			StrictMode.ThreadPolicy.Builder()
    			.permitAll().build();
    			StrictMode.setThreadPolicy(policy);    	
    }
    public static Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
        	Log.d("MainActivity", "####### url: "+url);
        	
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
           
       } catch (IOException e) {
    	   e.printStackTrace();
       }
       return bm;
    }  
    
    
	public static ImageView getImageView(ImageView imageView, String imagePreviewUrl) {    
		
	    InputStream is = null;
	    Bitmap bm = null;

	    imageView.getLayoutParams().height = 120;
	    //imageView.getLayoutParams().width = 120;
	    
	    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	    imageView.setPadding(2, 2, 2, 2);
	    Log.d("MainActivity", "####### imagePreviewUrl: "+imagePreviewUrl);
	    
	    try{
	        is = new URL(imagePreviewUrl).openStream();
	        
	        Log.d("MainActivity", "####### is: "+is);
	        
	        bm = BitmapFactory.decodeStream(is);
	        is.close();   
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }
	
	    imageView.setImageBitmap(bm);
	
	    return imageView;
	}

}
