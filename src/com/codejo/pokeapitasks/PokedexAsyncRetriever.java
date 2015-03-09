package com.codejo.pokeapitasks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import com.codejo.adapter.PokedexParser;
import com.codejo.data.Pokemon;
import com.codejo.sections.PokeListFragment;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

//TODO Strategy pattern would be suitable for this behaviour Storage/Retrieval.

public class PokedexAsyncRetriever extends AsyncTask<String,Void,String> {

	private static final String TAG = "PokedexAsync";
	private PokeListFragment pokedexFragment;
	private Context pokeMapsContext;
	
	//TODO name of file should be on Configuration file. 
	private static String FILENAME = "pokedex.data";
	
	public PokedexAsyncRetriever(PokeListFragment pokedex_fragment){
		pokedexFragment = pokedex_fragment;
		pokeMapsContext = pokedexFragment.getActivity().getApplicationContext();
	}
	
	
	@Override
	protected String doInBackground(String... arg0) {
		File pokemap_directory = pokeMapsContext.getFilesDir();
		
		Log.d(TAG, "Directory for app : "+ pokemap_directory);
		
		FileInputStream fis = null;
		StringBuilder pokedex = null; 
		BufferedReader reader = null;
		try {
			Log.d(TAG,"Pokestorage access start");
			fis = pokeMapsContext.openFileInput(FILENAME);
			if(fis != null){

				reader = new BufferedReader(new InputStreamReader(fis));
				
				pokedex = new StringBuilder();
				
				pokedex.append(reader.readLine());
					
			}
			
		} catch (FileNotFoundException e) {
			// TODO Catch when file pokedex.data not found.
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Catch I/O exception when writing to pokedex.data file.
			e.printStackTrace();
		} finally{
			try {
				fis.close();
				reader.close();
			} catch (IOException e) {
				// TODO Catch I/O exception when closing pokedex.data file.
				e.printStackTrace();
			}
		}
		Log.d(TAG,"Pokestorage access end");
		return pokedex.toString();
	}
	
	@Override 
	protected void onPostExecute(String result){
		ArrayList<Pokemon> pokemonData = PokedexParser.parsePokedexFromStorage(result);
		pokedexFragment.setPokemons(pokemonData);
		super.onPostExecute(result);
	}
	
}
