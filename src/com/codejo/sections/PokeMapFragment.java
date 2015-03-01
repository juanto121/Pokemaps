package com.codejo.sections;

import com.codejo.pokemaps.R;



import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PokeMapFragment extends Fragment{
	
	private static final String TAG = "PokeMapFragment";
	private MapView mapView;
	private GoogleMap map;
	
	public PokeMapFragment(){
		//setRetainInstance(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{	
	
		
		View rootView = inflater.inflate(R.layout.fragment_map, container,false);
		/*mapView = (MapView) rootView.findViewById(R.id.mapview);
		mapView.onCreate(savedInstanceState);
		
		map = mapView.getMap();
		map.getUiSettings().setMyLocationButtonEnabled(true);
		map.setMyLocationEnabled(true);
		
		MapsInitializer.initialize(this.getActivity());
		    
		
	    // Updates the location and zoom of the MapView
	    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(0,0), 100);
	    map.animateCamera(cameraUpdate);
	*/
		return rootView;
	}
	
	
	

	@Override
	public void onResume() {
	  //  mapView.onResume();
	   // Log.d(TAG, "My location: " + map.getMyLocation());
	    super.onResume();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	  //  mapView.onDestroy();
	 }

	 @Override
	 public void onLowMemory() {
	    super.onLowMemory();
	    //mapView.onLowMemory();
	  }
	
}

