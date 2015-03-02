package com.codejo.data;

public class Pokemon {
	private boolean caught;
	private String name;
	private String uri;
	private String sprite_uri;
	private String description_uri;
	private String real_sprite_uri;
	
	public Pokemon(String pokename, String pokeuri){
		name = pokename.substring(0, 1).toUpperCase() + pokename.substring(1);
		uri = pokeuri;
		caught = false;
	}

	public Pokemon(String name, String uri, boolean caught,
			String sprite_uri) {
	
		this(name, uri);
		
		this.caught = caught;
		this.sprite_uri = sprite_uri;
		
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

	public boolean isCaught() {
		return caught;
	}

	public void setCaught(boolean caught) {
		this.caught = caught;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public void setRealSpriteUri(String real_sprite){
		this.real_sprite_uri = real_sprite;
	}

	public String getRealImage() {
		return this.real_sprite_uri;
	}
	
	
	
	
}
