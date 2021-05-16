package com.daccord.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daccord.entities.Song;
import com.daccord.service.SongService;

@RestController
@RequestMapping("/song")
public class SongController {

	@Autowired
	private SongService songService;
	
	@GetMapping("/all")
	public List<Song> getAllSong() throws InterruptedException, ExecutionException {
		return songService.getAllSong();
	}
	
	@GetMapping("/{id}")
	public Song getSongById(String id) throws InterruptedException, ExecutionException {
		return songService.getSongById(id);
	}
}
