package com.codejo.adapter;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codejo.data.Pokemon;

public  class PokedexParser {
	
	public static ArrayList<Pokemon> parsePokedexFromApi(String jsonPokedex){
		ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();
		try{
			JSONObject responseObject = new JSONObject(jsonPokedex);
			JSONArray pokemons = responseObject.getJSONArray("pokemon");
			
			int pokemons_length = pokemons.length();
			
			for(int index = 0; index < pokemons_length; index++){
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
	
	public static ArrayList<Pokemon> parsePokedexFromStorage(String jsonPokedex){
		//TODO read JSON for stored Pokemons includes caught value and sprite resource (explicit .png uri)
		return null;
	}
	
	public static String parsePokemonForStorage(Pokemon pokemon){
		//TODO parse a single pokemon.
		return null;
	}
	
	public static String parsePokedexForStorage(ArrayList<Pokemon> pokedex){
		//TODO
		JSONArray json_pokedex = new JSONArray();
		JSONObject json_pokemon;
		
		String pokemon_name, pokemon_uri, pokemon_sprite_uri;
		boolean pokemon_caught;
		
		int pokedex_lenght = pokedex.size();
		
		for(int index = 0; index < pokedex_lenght; index++){
			pokemon_name = pokedex.get(index).getName();
			pokemon_uri = pokedex.get(index).getUri();
			pokemon_sprite_uri = pokedex.get(index).getUri();
			pokemon_caught = pokedex.get(index).isCaught();
			
			json_pokemon = new JSONObject();
			try {
				json_pokemon.put( "name" , pokemon_name );
				json_pokemon.put( "uri" , pokemon_uri);
				json_pokemon.put( "sprite_uri", pokemon_sprite_uri);
				json_pokemon.put( "caught", pokemon_caught);
				json_pokedex.put(json_pokemon);
			} catch (JSONException e) {
				// TODO How to properly catch malformed JSON.
				e.printStackTrace();
			}
			
		}
		
		return json_pokedex.toString();
	}

	/*
	 * JSONObject pokemon = new JSONObject();
		try {
			//TODO check for null on each pokemon on the list.
			pokemon.put("name",pokemonList.get(0).getName());
			pokemon.put("uri",pokemonList.get(0).getUri());
			pokemon.put("sprite_uri",pokemonList.get(0).getSprite_uri());
			pokemon.put("caught", pokemonList.get(0).isCaught());
			
			JSONArray json_pokedex = new JSONArray();
			json_pokedex.put(pokemon);
			
			
			
			System.out.println(json_pokedex.toString());
		} catch (JSONException e) {
			// TODO Catch malformed json exception
			e.printStackTrace();
		}
		
	 * 
	 */
	
}
