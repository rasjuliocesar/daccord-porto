package com.daccord.entities;

import com.daccord.utils.Utils;

public class Artists {

	private String _id;
	private String artist_name;
	private String artist_genre;
	
	public Artists() {
	}

	public Artists(String _id, String artist_name, String artist_genre) {
		
		Utils util = new Utils();
		this._id = util.geradorId();
		
		this.artist_name = artist_name;
		this.artist_genre = artist_genre;
	}
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getArtist_name() {
		return artist_name;
	}

	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}

	public String getArtist_genre() {
		return artist_genre;
	}

	public void setArtist_genre(String artist_genre) {
		this.artist_genre = artist_genre;
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
		Artists other = (Artists) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		return true;
	}
	
}
