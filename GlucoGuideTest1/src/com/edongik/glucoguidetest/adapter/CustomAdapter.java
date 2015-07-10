package com.edongik.glucoguidetest.adapter;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.BitmapAjaxCallback;
import com.edongik.glucoguidetest.R;
import com.edongik.glucoguidetest.api.MyItem;

public class CustomAdapter extends BaseAdapter 
{
	private LayoutInflater mInflater;
	private ArrayList<MyItem> mRowList;
	private int selectedPosition = -1;	
	private Context mContext;
	
	//Image
	ImageParse imageTask;
	String pictureUrl = "";
	
	public CustomAdapter(Context context, ArrayList<MyItem> rowList)
	{
		this.mRowList = rowList;
		this.mInflater = LayoutInflater.from(context);
		this.mContext = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		final ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.row_image, null);
			holder = new ViewHolder();
			
			holder.ivPicture = (ImageView) convertView.findViewById(R.id.ivPicture);
			holder.tvImageName = (TextView) convertView.findViewById(R.id.tvImageName);
			
			
			convertView.setTag(holder);
			
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		pictureUrl = mRowList.get(position).getImageUrl().trim();
		String imageName = pictureUrl.substring(pictureUrl.lastIndexOf("/")+1);
		holder.tvImageName.setText(  imageName );
		
		
		if(mRowList.get(position).getImageUrl().trim().equals("")){
			holder.ivPicture.setVisibility(View.GONE);
		}else{
			holder.ivPicture.setVisibility(View.VISIBLE);

			
//			imageTask = new ImageParse();
//			imageTask.execute(holder);		
			
			
			AQuery androidAQuery = new AQuery(convertView);
		    androidAQuery.id(holder.ivPicture).image(pictureUrl, true, true, 0, 0, new BitmapAjaxCallback(){

		            @Override
		            public void callback(String url, ImageView iv, Bitmap bm, AjaxStatus status){
		                //iv.setImageBitmap(bm);
		                
		                iv.getLayoutParams().height = 250;
		                iv.getLayoutParams().width = 350;
		                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
		                iv.setImageBitmap(bm);
		            }
		        });	
		}
		
		return convertView;
	}

	@Override
	public int getCount()
	{
		return mRowList.size();
	}

	@Override
	public Object getItem(int position)
	{
		
		return mRowList.get(position);
	}
	
	public int getRowId(int position)
	{
		return mRowList.get(position).getRowId();
	}
	
	public String getImageUrl(int position)
	{
		return mRowList.get(position).getImageUrl();
	}	

	@Override
	public long getItemId(int position)
	{
		return position;
	}
	
	public void setSelected(int position) {
        selectedPosition = position;
    }	
	public int getSelected() {
        return selectedPosition;
    }
		
	private static class ViewHolder {
		
		private ImageView ivPicture;
		private TextView tvImageName;
	}	
	
	private class ImageParse extends AsyncTask<ViewHolder, String, ViewHolder> {
	    InputStream is = null;
	    Bitmap bm = null;
	    
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		
		}
	    @Override
	    protected ViewHolder doInBackground(ViewHolder... args) {
	    	 
	    	ViewHolder viewHolder = args[0];

	    	try{
		        is = new URL(pictureUrl).openStream();
		        bm = BitmapFactory.decodeStream(is);
		        
		        is.close();   
		    }catch (Exception e) {
		    	e.printStackTrace();
		    }	    	
	        return viewHolder;
	    }
	    @Override
	    protected void onPostExecute(ViewHolder viewHolder) {	
	    	
	    	if(bm!=null){	
	    		viewHolder.ivPicture.getLayoutParams().height = 250;
	    		viewHolder.ivPicture.getLayoutParams().width = 350;
	    		viewHolder.ivPicture.setScaleType(ImageView.ScaleType.CENTER_CROP);
	    		viewHolder.ivPicture.setImageBitmap(bm);
	    	}else{
	    		viewHolder.ivPicture.setImageResource(R.drawable.box_gallery);
	    	}
	    	  

	    }
	}		
}

