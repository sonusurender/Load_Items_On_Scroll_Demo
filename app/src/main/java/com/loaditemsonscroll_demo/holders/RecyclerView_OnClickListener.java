package com.loaditemsonscroll_demo.holders;

import android.view.View;

public class RecyclerView_OnClickListener {
	/** Interface for Item Click over Recycler View Items **/
	public interface OnClickListener {
		public void OnItemClick(View view, int position);
	}
}
