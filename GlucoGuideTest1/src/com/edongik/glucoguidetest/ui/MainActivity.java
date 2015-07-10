/**
 * Title : main activity
 * Create Date : July. 9.2015
 * Author : Dong Ik Lee
 */
package com.edongik.glucoguidetest.ui;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edongik.glucoguidetest.R;
import com.edongik.glucoguidetest.adapter.CustomAdapter;
import com.edongik.glucoguidetest.api.MyItem;


public class MainActivity extends BaseActivity implements OnScrollListener{

	private static final String LOG = "MainActivity";
	
	AlertDialog.Builder dialog;
	private CustomAdapter mAdapter ;
	private ListView mListView;
	private LayoutInflater mInflater;
	private ArrayList<MyItem> mRowList;
	private boolean mLockListView;
		  
	private SQLiteDatabase db=null;
	
	public int totalSize = 0;
	private int TOTALVOLUM = 0;
	private int ROW_VOLUME = 30;
	public int HEIGH_TO_SCROLL = 90;
	public int POSITION_BY_SCROLL = 0;	
	public int SORT_NUMBER = 4;
	
    
    ImageView btnSortList;
	public TextView textSeconds;
	public TextView textQuestion;
	public TextView textTitle;
	
	View footer;    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		initActivity();
		
		/**************************
		 * Text view
		 *********************/
		
		textTitle = (TextView)findViewById(R.id.textTitle);
		textTitle.setTextColor(Color.parseColor("#3A465A"));
		Typeface font = Typeface.createFromAsset(this.getAssets(),
				"Roboto-Regular.ttf");
		textTitle.setTypeface(font);
		
	    //List view
	    mRowList = new ArrayList<MyItem>();
	    mLockListView = true;
	    
		/**************************
		 * initialize Data 
		 */
	    
	    initData();
	    addItems(ROW_VOLUME);	
	    
		/**************************
		 * Layout
		 *********************/
	    mAdapter = new CustomAdapter(this, mRowList);
	    mListView = (ListView) findViewById(R.id.listView1);
	    
        footer = getLayoutInflater().inflate(R.layout.footer, null, false);
        mListView.addFooterView(footer);
        
	    mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    mListView.setOnScrollListener(this);
	    mListView.setAdapter(mAdapter);
	}
	
	public void onReloadListView() {
	
	}	
	
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
	{
		int count = totalItemCount - visibleItemCount;
		
		if(firstVisibleItem >= count && totalItemCount != 0
		  && mLockListView == false)
		{
		      
		      if(TOTALVOLUM>totalSize){
		    	  addItems(ROW_VOLUME);
		      }else{
				 if(mListView.getFooterViewsCount() > 0){
					 mListView.removeFooterView(footer);
				 }
		      }
		}  
	}
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState)
	{
	}
	  
	@SuppressWarnings("deprecation")
	private void addItems(final int localSize)
	{
		mLockListView = true;
		
		Runnable run = new Runnable()
		{
			@Override
			public void run()
			{
			      MyItem myRowItem;
				  for(int i=0; i< localSize;i++){
					  
					  if(i+totalSize >= imageList.size()) break;
					  
					  int rowId = i;
					  String imageUrl = imageList.get(i+totalSize);
					  
					  myRowItem = new MyItem(rowId
								, imageUrl
								);
						
					  mRowList.add(myRowItem);
				  }  	
					
					if(imageList.size()==0  ){
						
						myRowItem = new MyItem(0, "\n There is no more image to show...");
						mRowList.add(myRowItem);
					}

				mAdapter.notifyDataSetChanged();
		        mLockListView = false;
		        
		        totalSize +=  localSize;
			}
		};
	    
		// 
		Handler handler = new Handler();
		handler.postDelayed(run, 500);
	}	

	private void initData() {
		  TOTALVOLUM = imageList.size();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    
	}	
	
	public void onResume() {
	    super.onResume();
	}     
	    
	public void onDestroy() {
		super.onDestroy();
	}   
	
	protected void onPause() {
	     // TODO Auto-generated method stub
		super.onPause();
		
	}	
	
	 @Override
	protected void onRestart() {
	        // TODO Auto-generated method stub
	        super.onRestart();
	}	

}
