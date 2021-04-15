package com.daccord.entities;

import com.daccord.utils.Utils;

public class Song {

	private String _id;
	private String artist;
	private String artist_id;
	private String link_audio;
	private String title;
	
	public Song() {
	}

	public Song(String _id, String artist, String artist_id, String link_audio, String title) {
		
		Utils util = new Utils();
		this._id = util.geradorId();
		
		this.artist = artist;
		this.artist_id = artist_id;
		this.link_audio = link_audio;
		this.title = title;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getArtist_id() {
		return artist_id;
	}

	public void setArtist_id(String artist_id) {
		this.artist_id = artist_id;
	}

	public String getLink_audio() {
		return link_audio;
	}

	public void setLink_audio(String link_audio) {
		this.link_audio = link_audio;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
		Song other = (Song) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		return true;
	}
}
