package com.loaditemsonscroll_demo;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;

import com.loaditemsonscroll_demo.adapter.ListView_Recycler_Adapter;

public class LinearLayout_Fragment extends Fragment {
	private static View view;
	private static RecyclerView listRecyclerView;
	private static ArrayList<Data_Model> listArrayList;
	private static ListView_Recycler_Adapter adapter;

	// Images array for images
	private static final int[] images = { R.drawable.tajmahal,
			R.drawable.hawamahal, R.drawable.golden, R.drawable.shore,
			R.drawable.shivaji, R.drawable.lotus, R.drawable.victoria,
			R.drawable.brihadishwara, R.drawable.mahabodhi };

	// String array for title, location, year
	String[] getTitle, getLocation, getYear;
	private static RelativeLayout bottomLayout;
	private static LinearLayoutManager mLayoutManager;

	// Variables for scroll listener
	private boolean userScrolled = true;
	int pastVisiblesItems, visibleItemCount, totalItemCount;

	public LinearLayout_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.linearlayout_fragment, container,
				false);
		init();
		populatRecyclerView();
		implementScrollListener();
		return view;
	}

	// Initialize the view
	private void init() {

		bottomLayout = (RelativeLayout) view
				.findViewById(R.id.loadItemsLayout_recyclerView);
		// Getting the string array from strings.xml

		getTitle = getActivity().getResources().getStringArray(R.array.title);
		getLocation = getActivity().getResources().getStringArray(
				R.array.location);
		getYear = getActivity().getResources().getStringArray(
				R.array.constructed_year);

		mLayoutManager = new LinearLayoutManager(getActivity());
		listRecyclerView = (RecyclerView) view
				.findViewById(R.id.linear_recyclerview);
		listRecyclerView.setHasFixedSize(true);
		listRecyclerView.setLayoutManager(mLayoutManager);// for
															// linear
															// data
															// display
															// we
															// use
															// linear
															// layoutmanager

	}

	// populate the list view by adding data to arraylist
	private void populatRecyclerView() {
		listArrayList = new ArrayList<Data_Model>();
		for (int i = 0; i < getTitle.length; i++) {
			listArrayList.add(new Data_Model(getTitle[i], getLocation[i],
					getYear[i], images[i]));
		}
		adapter = new ListView_Recycler_Adapter(getActivity(), listArrayList);
		listRecyclerView.setAdapter(adapter);// set adapter on recyclerview
		adapter.notifyDataSetChanged();// Notify the adapter

	}

	// Implement scroll listener
	private void implementScrollListener() {
		listRecyclerView
				.addOnScrollListener(new RecyclerView.OnScrollListener() {

					@Override
					public void onScrollStateChanged(RecyclerView recyclerView,
							int newState) {

						super.onScrollStateChanged(recyclerView, newState);

						// If scroll state is touch scroll then set userScrolled
						// true
						if (newState == OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
							userScrolled = true;

						}

					}

					@Override
					public void onScrolled(RecyclerView recyclerView, int dx,
							int dy) {

						super.onScrolled(recyclerView, dx, dy);
						// Here get the child count, item count and visibleitems
						// from layout manager

						visibleItemCount = mLayoutManager.getChildCount();
						totalItemCount = mLayoutManager.getItemCount();
						pastVisiblesItems = mLayoutManager
								.findFirstVisibleItemPosition();

						// Now check if userScrolled is true and also check if
						// the item is end then update recycler view and set
						// userScrolled to false
						if (userScrolled
								&& (visibleItemCount + pastVisiblesItems) == totalItemCount) {
							userScrolled = false;

							updateRecyclerView();
						}

					}

				});

	}

	// Method for repopulating recycler view
	private void updateRecyclerView() {

		// Show Progress Layout
		bottomLayout.setVisibility(View.VISIBLE);

		// Handler to show refresh for a period of time you can use async task
		// while commnunicating serve

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				// Loop for 3 items
				for (int i = 0; i < 3; i++) {
					int value = new RandomNumberGenerator().RandomGenerator();// Random
																				// value

					// add random data to arraylist
					listArrayList.add(new Data_Model(getTitle[value],
							getLocation[value], getYear[value], images[value]));
				}
				adapter.notifyDataSetChanged();// notify adapter

				// Toast for task completion
				Toast.makeText(getActivity(), "Items Updated.",
						Toast.LENGTH_SHORT).show();

				// After adding new data hide the view.
				bottomLayout.setVisibility(View.GONE);

			}
		}, 5000);
	}

}
