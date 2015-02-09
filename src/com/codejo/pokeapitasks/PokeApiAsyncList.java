package com.codejo.pokeapitasks;


import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;

public class PokeApiAsyncList extends AsyncTask<String,Void,String>{
	
	private FragmentActivity PokedexFragmentActivity;
	
	protected void onPreExecute(){
		super.onPreExecute();
		//progressDialog?
	}
	
	protected String doInBackground(String... input){
		String result = PokeApiDownloader.downloadPokedex("");
		return result;
	}

	protected void onPostExecute(String result){
		
	}
}