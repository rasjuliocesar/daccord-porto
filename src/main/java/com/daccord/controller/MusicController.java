package com.daccord.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/{id}")
	public Music getMusicId(@PathVariable String id) throws InterruptedException, ExecutionException {
		return musicService.getMusicDetailsById(id);
	}
	
	@GetMapping("/all")
	public List<Music>getAllMusic() throws InterruptedException, ExecutionException {
		return musicService.getAllMusicDetails();
	}
	
	@GetMapping("/nivel/{nivel}")
	public List<Music> getMusicNivel(@PathVariable String nivel) throws InterruptedException, ExecutionException {
		return musicService.getMusicDetailsByNivel(nivel);
	}
	
	@GetMapping("/genero/{genero}")
	public List<Music> getMusicGenero(@PathVariable String genero) throws InterruptedException, ExecutionException {
		return musicService.getMusicDetailsByGenero(genero);
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
