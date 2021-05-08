package com.daccord.entities;

import java.util.HashMap;

public class Counters {

	private HashMap<Integer, Integer>  nivel = new HashMap<>();
	private HashMap<Integer, Integer> chords = new HashMap<>();
	private HashMap<Integer, Integer> genre = new HashMap<>();
	private HashMap<String, Integer> artist = new HashMap<>();
	
	public Counters() {
	}

	public HashMap<Integer, Integer> getNivel() {
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
	}
}
