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

import com.daccord.entities.Genre;
import com.daccord.service.GenreService;
import com.daccord.utils.Utils;

@RestController
@RequestMapping("/genre")
public class GenreController {
	
	@Autowired
	private GenreService genreService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Genre>> getAllGenre() throws InterruptedException, ExecutionException {
		return ResponseEntity.ok().body(genreService.getAllGenre());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Genre> getGenretById(@PathVariable String id)
			throws InterruptedException, ExecutionException {
		return ResponseEntity.ok().body(genreService.getGenreById(id));
	}
	
	@PostMapping("/add")
	public ResponseEntity<Void> addGenre(@RequestBody Genre genre) 
			throws InterruptedException, ExecutionException {
		Utils util = new Utils();
		genre.set_id(util.geradorId());

		genreService.addGenre(genre);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteGenre(@PathVariable String id) 
			throws InterruptedException, ExecutionException {
		genreService.deleteGenreById(id);

		return ResponseEntity.noContent().build();
	}

	@PutMapping("/update")
	public ResponseEntity<Void> updateGenre(@RequestBody Genre genre)
			throws InterruptedException, ExecutionException {
		genreService.updateGenre(genre);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("namegenre/{name}")
	public ResponseEntity<Void> getIdGenreByName(@PathVariable String name)
			throws InterruptedException, ExecutionException {
		genreService.getIdGenreByName(name);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("id3/{id3}")
	public ResponseEntity<Genre> getNameGenretById3(@PathVariable String id3)
			throws InterruptedException, ExecutionException {
		genreService.getNameGenretById3(id3);
		return ResponseEntity.noContent().build();
	}
}
