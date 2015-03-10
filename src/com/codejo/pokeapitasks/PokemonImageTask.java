package com.codejo.pokeapitasks;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import com.codejo.adapter.PokedexParser;
import com.codejo.sections.PokeListFragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class PokemonImageTask{
	

	private static final String TAG = "ImageTask";
	private static Drawable DEFAULT_ICON = null;
	
	
	private HashMap<String, Drawable> spriteCache;
	private BaseAdapter adapter;
	
	
	public PokemonImageTask(Context context){
		spriteCache = new HashMap<String, Drawable>();
	}
	
	public Drawable loadSprite(BaseAdapter adapter, ImageView view){
		this.adapter = adapter;
		String url = (String) view.getTag();
		if(spriteCache.containsKey(url)){
			return spriteCache.get(url);
		}else{
			new PokeApiImageRetriever().execute(url);
			return DEFAULT_ICON;
		}
		
	}
	
	private class PokeApiImageRetriever extends AsyncTask<String,Void,Drawable>{
		
		private String sprite_uri;
		
		public PokeApiImageRetriever(){
			
		}
		
		@Override
		protected Drawable doInBackground(String... image_uri) {
			Log.d(TAG, "Start image download");
			sprite_uri = image_uri[0];
			InputStream input_stream;
			try {
				URL url = new URL(sprite_uri);
				input_stream = url.openStream();
			} catch (MalformedURLException e) {
				Log.e(TAG, "Malformed URL: " + e.getMessage());
				throw new RuntimeException(e);
			} catch (IOException e) {
				Log.e(TAG, "IO exception: "+ e.getMessage());
				throw new RuntimeException(e);
			}
			return Drawable.createFromStream(input_stream, "src");
		}
	
		@Override
		protected void onPostExecute(Drawable image_resource) {
			super.onPostExecute(image_resource);
			synchronized (this){
				spriteCache.put(sprite_uri, image_resource);
			}
			adapter.notifyDataSetChanged();
			
		}
		
	
	}

}
