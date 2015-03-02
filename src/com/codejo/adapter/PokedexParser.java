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
			
			
		}catch(JSONException e){
			//TODO add exception for handling malformed JSON
		}
		
		return pokedex;
	}
	
	public static ArrayList<Pokemon> parsePokedexFromStorage(String jsonPokedex){
		//TODO read JSON for stored Pokemons includes caught value and sprite resource (explicit .png uri)
		
		ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();
		try {
			JSONArray storedPokedex = new JSONArray(jsonPokedex);
			int pokedex_lenght = storedPokedex.length();
			for(int index = 0; index < pokedex_lenght; index ++){
				JSONObject jsonPokemon = storedPokedex.getJSONObject(index);
				String name = jsonPokemon.getString("name");
				String uri = jsonPokemon.getString("uri");
				boolean caught = jsonPokemon.getBoolean("caught");
				String sprite_uri = jsonPokemon.getString("sprite_uri");
				Pokemon pokemon_to_add = new Pokemon(name,uri,caught,sprite_uri);
				pokedex.add(pokemon_to_add);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pokedex;
	}
	
	public static String parsePokedexForStorage(ArrayList<Pokemon> pokedex){
		//TODO
		JSONArray json_pokedex = new JSONArray();
		JSONObject json_pokemon;
		
		String pokemon_name, pokemon_uri, pokemon_sprite_uri, pokemon_sprite;
		boolean pokemon_caught;
		
		int pokedex_lenght = pokedex.size();
		
		for(int index = 0; index < pokedex_lenght; index++){
			pokemon_name = pokedex.get(index).getName();
			pokemon_uri = pokedex.get(index).getUri();
			pokemon_sprite_uri = pokedex.get(index).getUri();
			pokemon_caught = pokedex.get(index).isCaught();
			pokemon_sprite = pokedex.get(index).getRealImage();
			
			
			json_pokemon = new JSONObject();
			try {
				json_pokemon.put( "name" , pokemon_name );
				json_pokemon.put( "uri" , pokemon_uri);
				json_pokemon.put( "sprite_uri", pokemon_sprite_uri);
				json_pokemon.put("sprite", pokemon_sprite);
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
	 * Parsing JSON formated sprite from Poke Api
	 * @return String array. Index 0 : pokemon name, Index 1 : real sprite url
	 * 			
	 */
	public static String[] parsePokemonSpriteFromApi(String jsonResource) {
		
		String pokemon_image = null;
		String pokemon_name = null;
		
		try {
			JSONObject jsonSprite = new JSONObject(jsonResource);
			pokemon_image = jsonSprite.getString("image");
			pokemon_name = jsonSprite.getString("name");
			
		} catch (JSONException e) {
			//TODO catch malformed JSON
			e.printStackTrace();
		}
		
		//TODO there is a better way to return this image!
		String pokemonImageBundle[] = {pokemon_name, pokemon_image}; 		
		return pokemonImageBundle;
	}
}
