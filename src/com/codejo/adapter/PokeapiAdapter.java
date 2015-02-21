package com.codejo.adapter;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.codejo.data.Pokemon;

public  class PokeapiAdapter {
	
	public static ArrayList<Pokemon> parsePokedex(String jsonPokedex){
		ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();
		try{
			JSONObject responseObject = new JSONObject(jsonPokedex);
			JSONArray pokemons = responseObject.getJSONArray("pokemon");
			int pokemons_length = pokemons.length(), index = pokemons_length;
			
			while( index-- >= 0 ){
				JSONObject jsonPokemon = pokemons.getJSONObject(index);
				String name = jsonPokemon.getString("name");
				String uri  = jsonPokemon.getString("resource_uri");
				Pokemon pokemon_to_add = new Pokemon(name, uri);
				pokedex.add(pokemon_to_add);
			}
			
		}catch(Exception e){
			//TODO add exception for handling malformed JSON
		}
		
		return pokedex;
	}
	
	public static Pokemon parsePokemon(){
		return null;
	}

}
