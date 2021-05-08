package com.daccord.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daccord.entities.Counters;
import com.daccord.service.CountersService;
import com.google.cloud.firestore.QueryDocumentSnapshot;

@RestController
@RequestMapping("/counters")
public class CountersController {
	
	@Autowired
	private CountersService countersService;
	
	@GetMapping("/all")
	public List<QueryDocumentSnapshot> getAllGenre() throws InterruptedException, ExecutionException {
		return countersService.getAllGenre();
	}

}
