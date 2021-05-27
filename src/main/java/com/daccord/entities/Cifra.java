package com.daccord.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.daccord.utils.Utils;

public class Cifra {

	private String _id;
	private String artist;
	private List<String> chord_sequence = new ArrayList<>();
	private String chord_sheet;
	private List<String> chords = new ArrayList<>();
	private List<Map<String, String>> onset_map = new ArrayList<>();
	private String song_id;
	private String source_url;
	private String title;
	private String version;
	private Integer difficulty;
	
	public Cifra() {
	}

	public Cifra(String _id, String artist, List<String> chord_sequence, String chord_sheet, List<String> chords,
			List<Map<String, String>> onset_map, String song_id, String source_url, String title, String version, Integer difficulty) {
		
		Utils util = new Utils();
		this._id = util.geradorId();
		
		this.artist = artist;
		this.chord_sequence = chord_sequence;
		this.chord_sheet = chord_sheet;
		this.chords = chords;
		this.onset_map = onset_map;
		this.song_id = song_id;
		this.source_url = source_url;
		this.title = title;
		this.version = version;
		this.difficulty = difficulty;
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

	public List<String> getChord_sequence() {
		return chord_sequence;
	}

	public void setChord_sequence(List<String> chord_sequence) {
		this.chord_sequence = chord_sequence;
	}

	public String getChord_sheet() {
		return chord_sheet;
	}

	public void setChord_sheet(String chord_sheet) {
		this.chord_sheet = chord_sheet;
	}

	public List<String> getChords() {
		return chords;
	}

	public void setChords(List<String> chords) {
		this.chords = chords;
	}

	public List<Map<String, String>> getOnset_map() {
		return onset_map;
	}

	public void setOnset_map(List<Map<String, String>> onset_map) {
		this.onset_map = onset_map;
	}

	public String getSong_id() {
		return song_id;
	}

	public void setSong_id(String song_id) {
		this.song_id = song_id;
	}

	public String getSource_url() {
		return source_url;
	}

	public void setSource_url(String source_url) {
		this.source_url = source_url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
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
		Cifra other = (Cifra) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		return true;
	}
}
