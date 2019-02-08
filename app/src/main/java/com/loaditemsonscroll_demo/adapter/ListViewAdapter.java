package com.loaditemsonscroll_demo.adapter;

import java.util.ArrayList;

import com.loaditemsonscroll_demo.Data_Model;
import com.loaditemsonscroll_demo.R;
import com.loaditemsonscroll_demo.holders.ListView_Holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
	private ArrayList<Data_Model> arrayList;
	private Context context;

	public ListViewAdapter(Context context, ArrayList<Data_Model> arrayList) {
		this.context = context;
		this.arrayList = arrayList;
	}

	@Override
	public int getCount() {

		return arrayList.size();
	}

	@Override
	public Object getItem(int position) {

		return arrayList.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder = null;

		// Inflater for custom layout
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (view == null) {
			view = inflater.inflate(R.layout.list_customview, parent, false);

			holder = new ViewHolder();

			holder.list_title = (TextView) view.findViewById(R.id.list_title);
			holder.list_location = (TextView) view
					.findViewById(R.id.list_location);
			holder.list_date = (TextView) view
					.findViewById(R.id.list_dateconstructed);
			holder.list_imageView = (ImageView) view
					.findViewById(R.id.list_imageview);

			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}

		final Data_Model model = arrayList.get(position);

		Bitmap image = BitmapFactory.decodeResource(context.getResources(),
				model.getImage());// This will convert drawbale image into
									// bitmap

		// setting data over views
		holder.list_title.setText(model.getTitle());
		holder.list_location.setText(model.getLocation());
		holder.list_date.setText(model.getYear());
		holder.list_imageView.setImageBitmap(image);

		return view;
	}

	// View Holder
	private class ViewHolder {
		public TextView list_title, list_location, list_date;
		public ImageView list_imageView;
	}
}
