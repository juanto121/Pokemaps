package com.codejo.pokeapitasks;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

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
		ArrayList<Pokemon> pokemonData = new ArrayList<Pokemon>();
		try{
			JSONObject responseObject = new JSONObject(result);
			JSONArray pokemons = responseObject.getJSONArray("pokemon");
			int pokemons_length = pokemons.length(), index = pokemons_length;
			
			while( index-- >= 0 ){
				JSONObject jsonPokemon = pokemons.getJSONObject(index);
				String name = jsonPokemon.getString("name");
				String uri  = jsonPokemon.getString("resource_uri");
				Pokemon pokemon_to_add = new Pokemon(name, uri);
				pokemonData.add(pokemon_to_add);
			}
			
		}catch(Exception e){
			//TODO add exception for handling malformed JSON
		}
		
		this.pokedexFragment.setPokemons(pokemonData);
		
	}
}