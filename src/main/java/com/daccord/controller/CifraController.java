package com.daccord.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daccord.entities.Cifra;
import com.daccord.service.CifraService;
import com.daccord.utils.Utils;

@RestController
@RequestMapping("/cifra")
public class CifraController {

	@Autowired
	private CifraService cifraService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Cifra>> getAllCifra() 
			throws InterruptedException, ExecutionException {

		return ResponseEntity.ok().body(cifraService.getAllCifra());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cifra> getCifraById(@PathVariable String id)
			throws InterruptedException, ExecutionException {
		return ResponseEntity.ok().body(cifraService.getCifraById(id));
	}

	@PostMapping("/add")
	public ResponseEntity<Void> addCifra(@RequestBody Cifra cifra) 
			throws InterruptedException, ExecutionException {
		Utils util = new Utils();
		cifra.set_id(util.geradorId());

		cifraService.addCifra(cifra);

		return ResponseEntity.noContent().build();
	}
}
