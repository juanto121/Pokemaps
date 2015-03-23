package com.codejo.adapter;

import com.codejo.sections.*;

import android.support.v4.app.Fragment;
import  android.support.v4.app.FragmentPagerAdapter;




public class TabsPagerAdapter extends FragmentPagerAdapter{
	private final int TOTAL_TABS = 2;
	public static final int MAP_FRAGMENT_INDEX= 1;
	public static final int LIST_FRAGMENT_INDEX= 0;
	
	/*
	 * Potential null pointer as reference to each fragment could have changed due to the ViewPager recreation.
	 */
	private Fragment map;
	private Fragment pokedex;
	
	public TabsPagerAdapter(android.support.v4.app.FragmentManager fm){
		super(fm);
	}
	
	
	@Override
	public Fragment getItem(int index){
		Fragment swipe_to_fragment = null;
		switch(index){
			case MAP_FRAGMENT_INDEX:
				swipe_to_fragment = new PokeMapFragment();
				map = swipe_to_fragment;
				break;
			case LIST_FRAGMENT_INDEX:
				swipe_to_fragment = new PokeListFragment();
				pokedex = swipe_to_fragment;
				break;
		}
		return swipe_to_fragment;
	}
	
	public Fragment getFragment(int index){
		Fragment fragment = null;
		switch(index){
		case LIST_FRAGMENT_INDEX:
			fragment = pokedex;
			break;
		case MAP_FRAGMENT_INDEX:
			fragment = map;
			break;
		}
		return fragment;
	}
	
	@Override
	public int getCount(){
		return TOTAL_TABS;
	}
}