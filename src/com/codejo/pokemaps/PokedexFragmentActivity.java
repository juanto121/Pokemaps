package com.codejo.pokemaps;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class PokedexActivity extends FragmentActivity implements ActionBar.TabListener{


	private ViewPager viewpager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pokedex);

		viewpager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		resources = getResources();

		String[] tabs = res.getStringArray(R.array.tab_names);

		for (String tab : tabs){
			actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pokedex, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
