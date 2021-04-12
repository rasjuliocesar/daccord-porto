package com.daccord.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daccord.entities.Artist;
import com.daccord.service.ArtistService;

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
}
