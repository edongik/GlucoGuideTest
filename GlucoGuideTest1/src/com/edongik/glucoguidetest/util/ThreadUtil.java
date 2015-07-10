package com.edongik.glucoguidetest.util;

import java.util.concurrent.atomic.AtomicBoolean;

import android.os.Handler;


public class ThreadUtil {
	AtomicBoolean isRunning=new AtomicBoolean(false);
	Handler mHandler;

    public ThreadUtil(Handler handler) { 
    	mHandler = handler;
    }
	
	public void commonThread(final long threadTime) {
	    //boolean isRunning = false;
		Thread background=new Thread(new Runnable() {
			public void run() {
				try {
					for (int i=0;i<1 && isRunning.get();i++) {
						Thread.sleep(threadTime);
						mHandler.sendMessage(mHandler.obtainMessage());
					}
				}
				catch (Throwable t) {
				}
			}
		});
	
		isRunning.set(true);
		background.start();
    }		
}
