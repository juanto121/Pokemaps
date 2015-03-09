package com.codejo.pokeapitasks;

import android.content.Context;
import android.os.AsyncTask;

import com.codejo.adapter.PokedexParser;
import com.codejo.data.Pokemon;
import com.codejo.sections.PokeListFragment;

public class PokeApiPokemonRetriever extends AsyncTask<Pokemon,Void,Pokemon>{
	private PokeListFragment pokedexFragment;
	private Context pokedexContext;
	
	public PokeApiPokemonRetriever(PokeListFragment dexFragment){
		pokedexFragment = dexFragment;
		pokedexContext = dexFragment.getActivity().getApplicationContext();
	}
	
	@Override
	protected Pokemon doInBackground(Pokemon... pokemon) {
		String result = PokeApiDownloader.downloadApiResource(pokemon[0].getUri());
		Pokemon monster = PokedexParser.parsePokemonFromApi(result);
		return monster;
	}

	@Override
	protected void onPostExecute(Pokemon pokemon) {
		
		/*
		 * 
		 * TODO parse pokemon full description
		 * 	obtain sprite_url
		 * 	obtain description_url
		 * 
		 * 
		 */
		
		super.onPostExecute(pokemon);
	}


}
