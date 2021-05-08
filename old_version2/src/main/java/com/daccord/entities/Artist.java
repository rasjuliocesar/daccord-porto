package com.daccord.entities;

import com.daccord.utils.Utils;

public class Artist {

	private String _id;
	private String artist_genre;
	private String artist_name;
	
	public Artist() {
	}
	
	public Artist(String _id, String artistGenre, String artistName) {

		Utils util = new Utils();
		this._id = util.geradorId();
		
		this.artist_genre = artistGenre;
		this.artist_name = artistName;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getArtist_genre() {
		return artist_genre;
	}

	public void setArtist_genre(String artistGenre) {
		this.artist_genre = artistGenre;
	}

	public String getArtist_name() {
		return artist_name;
	}

	public void setArtist_name(String artistName) {
		this.artist_name = artistName;
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
		Artist other = (Artist) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		return true;
	}
}
