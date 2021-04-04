package com.daccord.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daccord.entities.Music;
import com.daccord.service.MusicService;

@RestController
@RequestMapping("/music")
public class MusicController {

	@Autowired
	private MusicService musicService;
	
	@PostMapping("/add")
	public String saveMusic(@RequestBody Music music) throws InterruptedException, ExecutionException {
		
		music.set_id(music.geradorId());
		
		return musicService.saveMusic(music);
	}
	
	@PutMapping("/update")
	public String update(@RequestBody Music music) throws InterruptedException, ExecutionException {
		return musicService.updateMusic(music);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteMusic(@PathVariable String id) throws InterruptedException, ExecutionException {
		return musicService.deleteMusicById(id);
	}
	
	@GetMapping("/{id}")
	public Music getMusicId(@PathVariable String id) throws InterruptedException, ExecutionException {
		return musicService.getMusicDetailsById(id);
	}
	
	@GetMapping("/all")
	public List<Music>getAllMusic() throws InterruptedException, ExecutionException {
		return musicService.getAllMusicDetails();
	}
	
	@GetMapping("/artista/{artista}")
	public List<Music> getMusicArtista(@PathVariable String artista) throws InterruptedException, ExecutionException {
		return musicService.getMusicDetailsByArtista(artista);
	}
	
	@GetMapping("/titulo/{titulo}")
	public List<Music> getMusicTitulo(@PathVariable String titulo) throws InterruptedException, ExecutionException {
		return musicService.getMusicDetailsByTitulo(titulo);
	}
	
	@GetMapping("/nivel/{nivel}")
	public List<Music> getMusicNivel(@PathVariable String nivel) throws InterruptedException, ExecutionException {
		return musicService.getMusicDetailsByNivel(nivel);
	}
	
	@GetMapping("/genero/{genero}")
	public List<Music> getMusicGenero(@PathVariable String genero) throws InterruptedException, ExecutionException {
		return musicService.getMusicDetailsByGenero(genero);
	}
	
	@GetMapping("/acordes/{acordes}")
	public List<Music> getMusicAcordes(@PathVariable Integer acordes) throws InterruptedException, ExecutionException {
		return musicService.getMusicDetailsByAcordes(acordes);
	}
	
	@GetMapping("/genero/qnt/{genero}")
	public int getMusicQntGenero(@PathVariable String genero) throws InterruptedException, ExecutionException {
		List<Music> musicas = musicService.getMusicDetailsByGenero(genero);
		if(musicas != null) {
			return musicas.size();			
		}		
		return 0;
	}
}
