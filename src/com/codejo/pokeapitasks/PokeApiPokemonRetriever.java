package com.codejo.pokeapitasks;


import java.util.ArrayList;

import android.os.AsyncTask;

import com.codejo.adapter.PokedexParser;
import com.codejo.data.Pokemon;
import com.codejo.sections.PokeListFragment;

public class PokeApiPokemonRetriever extends AsyncTask<Pokemon,Void,Pokemon>{
	private PokeListFragment pokedexFragment;
	//private Context pokedexContext;
	
	public PokeApiPokemonRetriever(PokeListFragment dexFragment){
		pokedexFragment = dexFragment;
		//pokedexContext = dexFragment.getActivity().getApplicationContext();
	}
	
	@Override
	protected Pokemon doInBackground(Pokemon... pokemon) {
		String pokemonJson = PokeApiDownloader.downloadApiResource(pokemon[0].getUri());
		Pokemon monster = PokedexParser.parsePokemonFromApi(pokemonJson);
		String pokeSpriteJson = PokeApiDownloader.downloadApiResource(monster.getSprite_uri());
		String real_sprite =    PokedexParser.parseSpriteFromApi(pokeSpriteJson);
		
		monster.setRealSpriteUri(real_sprite);
		pokemon[0] = monster;
		return monster;
	}

	@Override
	protected void onPostExecute(Pokemon pokemon) {
		//TODO CHANGE ONLY TESTING!
		ArrayList<Pokemon> pokelist = pokedexFragment.getPokemonList();
		pokelist.remove(2);
		pokelist.add(2, pokemon);
		pokedexFragment.updatePokemonListView();
		super.onPostExecute(pokemon);
	}


}
