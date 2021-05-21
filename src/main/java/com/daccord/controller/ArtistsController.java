package com.daccord.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<Artists>> getAllArtist() 
			throws InterruptedException, ExecutionException {

		return ResponseEntity.ok().body(artistsService.getAllArtist());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Artists> getArtistById(@PathVariable String id)
			throws InterruptedException, ExecutionException {
		return ResponseEntity.ok().body(artistsService.getArtistById(id));
	}

	@PostMapping("/add")
	public ResponseEntity<Void> addArtist(@RequestBody Artists artist) 
			throws InterruptedException, ExecutionException {
		Utils util = new Utils();
		artist.set_id(util.geradorId());

		String result = artistsService.addArtist(artist);

		if (result != null) {
			countersService.incrementCountersArtists();
		}

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteArtist(@PathVariable String id) 
			throws InterruptedException, ExecutionException {
		String result = artistsService.deleteArtistById(id);

		if (result != null) {
			countersService.decrementCountersArtists();
		}
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/update")
	public ResponseEntity<Void> updateArtist(@RequestBody Artists artist)
			throws InterruptedException, ExecutionException {
		artistsService.updateArtist(artist);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("nameart/{name}")
	public ResponseEntity<Void> getNameArtistById(@PathVariable String name)
			throws InterruptedException, ExecutionException {
		artistsService.getNameArtistById(name);
		return ResponseEntity.noContent().build();
	}
}
