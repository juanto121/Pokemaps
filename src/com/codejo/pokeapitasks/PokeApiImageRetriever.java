package com.codejo.pokeapitasks;

import com.codejo.adapter.PokedexParser;
import com.codejo.sections.PokeListFragment;

import android.content.Context;
import android.os.AsyncTask;

public class PokeApiImageRetriever extends AsyncTask<String,Void,String>{
	
	private PokeListFragment pokedexFragment;
	private Context pokedexContext;
	
	public PokeApiImageRetriever(PokeListFragment dexFragment){
		pokedexFragment = dexFragment;
		pokedexContext = dexFragment.getActivity().getApplicationContext();
	}
	
	@Override
	protected String doInBackground(String... image_uri) {
		String result = PokeApiDownloader.downloadApiResource(image_uri[0]);
		return result;
	}

	@Override
	protected void onPostExecute(String image_resource) {
		String real_image_uri[] = PokedexParser.parsePokemonSpriteFromApi(image_resource);
		this.pokedexFragment.changePokemonImage(real_image_uri);
		super.onPostExecute(image_resource);
	}
	

}
