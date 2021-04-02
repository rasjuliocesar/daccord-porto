package com.daccord.entities;

public class Music {

	private Long _id;
	private String artista;
	private String titulo;
	private String link;
	private String nivel;
	private Integer music_user;
	private String genero;
	
	public Music() {
	}

	public Music(Long _id, String artista, String titulo, String link, String nivel, Integer music_user,
			String genero) {
		this._id = _id;
		this.artista = artista;
		this.titulo = titulo;
		this.link = link;
		this.nivel = nivel;
		this.music_user = music_user;
		this.genero = genero;
	}

	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
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
}
