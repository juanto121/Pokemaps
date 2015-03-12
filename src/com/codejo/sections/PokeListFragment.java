package com.codejo.sections;

import java.io.File;
import java.util.ArrayList;






import com.codejo.pokeapitasks.PokeApiAsyncList;
import com.codejo.pokeapitasks.PokeApiPokemonRetriever;
import com.codejo.pokeapitasks.PokedexStorageRetriever;
import com.codejo.pokeapitasks.PokedexStorageSave;
import com.codejo.pokeapitasks.PokemonImageTask;
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
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PokeListFragment extends Fragment{
	
	private ListView pokeListView;
	private ArrayList<Pokemon> pokemonList;
	private PokedexStorageRetriever pokedexRetriever;
	private PokeApiPokemonRetriever pokemonRetriever;
	private PokemonImageTask pokemonImageTask;
	private ListAdapter pokedexAdapter;
	private LayoutInflater inflater;
	private static String TAG = "PokeListFragment";
	private static String FILENAME = "pokedex.data";
	
	public PokeListFragment(){}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstances ){
		
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		this.inflater = inflater;
			
		
			this.pokeListView = (ListView) rootView.findViewById(R.id.pokeList);
			pokedexRetriever = new PokedexStorageRetriever(this);
			pokemonRetriever = new PokeApiPokemonRetriever(this);
			if (pokemonImageTask == null)
				pokemonImageTask = new PokemonImageTask(this.getActivity().getApplicationContext());
			
		if( checkPokedexInStorage() ){
			try{
				pokedexRetriever.execute("");
			}catch(Exception e ){
				Log.d(TAG, "Error during execute to pokeApiAsyncRetriever");
			}
		}else{
			PokeApiAsyncList pokeapiTask = new PokeApiAsyncList(this);
			try{
				pokeapiTask.execute("");
			}catch(Exception e){
				Log.d(TAG, "Error when retrieving pokedex from API");
			}
		}
			
		this.setRetainInstance(true);
		
		return rootView;
	}
	
	public void catchPokemon(int pokemon_index){
		pokemonList.get(pokemon_index).setCaught(true);
	}
	
	public void retrieveCaughtPokemon() {
		catchPokemon(2);
		if(pokemonList != null){
			int pokemon_list_length = pokemonList.size();
				for(int index = 0; index < pokemon_list_length; index++ ){
					Pokemon pokemon = pokemonList.get(index);
					if(pokemon.isCaught() && pokemon.getRealImage().equals("")){
						new PokeApiPokemonRetriever(this).execute(pokemon);			
					}
				}	
		}
	}

	private boolean checkPokedexInStorage() {
		File file = this.getActivity().getApplicationContext().getFileStreamPath(FILENAME);
		return file.exists();
	}

	@Override
	public void onPause() {
		
		String json_pokedex = PokedexParser.parsePokedexForStorage(this.pokemonList);
		
		PokedexStorageSave pokedexStorage = new PokedexStorageSave(this);
		try{
			pokedexStorage.execute(json_pokedex);
		}catch(Exception e){
			Log.d(TAG, "Error during pokedex storage task");
		}
		
		super.onPause();
	}
	

	public void updatePokemonListView(ArrayList<Pokemon> pokedex){
		this.pokemonList = pokedex;
		Pokemon pokedexArray[] = new Pokemon[this.pokemonList.size()];
		
		pokedexAdapter = new PokedexListViewAdapter(this.getActivity().getApplicationContext(),
															 pokemonList.toArray(pokedexArray), pokemonImageTask);
		this.pokeListView.setAdapter(pokedexAdapter);
	
		
	}
	
	
	public void updatePokemonListView(){
		this.updatePokemonListView(this.pokemonList);
	}
		
	
	public ArrayList<Pokemon> getPokemonList() {
		return pokemonList;
	}
	
	public void setPokemonList(ArrayList<Pokemon> pokedexArray){
		this.pokemonList = pokedexArray;
		retrieveCaughtPokemon();
		updatePokemonListView();
	}
	


	
}
