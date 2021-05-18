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

import com.daccord.entities.Song;
import com.daccord.service.ArtistsService;
import com.daccord.service.CountersService;
import com.daccord.service.SongService;
import com.daccord.utils.Utils;

@RestController
@RequestMapping("/song")
public class SongController {

	@Autowired
	private SongService songService;
	@Autowired
	private CountersService countersService;
	@Autowired
	private ArtistsService artistsService;
	
	@GetMapping("/all")
	public List<Song> getAllSong() throws InterruptedException, ExecutionException {
		return songService.getAllSong();
	}
	
	@GetMapping("/{id}")
	public Song getSongById(@PathVariable String id) throws InterruptedException, ExecutionException {
		return songService.getSongById(id);
	}
	
	@PostMapping("/add")
	public String addSong(@RequestBody Song song) throws InterruptedException, ExecutionException {
		Utils util = new Utils();
		song.set_id(util.geradorId());
		
		song.setArtist_id(artistsService.getNameArtistById(song.getArtist()));
		
		String result = songService.addSong(song);
		
		if(result != null) {
			countersService.incrementCountersSong();
		}
		
		return result;
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteSongById(@PathVariable String id) throws InterruptedException, ExecutionException {
		String result = songService.deleteSongById(id);;
		
		if(result != null) {
			countersService.decrementCountersSong();
		}
		return result;
	}
	
	@PutMapping("/update")
	public String updateSong(@RequestBody Song song) throws InterruptedException, ExecutionException {
		return songService.updateSong(song);
	}
}
