package com.daccord.entities;

import java.util.HashMap;
import java.util.Map;

public class Counters {

	/*private HashMap<Integer, Integer>  nivel = new HashMap<>();
	private HashMap<Integer, Integer> chords = new HashMap<>();
	private HashMap<Integer, Integer> genre = new HashMap<>();
	private HashMap<String, Integer> artist = new HashMap<>();*/
	
	private String values;
	private Integer value;
	private Integer key;
	
	public Counters() {
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}
	
	public Map<String, Object> toMapString(String key, Integer value) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("key", key);
        result.put("value", value);

        return result;
    }
	
	public Map<Integer, Object> toMapInteger(Integer key, Integer value) {
        HashMap<Integer, Object> result = new HashMap<>();
        result.put(key, key);
        result.put(value, value);

        return result;
    }

	/*public HashMap<Integer, Integer> getNivel() {
		return nivel;
	}

	public void setNivel(HashMap<Integer, Integer> nivel) {
		this.nivel = nivel;
	}

	public HashMap<Integer, Integer> getChords() {
		return chords;
	}

	public void setChords(HashMap<Integer, Integer> chords) {
		this.chords = chords;
	}

	public HashMap<Integer, Integer> getGenre() {
		return genre;
	}

	public void setGenre(HashMap<Integer, Integer> genre) {
		this.genre = genre;
	}

	public HashMap<String, Integer> getArtist() {
		return artist;
	}

	public void setArtist(HashMap<String, Integer> artist) {
		this.artist = artist;
	}*/
}
