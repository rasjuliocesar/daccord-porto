package com.daccord.entities;

import java.util.HashMap;
import java.util.Map;

public class Counters {

	private String _id;
	private Integer users;
	private Integer artists;
	private Integer song;
	private Map<String, Integer>  nivel = new HashMap<>();
	private Map<String, Integer>  chords = new HashMap<>();
	private Map<String, Integer>  genre = new HashMap<>();
	
	public Counters() {
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Integer getUsers() {
		return users;
	}

	public void setUsers(Integer users) {
		this.users = users;
	}

	public Integer getArtists() {
		return artists;
	}

	public void setArtists(Integer artists) {
		this.artists = artists;
	}

	public Integer getSong() {
		return song;
	}

	public void setSong(Integer song) {
		this.song = song;
	}

	public Map<String, Integer> getNivel() {
		return nivel;
	}

	public void setNivel(Map<String, Integer> nivel) {
		this.nivel = nivel;
	}
	
	public Map<String, Integer> getChords() {
		return chords;
	}

	public void setChords(Map<String, Integer> chords) {
		this.chords = chords;
	}

	public Map<String, Integer> getGenre() {
		return genre;
	}

	public void setGenre(Map<String, Integer> genre) {
		this.genre = genre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Counters other = (Counters) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		return true;
	}
	
	
	
	/*private HashMap<Integer, Integer>  nivel = new HashMap<>();
	private HashMap<Integer, Integer> chords = new HashMap<>();
	private HashMap<Integer, Integer> genre = new HashMap<>();
	private HashMap<String, Integer> artist = new HashMap<>();*/
	
	/*private String values;
	private Integer value;
	private Integer key;*/

	/*public String getValues() {
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
    }*/

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
