package com.loaditemsonscroll_demo;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.loaditemsonscroll_demo.adapter.ListViewAdapter;

public class ListViewLayout_Fragment extends Fragment {
	private static View view;
	private static ListView listView;;
	private static ListViewAdapter adapter;
	private static ArrayList<Data_Model> listArrayList;

	// Variables for scroll listener
	int mVisibleThreshold = 5;
	int mCurrentPage = 0;
	int mPreviousTotal = 0;
	boolean mLoading = true;
	boolean mLastPage = false;
	boolean userScrolled = false;

	// Images int array from drawable folders
	private static final int[] images = { R.drawable.tajmahal,
			R.drawable.hawamahal, R.drawable.golden, R.drawable.shore,
			R.drawable.shivaji, R.drawable.lotus, R.drawable.victoria,
			R.drawable.brihadishwara, R.drawable.mahabodhi };

	// String array for title, location,year
	String[] getTitle, getLocation, getYear;

	private static RelativeLayout bottomLayout;

	public ListViewLayout_Fragment() {
		// Empty constructor is neccessary
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.listview_fragment, container, false);
		init();
		populatRecyclerView();
		implementScrollListener();
		return view;
	}

	// Initialize all variables and views
	private void init() {

		bottomLayout = (RelativeLayout) view
				.findViewById(R.id.loadItemsLayout_listView);
		// Get the title , location and year from string.xml in array form

		getTitle = getActivity().getResources().getStringArray(R.array.title);
		getLocation = getActivity().getResources().getStringArray(
				R.array.location);
		getYear = getActivity().getResources().getStringArray(
				R.array.constructed_year);

		// Find the id of list view
		listView = (ListView) view.findViewById(R.id.listView);

	}

	// Populate the listview with data
	private void populatRecyclerView() {
		listArrayList = new ArrayList<Data_Model>();
		for (int i = 0; i < getTitle.length; i++) {
			// add the items one by one in arraylist
			listArrayList.add(new Data_Model(getTitle[i], getLocation[i],
					getYear[i], images[i]));
		}
		adapter = new ListViewAdapter(getActivity(), listArrayList);

		// set adapter over recyclerview
		listView.setAdapter(adapter);
		adapter.notifyDataSetChanged();

	}

	// Implement scroll listener
	private void implementScrollListener() {
		listView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView arg0, int scrollState) {
				// If scroll state is touch scroll then set userScrolled
				// true
				if (scrollState == OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
					userScrolled = true;

				}

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// Now check if userScrolled is true and also check if
				// the item is end then update list view and set
				// userScrolled to false
				if (userScrolled
						&& firstVisibleItem + visibleItemCount == totalItemCount) {

					userScrolled = false;
					updateListView();
				}
			}
		});
	}

	// Method for repopulating recycler view
	private void updateListView() {
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
