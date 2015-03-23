package com.codejo.pokeapitasks;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import com.codejo.sections.PokeMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class ParkFinder{
	
	private static final String TAG = "PokeMapFragment";
	private static String PLACES_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
	private static int  RADIUS = 500;
	private PokeMapFragment map_fragment;
	private double latitude;
	private double longitude;
	private String keyword;
	private LatLng park;
	

	private static final int HTTP_OK = 200;
	private static byte[] buffer = new byte[1024];
	
	public ParkFinder(PokeMapFragment mapfrag){
		map_fragment = mapfrag;
	}
	
	public LatLng findNearestPark(){
		if(park!=null){
			return park;
		}else{

			new PlacesDownloader().execute("");	
			
		}		
		return null;
	}
	
	
	
	private class PlacesDownloader extends AsyncTask<String, Void, LatLng>{

		@Override
		protected LatLng doInBackground(String... arg0) {
			String json_park = downloadNearestPark();
			LatLng near_location = parsePlacesResponse(json_park);
			return near_location;
		}
		
		@Override
		protected void onPostExecute(LatLng place) {
			super.onPostExecute(place);
			park = place;
			map_fragment.placeNearPokemon();
		}
		
		public LatLng parsePlacesResponse(String json){
			LatLng place = null;
			try {
				JSONObject json_place = new JSONObject(json);
				JSONArray results = json_place.getJSONArray("results");
				if(results.length()!=0){
					JSONObject top_result = results.getJSONObject(0);
					JSONObject geometry = top_result.getJSONObject("geometry");
					JSONObject location = geometry.getJSONObject("location");
					double latitude = location.getDouble("lat");
					double longitude = location.getDouble("lng");
					place = new LatLng(latitude, longitude);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			}
			return place;
		}
		
		public synchronized String downloadNearestPark(){
			String url = String.format("%s?location=%f,%f&sensor=true&key=%s&keyword=%s&rankby=distance",PLACES_URL,latitude,longitude,PokeMapFragment.PLACES_API_KEY,keyword);
			
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(url);
			String resource = null;
			
			try{
				Log.d(TAG,"Pokeapi access start "+ url);
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

			Log.d(TAG,"Pokeapi access Ended " + url);
			return resource;
		}
		
	}
	
	

	public void setCurrentLatitude(double latitude) {
		this.latitude = latitude;
	}
	

	public void setCurrentLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
	public void setParkKeyword(String keyword){
		this.keyword = keyword;
	}
	
	public LatLng getPark(){
		return park;
	}
	
}
