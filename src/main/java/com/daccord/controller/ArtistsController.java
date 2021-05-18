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
import com.daccord.service.CountersService;
import com.daccord.utils.Utils;

@RestController
@RequestMapping("/artists")
public class ArtistsController {

	@Autowired
	private ArtistsService artistsService;
	@Autowired
	private CountersService countersService;
	
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
		
		String result = artistsService.addArtist(artist);
		
		if(result != null) {
			countersService.incrementCountersArtists();			
		}
		
		return result;
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteArtist(@PathVariable String id) throws InterruptedException, ExecutionException {
		String result = artistsService.deleteArtistById(id);
		
		if(result != null) {
			countersService.decrementCountersArtists();			
		}
	
		return result;
	}
	
	@PutMapping("/update")
	public String updateArtist(@RequestBody Artists artist) throws InterruptedException, ExecutionException {
		return artistsService.updateArtist(artist);
	}
	
	@GetMapping("nameart/{name}")
	public String getNameArtistById(@PathVariable String name) throws InterruptedException, ExecutionException {
		return artistsService.getNameArtistById(name);
	}
}
