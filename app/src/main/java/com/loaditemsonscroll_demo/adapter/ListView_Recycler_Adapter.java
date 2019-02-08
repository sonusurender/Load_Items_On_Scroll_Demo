package com.loaditemsonscroll_demo.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.loaditemsonscroll_demo.Data_Model;
import com.loaditemsonscroll_demo.holders.ListView_Holder;
import com.loaditemsonscroll_demo.holders.RecyclerView_OnClickListener.OnClickListener;
import com.loaditemsonscroll_demo.R;

public class ListView_Recycler_Adapter extends
		RecyclerView.Adapter<ListView_Holder> {// Recyclerview will extend to
												// recyclerview adapter
	private ArrayList<Data_Model> arrayList;
	private Context context;

	public ListView_Recycler_Adapter(Context context,
			ArrayList<Data_Model> arrayList) {
		this.context = context;
		this.arrayList = arrayList;

	}

	@Override
	public int getItemCount() {
		return (null != arrayList ? arrayList.size() : 0);

	}

	@Override
	public void onBindViewHolder(ListView_Holder holder, int position) {
		final Data_Model model = arrayList.get(position);

		ListView_Holder mainHolder = (ListView_Holder) holder;// holder

		Bitmap image = BitmapFactory.decodeResource(context.getResources(),
				model.getImage());// This will convert drawbale image into
									// bitmap

		// setting data over views
		mainHolder.list_title.setText(model.getTitle());
		mainHolder.list_location.setText(model.getLocation());
		mainHolder.list_date.setText(model.getYear());
		mainHolder.list_imageView.setImageBitmap(image);

		// Implement click listener over layout
		mainHolder.setClickListener(new OnClickListener() {

			@Override
			public void OnItemClick(View view, int position) {
				switch (view.getId()) {
				case R.id.list_layout:

					// Show a toast on clicking layout
					Toast.makeText(context,
							"You have clicked " + model.getTitle(),
							Toast.LENGTH_LONG).show();
					break;

				}
			}

		});

	}

	@Override
	public ListView_Holder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

		// This method will inflate the custom layout and return as viewholder
		LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

		ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
				R.layout.list_customview, viewGroup, false);
		ListView_Holder listHolder = new ListView_Holder(mainGroup);
		return listHolder;

	}

}
