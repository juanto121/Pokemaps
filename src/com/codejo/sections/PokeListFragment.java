package com.codejo.sections;

import java.util.ArrayList;

import com.codejo.pokeapitasks.PokeApiAsyncList;
import com.codejo.data.Pokemon;
import com.codejo.pokemaps.R;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class PokeListFragment extends Fragment{
	
	private ListView pokeListView;
	private LayoutInflater layoutInflater;
	private ArrayList<Pokemon> pokemonList;
	private static String TAG = "PokeListFragment";
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstances ){
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		
		this.pokeListView = (ListView) rootView.findViewById(R.id.pokeList);
		
		PokeApiAsyncList pokeapiTask = new PokeApiAsyncList(this);
		try{
			pokeapiTask.execute("");
		}catch(Exception e){
			Log.d(TAG, "Error during execute to pokeApiAsyncList");
		}
		
		final Object[] data = (Object[])getActivity().getLastCustomNonConfigurationInstance();
		if(data != null){
			this.pokemonList = (ArrayList<Pokemon>) data[0];
			String[] pokemonArray = new String[pokemonList.size()];
		}
		return rootView;
	}
	
	public void setPokemons(ArrayList<Pokemon> pokedex){
		this.pokemonList = pokedex;
		Pokemon pokedexArray[] = new Pokemon[this.pokemonList.size()];
		
		ListAdapter pokedexAdapter = new ArrayAdapter<Pokemon>(this.getActivity().getApplicationContext(),
															 R.layout.list_row_layout,pokemonList.toArray(pokedexArray));
		this.pokeListView.setAdapter(pokedexAdapter);
	}
	
}
