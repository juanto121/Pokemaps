package com.codejo.pokemaps;
import com.codejo.adapter.*;
import com.codejo.sections.PokeListFragment;
import com.codejo.sections.PokeMapFragment;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;



public class PokedexFragmentActivity extends FragmentActivity implements ActionBar.TabListener, PokeMapFragment.OnPokemonCaughtListener{


	private ViewPager viewpager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pokedex_fragment);
		

		viewpager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(this.getSupportFragmentManager());
		
		
		viewpager.setAdapter(mAdapter);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setHomeButtonEnabled(false);
		Resources res = getResources();
		
		String[] tabs = res.getStringArray(R.array.tab_names);
		

		for (String tab : tabs){
			actionBar.addTab(actionBar.newTab().setText(tab).setTabListener(this));
		}
		

		viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				
				actionBar.setSelectedNavigationItem(position);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
				
			}
		});
		
	}


	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewpager.setCurrentItem(tab.getPosition());
	}


	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
		
	}


	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	
		
	}


	@Override
	public void onPokemonCaught() {
		PokeListFragment  map = (PokeListFragment)mAdapter.getFragment(TabsPagerAdapter.LIST_FRAGMENT_INDEX);
		map.catchPokemon( (int)(Math.random()*map.getPokemonList().size()));
	}

	

}
