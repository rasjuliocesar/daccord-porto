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
import com.daccord.utils.Utils;

@RestController
@RequestMapping("/artists")
public class ArtistsController {

	@Autowired
	private ArtistsService artistsService;
	
	@GetMapping("/all")
	public List<Artists> getAllArtist() throws InterruptedException, ExecutionException {
		return artistsService.getAllArtist();
	}
	
	@GetMapping("/{id}")
	public Artists getArtistById(@PathVariable String id) throws InterruptedException, ExecutionException {
		return artistsService.getArtistById(id);
	}
	
	@PostMapping("/add")
	public String addArtist(@RequestBody Artists artist) throws InterruptedException, ExecutionException {
		Utils util = new Utils();
		artist.set_id(util.geradorId());
		
		return artistsService.addArtist(artist);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteArtist(@PathVariable String id) {
		return artistsService.deleteArtistById(id);
	}
	
	@PutMapping("/update")
	public String updateArtist(@RequestBody Artists artist) throws InterruptedException, ExecutionException {
		return artistsService.updateArtist(artist);
	}
}
