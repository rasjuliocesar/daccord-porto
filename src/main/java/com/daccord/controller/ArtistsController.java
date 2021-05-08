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

import com.daccord.entities.Artists;
import com.daccord.service.ArtistsService;

@RestController
@RequestMapping("/artists")
public class ArtistsController {

	@Autowired
	private ArtistsService artistsService;
	
	@GetMapping("/all")
	public List<Artists> getAllArtist() throws InterruptedException, ExecutionException {
		return artistsService.getAllArtist();
	}
	
	@GetMapping("/{name}")
	public Artists getArtistByName(@PathVariable String name) throws InterruptedException, ExecutionException {
		return artistsService.getArtistByName(name);
	}
		
	@PostMapping("/add")
	public String addArtist(@RequestBody Artists artist) throws InterruptedException, ExecutionException {		
		return artistsService.addArtist(artist);
	}
	
	@DeleteMapping("/delete/{name}")
	public String deleteArtistByName(@PathVariable String name) throws InterruptedException, ExecutionException {
		
		Artists artist = artistsService.getArtistByName(name);
		
		try {
			if(artist.equals(null)) {
				return "Artist does not exists!";
			}
		} catch(NullPointerException e) {
			return e.getMessage();
		}
		
		return artistsService.deleteArtistByName(name);
	}
	
	@PutMapping("/update")
	public String updateArtist(@RequestBody Artists update) throws InterruptedException, ExecutionException {
		
		Artists artists = artistsService.getArtistByName(update.getArtist_name());
		
		try {
			if(artists.getArtist_name().equals(null)) {
				return "Artist does not exists!";
			}
		} catch(NullPointerException e) {
			return e.getMessage();
		}
		
		return artistsService.updateArtist(update);
	}
}
