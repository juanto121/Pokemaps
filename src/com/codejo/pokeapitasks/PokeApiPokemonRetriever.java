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
		if(pokemonJson==null || pokemonJson.isEmpty()){
			return pokemon[0];
		}
		Pokemon monster = PokedexParser.parsePokemonFromApi(pokemonJson,pokemon[0]);
		String pokeSpriteJson = PokeApiDownloader.downloadApiResource(monster.getSprite_uri());
		String real_sprite =    PokedexParser.parseSpriteFromApi(pokeSpriteJson);
		monster.setRealSpriteUri(real_sprite);

		return monster;
	}

	@Override
	protected void onPostExecute(Pokemon pokemon) {
		pokedexFragment.updatePokemonListView();
		super.onPostExecute(pokemon);
	}


}
