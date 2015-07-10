package com.edongik.glucoguidetest.util;

import java.util.Properties;

import android.content.Context;

import com.edongik.glucoguidetest.property.AssetsPropertyReader;

public class PropertyUtil {

    private AssetsPropertyReader assetsPropertyReader;
    private Context context;
    private Properties p;
    
    public PropertyUtil(Context localContext) {
    	context = localContext;
        assetsPropertyReader = new AssetsPropertyReader(context);
        p = assetsPropertyReader.getProperties("App.properties");    	
    }
    
    public String GetAppInfoUrl() {
    	return p.getProperty("appInfo.url");
    } 
}
