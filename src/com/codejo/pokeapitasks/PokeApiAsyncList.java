package com.codejo.pokeapitasks;


import java.util.ArrayList;


import android.os.AsyncTask;

import com.codejo.adapter.PokedexParser;
import com.codejo.data.Pokemon;
import com.codejo.sections.PokeListFragment;

public class PokeApiAsyncList extends AsyncTask<String,Void,String>{
	
	private PokeListFragment pokedexFragment;
	//private Context pokedexContext;
	
	public PokeApiAsyncList(PokeListFragment pokedex_fragment){
		super();
		this.pokedexFragment= pokedex_fragment;
		//this.pokedexContext = this.pokedexFragment.getActivity().getApplicationContext();
	}
	
	protected void onPreExecute(){
		super.onPreExecute();
		//progressDialog?
	}
	
	protected String doInBackground(String... input){
		String result = PokeApiDownloader.downloadApiResource(PokeApiDownloader.POKEDEX_URI);
		return result;
	}

	protected void onPostExecute(String result){
		ArrayList<Pokemon> pokemonData = PokedexParser.parsePokedexFromApi(result);
		pokedexFragment.setPokemonList(pokemonData);
	}
}