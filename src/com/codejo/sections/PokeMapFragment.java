package com.codejo.sections;

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

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PokeMapFragment extends Fragment implements OnMapReadyCallback{
	
	private static final String TAG = "PokeMapFragment";
	private MapView mapView;
	private GoogleMap map;
	private LocationManager locator;
	private String locationProvider = LocationManager.NETWORK_PROVIDER;
	
	public PokeMapFragment(){
		//setRetainInstance(true);
	}
	
	
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}




	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{	
		View rootView = inflater.inflate(R.layout.fragment_map, container,false);
		locator = (LocationManager)this.getActivity().getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
		mapView = (MapView) rootView.findViewById(R.id.mapview);
		mapView.onCreate(savedInstanceState);
		mapView.getMapAsync(this);
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
	    // Updates the location and zoom of the MapView
	    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()), 16);
	    map.animateCamera(cameraUpdate);
	
	}
	
}

