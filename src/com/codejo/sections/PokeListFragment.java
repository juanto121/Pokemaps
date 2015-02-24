package com.codejo.sections;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codejo.pokeapitasks.PokeApiAsyncList;
import com.codejo.pokeapitasks.PokedexAsyncStorage;
import com.codejo.adapter.PokedexListViewAdapter;
import com.codejo.adapter.PokedexParser;
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
	
	public PokeListFragment(){
		setRetainInstance(true);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstances ){
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		
		this.pokeListView = (ListView) rootView.findViewById(R.id.pokeList);
		
		//TODO: CHECK INTERNAL STORAGE for already downloaded pokedex.
		
		PokeApiAsyncList pokeapiTask = new PokeApiAsyncList(this);
		try{
			pokeapiTask.execute("");
			
		}catch(Exception e){
			Log.d(TAG, "Error during execute to pokeApiAsyncList");
		}
		
		return rootView;
	}
	
	
	
	@Override
	public void onPause() {
		
		String json_pokedex = PokedexParser.parsePokedexForStorage(this.pokemonList);
		
		PokedexAsyncStorage pokedexStorage = new PokedexAsyncStorage(this);
		try{
			pokedexStorage.execute(json_pokedex);
		}catch(Exception e){
			Log.d(TAG, "Error during pokedex storage task");
		}
		
		super.onPause();
	}

	public void setPokemons(ArrayList<Pokemon> pokedex){
		this.pokemonList = pokedex;
		Pokemon pokedexArray[] = new Pokemon[this.pokemonList.size()];
		
		ListAdapter pokedexAdapter = new PokedexListViewAdapter(this.getActivity().getApplicationContext(),
															 pokemonList.toArray(pokedexArray));
		this.pokeListView.setAdapter(pokedexAdapter);
	}
	
}
