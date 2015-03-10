package com.codejo.pokeapitasks;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;


public class PokeApiDownloader{
	
	private static final String TAG = "PokeApiDownloader";
	public static final String POKEAPI_URI = "http://pokeapi.co/";
	public static final String POKEDEX_URI = "api/v1/pokedex/1/";
	
	private static final int HTTP_OK = 200;
	private static byte[] buffer = new byte[1024];
	
	/*
	 * Synchronized method for getting JSON resource from pokeapi.co/api/v1/
	 * 
	 * @param specific path for a resource: eg. pokedex/1/ or sprite/1/
	 * @return JSON formated String of the resource.
	 */
	
	public static synchronized String downloadApiResource(String... specific_resource){
	
		String resource = null;
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(POKEAPI_URI + specific_resource[0]);

		try{
			Log.d(TAG,"Pokeapi access start");
			HttpResponse response = client.execute(request);
			StatusLine status = response.getStatusLine();
			if(status.getStatusCode() != HTTP_OK){
				//Throw exception for invalid response HTTP_NOT_OK
				Log.d(TAG,"Http response error");
			}
			HttpEntity entity = response.getEntity();
			InputStream inputStream = entity.getContent();
			ByteArrayOutputStream content = new ByteArrayOutputStream();

			int readCount = 0;
			while((readCount=inputStream.read(buffer)) != -1){
				content.write(buffer,0,readCount);
			}
			resource = new String(content.toByteArray());

		}catch(Exception e){
			//Throw connection exception.
			Log.d(TAG,"Error during client.execute() -- ");
		}

		Log.d(TAG,"Pokeapi access Ended");
		return resource;
	}
}