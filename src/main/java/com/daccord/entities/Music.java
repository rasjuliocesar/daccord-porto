package com.daccord.entities;

import java.util.ArrayList;
import java.util.List;

public class Music {

	private String _id;
	private String artista;
	private String titulo;
	private String link;
	private String nivel;
	private Integer music_user;
	private String genero;
	
	public Music() {
	}

	public Music(String _id, String artista, String titulo, String link, String nivel, Integer music_user,
			String genero) {
		this._id = _id;
		this.artista = artista;
		this.titulo = titulo;
		this.link = link;
		this.nivel = nivel;
		this.music_user = music_user;
		this.genero = genero;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public Integer getMusic_user() {
		return music_user;
	}

	public void setMusic_user(Integer music_user) {
		this.music_user = music_user;
	}

	public String getGenero() {
		
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public List<String> listaDeGenero(){
		List<String> listaGenero = new ArrayList<String>();
		
		listaGenero.add("Rock");
		listaGenero.add("Pop");
		listaGenero.add("Samba");
		listaGenero.add("Reggae");
		listaGenero.add("Sertanejo");
		
		return listaGenero;
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
		Music other = (Music) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		return true;
	}
	
	
}
