package com.loaditemsonscroll_demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.AppCompatActivity;
import com.loaditemsonscroll_demo.R;

public class MainActivity extends AppCompatActivity implements TabListener {
	private static ViewPager viewPager;//View Pager for setting tabs
	private static ActionBar actionBar;
	private static FragmentManager fragmentManager;//fragment manager to work on fragments

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		setTabs();
	}

	
	//initialize the view pager 
	private void init() {
		viewPager = (ViewPager) findViewById(R.id.pager);
		fragmentManager = getSupportFragmentManager();

		// Setting adapter over view pager
		viewPager.setAdapter(new MyAdapter(fragmentManager));

		// Implementing view pager pagechangelistener to navigate between tabs
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int pos) {

				// Setting navigation of tabs to actionbar
				actionBar.setSelectedNavigationItem(pos);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

	}

	private void setTabs() {
		// Getting actionbar
		actionBar = getSupportActionBar();

		// Setting navigation mode to actionbar
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Now adding a new tab to action bar and setting title, icon and
		// implementing listener
		android.support.v7.app.ActionBar.Tab tab1 = actionBar.newTab();
		tab1.setText("RecyclerView");
		// tab1.setIcon(R.drawable.ic_launcher);
		tab1.setTabListener(this);

		android.support.v7.app.ActionBar.Tab tab2 = actionBar.newTab();
		tab2.setText("ListView");
		tab2.setTabListener(this);

		// Now finally adding all tabs to actionbar
		actionBar.addTab(tab1);
		actionBar.addTab(tab2);

	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		// Setting current position of tab to view pager
		viewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		

	}

	// My adapter i.e. custom adapter for displaying fragments over view pager
	private class MyAdapter extends FragmentPagerAdapter {
		public MyAdapter(FragmentManager fm) {
			super(fm);

		}

		@Override
		public Fragment getItem(int i) {

			// Getting fragments according to selected position
			Fragment fragment = null;
			if (i == 0) {
				fragment = new LinearLayout_Fragment();
			}
			if (i == 1) {
				fragment = new ListViewLayout_Fragment();
			}

			// and finally returning fragments
			return fragment;
		}

		@Override
		public int getCount() {

			// Returning no. of counts of fragments
			return 2;
		}
	}

}
