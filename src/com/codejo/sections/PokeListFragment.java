package com.codejo.sections;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codejo.pokeapitasks.PokeApiAsyncList;
import com.codejo.pokeapitasks.PokeApiImageRetriever;
import com.codejo.pokeapitasks.PokedexAsyncRetriever;
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
	private static String FILENAME = "pokedex.data";
	
	public PokeListFragment(){
		setRetainInstance(true);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstances ){
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		
		this.pokeListView = (ListView) rootView.findViewById(R.id.pokeList);
		
		
		if( checkPokedexInStorage() ){
			PokedexAsyncRetriever pokedexRetriever = new PokedexAsyncRetriever(this);
			PokeApiImageRetriever imageRetriever = new PokeApiImageRetriever(this);
			try{
				pokedexRetriever.execute("");
				imageRetriever.execute("api/v1/sprite/4/");
			}catch(Exception e ){
				Log.d(TAG, "Error during execute to pokeApiAsyncRetriever");
				
			}
		}else{
			PokeApiAsyncList pokeapiTask = new PokeApiAsyncList(this);
			try{
				pokeapiTask.execute("");
				
			}catch(Exception e){
				Log.d(TAG, "Error during execute to pokeApiAsyncList");
			}
		}
		
		return rootView;
	}
	

	private boolean checkPokedexInStorage() {
		File file = this.getActivity().getApplicationContext().getFileStreamPath(FILENAME);
		return file.exists();
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
	
	public void changePokemonImage(String[] real_image_uri){
		
		//TODO real_img_uri[0] name is venusaur_auto
		
		int pokeListLenght = pokemonList.size();
		for( int index = 0; index < pokeListLenght; index ++ ){
			Pokemon poke = pokemonList.get(index);
			if( poke.getName().equals(real_image_uri[0])){
				poke.setRealSpriteUri(real_image_uri[1]);
				break;
			}
		
		}
		
		
	}
	
}
