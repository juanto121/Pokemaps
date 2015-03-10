package com.codejo.adapter;

import com.codejo.data.Pokemon;
import com.codejo.pokeapitasks.PokemonImageTask;
import com.codejo.pokemaps.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PokedexListViewAdapter extends ArrayAdapter<Pokemon>{

	private PokemonImageTask pokemonImageTask;
	private LayoutInflater layoutInflater;
	
	private static final String TAG = "ListViewAdapter";
	
	
	public PokedexListViewAdapter(Context context, Pokemon[] resource, PokemonImageTask pokemonImageTask) {
		super(context, R.layout.list_row_layout,resource);
		this.pokemonImageTask = pokemonImageTask;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(getContext());
		
		PokemonRowView pokemonRow;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.list_row_layout, parent, false);
			pokemonRow = new PokemonRowView();
			TextView pokemonTextView = (TextView) convertView.findViewById(R.id.listItem);
			pokemonRow.pokemonName = pokemonTextView; 
			ImageView picture = (ImageView) convertView.findViewById(R.id.pokemonPicture);
			pokemonRow.pokemonSprite = picture;
			convertView.setTag(pokemonRow);
		}else{
			pokemonRow = (PokemonRowView) convertView.getTag();
		}
		
		Pokemon pokemon = getItem(position);
		pokemonRow.pokemonName.setText(pokemon.getName());
		String realImageUrl = pokemon.getRealImage();
		if(realImageUrl != null && !realImageUrl.equals("") ){
			pokemonRow.pokemonSprite.setTag(pokemon.getRealImage());
			Drawable picture = pokemonImageTask.loadSprite(this, pokemonRow.pokemonSprite);
			if(picture != null){
				Log.d(TAG, "Sprite retrieved from HashMap");
				pokemonRow.pokemonSprite.setImageDrawable(picture);	
			}
		}else{
			pokemonRow.pokemonSprite.setImageResource(R.drawable.pokeball_pixe_sm_gray);
		}
		
		return convertView;
	}

	private static class PokemonRowView{
		public TextView pokemonName;
		public ImageView pokemonSprite;		
	}
	
}
