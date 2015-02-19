package com.codejo.adapter;

import com.codejo.sections.*;

import android.support.v4.app.Fragment;
import  android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;



public class TabsPagerAdapter extends FragmentPagerAdapter{
	private final int TOTAL_TABS = 2;
	private final int MAP_FRAGMENT_INDEX= 0;
	private final int LIST_FRAGMENT_INDEX= 1;

	public TabsPagerAdapter(android.support.v4.app.FragmentManager fm){
		super(fm);
	}
	@Override
	public Fragment getItem(int index){
		Fragment swipe_to_fragment = null;
		switch(index){
			case LIST_FRAGMENT_INDEX:
				swipe_to_fragment = new PokeMapFragment(); break;
			case MAP_FRAGMENT_INDEX:
				swipe_to_fragment = new PokeListFragment(); break;
		}
		return swipe_to_fragment;
	}
	@Override
	public int getCount(){
		return TOTAL_TABS;
	}
}