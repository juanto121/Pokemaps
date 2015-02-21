package com.codejo.pokeapitasks;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;

import com.codejo.adapter.PokeapiAdapter;
import com.codejo.data.Pokemon;
import com.codejo.sections.PokeListFragment;

public class PokeApiAsyncList extends AsyncTask<String,Void,String>{
	
	private PokeListFragment pokedexFragment;
	private Context pokedexContext;
	
	public PokeApiAsyncList(PokeListFragment pokedex_fragment){
		super();
		this.pokedexFragment= pokedex_fragment;
		this.pokedexContext = this.pokedexFragment.getActivity().getApplicationContext();
	}
	
	protected void onPreExecute(){
		super.onPreExecute();
		//progressDialog?
	}
	
	protected String doInBackground(String... input){
		String result = PokeApiDownloader.downloadPokedex("");
		return result;
	}

	protected void onPostExecute(String result){
		ArrayList<Pokemon> pokemonData = PokeapiAdapter.parsePokedexFromApi(result);
		this.pokedexFragment.setPokemons(pokemonData);
		//save pokedex to internal storage.
	}
}