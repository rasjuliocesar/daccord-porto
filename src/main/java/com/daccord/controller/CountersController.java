package com.daccord.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daccord.entities.Counters;
import com.daccord.service.CountersService;

@RestController
@RequestMapping("/counters")
public class CountersController {
	
	@Autowired
	private CountersService countersService;
	
	@GetMapping("/{id}")
	public Counters getCountersById(@PathVariable String id) throws InterruptedException, ExecutionException {
		return countersService.getCountersById(id);
	}
	
	@PutMapping("/increment")
	public String incrementCountersArtists() throws InterruptedException, ExecutionException {
		return countersService.incrementCountersArtists();
	}
	
	@PutMapping("/decrement")
	public String decrementCountersArtists() throws InterruptedException, ExecutionException {
		return countersService.decrementCountersArtists();
	}
	
	@PutMapping("/increment/nivel/{key}")
	public String incrementCountersNivel(@PathVariable String key) throws InterruptedException, ExecutionException {
		return countersService.incrementCountersNivel(key);
	}
	
	
	
}
