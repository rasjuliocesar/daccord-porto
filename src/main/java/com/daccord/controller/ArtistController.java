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

import com.daccord.entities.Artist;
import com.daccord.service.ArtistService;
import com.daccord.utils.Utils;

@RestController
@RequestMapping("/artist")
public class ArtistController {

	@Autowired
	private ArtistService artistService;
	
	@GetMapping("/all")
	public List<Artist> getAllArtist() throws InterruptedException, ExecutionException {
		return artistService.getAllArtist();
	}
	
	@GetMapping("/{id}")
	public Artist getArtistById(@PathVariable String id) throws InterruptedException, ExecutionException {
		return artistService.getArtistById(id);
	}
	
	@PostMapping("/add")
	public String addArtist(@RequestBody Artist artist) throws InterruptedException, ExecutionException {
		Utils util = new Utils();
		artist.set_id(util.geradorId());
		
		return artistService.addArtist(artist);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteArtist(@PathVariable String id) {
		return artistService.deleteArtistById(id);
	}
	
	@PutMapping("/update")
	public String updateArtist(@RequestBody Artist artist) throws InterruptedException, ExecutionException {
		return artistService.updateArtist(artist);
	}
}
