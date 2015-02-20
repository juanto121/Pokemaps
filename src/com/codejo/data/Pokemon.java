package com.codejo.data;

public class Pokemon {
	private String name;
	private String uri;
	
	public Pokemon(String pokename, String pokeuri){
		name = pokename;
		uri = pokeuri;
	}

	@Override
	public String toString() {
		return "Pokemon [name=" + name + "]";
	}
	
	
}
