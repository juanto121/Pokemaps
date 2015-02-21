package com.codejo.data;

public class Pokemon {
	private boolean caught;
	private String name;
	private String uri;
	private String sprite_uri;
	private String description_uri;
	
	public Pokemon(String pokename, String pokeuri){
		name = pokename;
		uri = pokeuri;
		caught = false;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getSprite_uri() {
		return sprite_uri;
	}

	public void setSprite_uri(String sprite_uri) {
		this.sprite_uri = sprite_uri;
	}

	public String getDescription_uri() {
		return description_uri;
	}

	public void setDescription_uri(String description_uri) {
		this.description_uri = description_uri;
	}
	
	
	
	
}
