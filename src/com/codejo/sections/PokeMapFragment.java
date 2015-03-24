package com.codejo.sections;

import com.codejo.pokeapitasks.ParkFinder;
import com.codejo.pokemaps.R;



import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

public class PokeMapFragment extends Fragment implements OnMapReadyCallback{
	
	private static final String TAG = "PokeMapFragment";
	private MapView mapView;
	private GoogleMap map;
	private LocationManager locator;
	private String locationProvider = LocationManager.NETWORK_PROVIDER;
	private ImageButton capture_button;
	private ParkFinder park_finder;
	private Location my_current_location;
	public static String PLACES_API_KEY;
	
	private PokeListFragment pokedex;

	private OnPokemonCaughtListener catcher;
	
	
	
	public PokeMapFragment(){
		
	}
	
	public interface OnPokemonCaughtListener{
		public void onPokemonCaught();
	}
	
	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try{
			catcher = (OnPokemonCaughtListener)activity;
		}catch(ClassCastException e){
			Log.e(TAG, "MUST IMPLEMENT OnPokemonCaughtListener");
		}
	}




	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}




	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{	
		PLACES_API_KEY = getString(R.string.API_PLACES);
		View rootView = inflater.inflate(R.layout.fragment_map, container,false);
		locator = (LocationManager)this.getActivity().getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
		mapView = (MapView) rootView.findViewById(R.id.mapview);
		
		capture_button = (ImageButton) rootView.findViewById(R.id.capture_button);

		if(park_finder == null)
			park_finder = new ParkFinder(this);
		
		capture_button.setOnClickListener(new  OnClickListener(){
			@Override
			public void onClick(View v) {
				LatLng park_latlng = park_finder.getPark();
				Location park_location = new Location(locationProvider);
				if(park_latlng != null && my_current_location!=null){
					park_location.setLatitude(park_latlng.latitude);
					park_location.setLongitude(park_latlng.longitude);
					float distance;
					if( (distance = my_current_location.distanceTo(park_location))<200){
							Toast.makeText(getActivity().getApplicationContext(), "Has Capturado un pokemon", Toast.LENGTH_SHORT).show();
							catcher.onPokemonCaught();
					}else{
						Toast.makeText(getActivity().getApplicationContext(), "Estas muy lejos del pokemon", Toast.LENGTH_SHORT).show();
					}
					Log.d(TAG,"From "+my_current_location+" to: "+ park_location + ". Distance to pokemon: " + distance);
				}
			}
			
		});
		
		mapView.onCreate(savedInstanceState);
		mapView.getMapAsync(this);
		
		//setRetainInstance(true);
		
		return rootView;
	}
	
	
	

	@Override
	public void onResume() {
	    mapView.onResume();

	    super.onResume();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    mapView.onDestroy();
	 }

	 @Override
	 public void onLowMemory() {
	    super.onLowMemory();
	   mapView.onLowMemory();
	  }




	@Override
	public void onMapReady(GoogleMap ready_map) {
		map = ready_map;
		map.getUiSettings().setMyLocationButtonEnabled(true);
		map.setMyLocationEnabled(true);
		
		MapsInitializer.initialize(this.getActivity());
		
		Location currentLocation = locator.getLastKnownLocation(locationProvider);
		my_current_location = currentLocation;
		
		
		
	    // Updates the location and zoom of the MapView
		if(currentLocation != null){
		    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()), 16);
		    map.animateCamera(cameraUpdate);
		    park_finder.setParkKeyword(getString(R.string.park_keyword));
			park_finder.setCurrentLatitude(currentLocation.getLatitude());
			park_finder.setCurrentLongitude(currentLocation.getLongitude());
			park_finder.findNearestPark();
		}
		
		
	}
	
	public void placeNearPokemon(){
		LatLng near_park = park_finder.getPark();
		MarkerOptions options = new MarkerOptions().position(near_park).title("Un pokemon Salvaje");
		map.addMarker(options);
	}
	
}

