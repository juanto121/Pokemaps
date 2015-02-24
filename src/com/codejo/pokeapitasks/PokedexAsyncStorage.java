package com.codejo.pokeapitasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.codejo.sections.PokeListFragment;




import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class PokedexAsyncStorage extends AsyncTask<String,Void,String>{

	private static final String TAG = "PokeAsyncStorage";
	private static String FILENAME = "pokedex.data";
	private PokeListFragment pokedexFragment;
	private Context pokeMapsContext;
	
	public PokedexAsyncStorage(PokeListFragment pokedex_fragment){
		pokedexFragment	= pokedex_fragment;
		pokeMapsContext = pokedexFragment.getActivity().getApplicationContext();
	}
	
	@Override
	protected String doInBackground(String... arg0) {
		File pokemap_directory = pokeMapsContext.getFilesDir();
		
		Log.d(TAG,"Directory for the app:" + pokemap_directory);
		
		FileOutputStream fos = null;
		try {
			 fos = pokeMapsContext.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fos.write(arg0[0].getBytes());
		} catch (FileNotFoundException e) {
			// TODO Catch when file pokedex.data not found.
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Catch I/O exception when writing to pokedex.data file.
			e.printStackTrace();
		} finally{
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Catch I/O exception when closing pokedex.data file.
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}
	
	

}
