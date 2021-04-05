package com.daccord.entities;

import java.util.ArrayList;
import java.util.List;

import com.daccord.utilities.Utilities;

public class Music {

	private String _id;
	private String titulo;
	private String artista;
	private String genero;
	private String album;
	private Integer ano;
	private String comentario;
	private String link;
	private String nivel;
	private Integer music_user;
	private Integer acordes;
	private User user;
	
	
	public Music() {
	}
	
	public Music(String titulo, String artista, String genero, String album, Integer ano, String comentario,
			String link, String nivel, Integer music_user, Integer acordes, User user) {

		Utilities util = new Utilities();
		this._id = util.geradorId();
		
		this.titulo = titulo;
		this.artista = artista;
		this.genero = genero;
		this.album = album;
		this.ano = ano;
		this.comentario = comentario;
		this.link = link;
		this.nivel = nivel;
		this.music_user = music_user;
		this.acordes = acordes;
		this.user = user;
	}
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
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

	public Integer getAcordes() {
		return acordes;
	}

	public void setAcordes(Integer acordes) {
		this.acordes = acordes;
	}

	/**
	 * Lista pre definida de generos
	 * @return listaGenero
	 * @author Carlos.Pereira
	 */
	public List<String> listaDeGenero(){
		List<String> listaGenero = new ArrayList<String>();
		
		listaGenero.add("Axé");
		listaGenero.add("Blues");
		listaGenero.add("Country");
		listaGenero.add("Eletrônica");
		listaGenero.add("Forró");
		listaGenero.add("Funk");
		listaGenero.add("Gospel");
		listaGenero.add("Hip Hop");
		listaGenero.add("Jazz");
		listaGenero.add("MPB");
		listaGenero.add("Música clássica");
		listaGenero.add("Pagode");
		listaGenero.add("Pop");
		listaGenero.add("Reggae");
		listaGenero.add("Rock");
		listaGenero.add("Samba");
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
