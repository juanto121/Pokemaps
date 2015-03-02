package com.codejo.adapter;

import com.codejo.data.Pokemon;
import com.codejo.pokemaps.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PokedexListViewAdapter extends ArrayAdapter<Pokemon>{

	
	public PokedexListViewAdapter(Context context, Pokemon[] resource) {
		super(context, R.layout.list_row_layout,resource);
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View view = inflater.inflate(R.layout.list_row_layout, parent,false);
		Pokemon pokemon = getItem(position);
		//TODO CARE HERE ONLY TESTING!!!
		if(position == 2) pokemon.setCaught(true);
		TextView pokemonTextView = (TextView) view.findViewById(R.id.listItem);
		pokemonTextView.setText(pokemon.toString());
		pokemonTextView.setGravity(Gravity.CENTER_VERTICAL);
		ImageView picture = (ImageView) view.findViewById(R.id.pokemonPicture);
		if(pokemon.isCaught()){
			//TODO change for the image not the textView.
			pokemonTextView.setText(pokemon.toString() + pokemon.getRealImage());
		}else{
			picture.setImageResource(R.drawable.pokeball_pixe_sm_gray);
		}
		return view;
	}

}
