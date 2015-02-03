package com.codejo.adapter;

public class TabPagerAdapter extends FragmentPagerAdater{
	private final int TOTAL_TABS = 2;
	private final int MAP_FRAGMENT_NUM = 0;
	private final int LIST_FRAGMENT_NUM = 1;

	public TabPagerAdapter(FragmentManager fm){
		super(fm);
	}
	@Override
	public Fragment getItem(int index){
		switch(index){
			case MAP_FRAGMENT_NUM:
				return new PokeMapFragment();
			case LIST_FRAGMENT_NUM:
				return new PokeListFragment();
		}
		return null;
	}
	Override
	public int getCount(){
		return TOTAL_TABS;
	}
}