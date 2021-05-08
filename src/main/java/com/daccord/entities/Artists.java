package com.daccord.entities;

public class Artists {

	private String artist_name;
	private String artist_genre;
	
	public Artists() {
	}

	public Artists(String artist_name, String artist_genre) {
		this.artist_name = artist_name;
		this.artist_genre = artist_genre;
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
		result = prime * result + ((artist_name == null) ? 0 : artist_name.hashCode());
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
		if (artist_name == null) {
			if (other.artist_name != null)
				return false;
		} else if (!artist_name.equals(other.artist_name))
			return false;
		return true;
	}
}
