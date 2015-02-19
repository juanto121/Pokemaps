package com.codejo.sections;

import com.codejo.pokemaps.R;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PokeListFragment extends Fragment{
	public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstances ){
		
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		return rootView;
	}
}
