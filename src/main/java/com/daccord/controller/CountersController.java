package com.daccord.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daccord.entities.Counters;
import com.daccord.service.CountersService;

@RestController
@RequestMapping("/counters")
public class CountersController {
	
	@Autowired
	private CountersService countersService;
	
	/*@GetMapping("/all")
	public List<QueryDocumentSnapshot> getAllGenre() throws InterruptedException, ExecutionException {
		return countersService.getAllGenre();
	}*/

	@GetMapping("/{id}")
	public Counters getCountersById(String id) throws InterruptedException, ExecutionException {
		return countersService.getCountersById(id);
	}
	
	@PutMapping("/update")
	public String updateCounters(@RequestBody String field, Integer value) throws InterruptedException, ExecutionException {
		return countersService.updateCounters(field, value);
	}
}
