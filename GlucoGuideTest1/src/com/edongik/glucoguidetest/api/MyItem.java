package com.edongik.glucoguidetest.api;

import android.widget.ImageView;
import android.widget.TextView;


public class MyItem {
/*	public int rowId;
	public String question;
	public int bookmark;*/

	private int rowId;
	private String imageUrl;
	
	public MyItem(int rowId1, String imageUrl
				) {
		  this.rowId         = rowId1;
		  this.imageUrl      = imageUrl;
	}
		 

	public int getRowId() {
		return rowId;
	}


	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
