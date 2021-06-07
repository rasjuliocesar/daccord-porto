package com.daccord.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daccord.entities.Song;
import com.daccord.service.ArtistsService;
import com.daccord.service.CountersService;
import com.daccord.service.SongService;
import com.daccord.utils.Utils;

@RestController
@RequestMapping("/song")
public class SongController {

	@Autowired
	private SongService songService;
	@Autowired
	private CountersService countersService;
	@Autowired
	private ArtistsService artistsService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Song>> getAllSong() throws InterruptedException, ExecutionException {
		return ResponseEntity.ok().body(songService.getAllSong());
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<Song>> getPageSong(Pageable pageable) throws InterruptedException, ExecutionException {
		return ResponseEntity.ok().body(songService.getPageSong(pageable));
	}
	
	@GetMapping("/difficulty")
	public ResponseEntity<Map<Object, Long>> getDifficulty() throws InterruptedException, ExecutionException {		
		return ResponseEntity.ok().body(songService.getDifficulty());
	}
	
	@GetMapping("/chords")
	public ResponseEntity<List<JSONObject>> getChords() throws InterruptedException, ExecutionException {		
		return ResponseEntity.ok().body(songService.getChords());
	}

	@GetMapping("/genre")
	public ResponseEntity<Map<Object, Long>> getGenre() throws InterruptedException, ExecutionException {		
		return ResponseEntity.ok().body(songService.getGenre());
	}
	
	@GetMapping("/artist")
	public ResponseEntity<Map<Object, Long>> getArtist() throws InterruptedException, ExecutionException {		
		return ResponseEntity.ok().body(songService.getArtist());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Song>getSongById(@PathVariable String id) throws InterruptedException, ExecutionException {
		return ResponseEntity.ok().body(songService.getSongById(id));
	}
	
	@GetMapping("/all/genre/{value}")
	public ResponseEntity<List<Song>> getAllSongByGenre(@PathVariable Integer value) throws InterruptedException, ExecutionException {
		return ResponseEntity.ok().body(songService.getAllSongByGenre(value));
	}
	
	@GetMapping("/all/artist/{name}")
	public ResponseEntity<List<Song>> getAllSongByArtist(@PathVariable String name) throws InterruptedException, ExecutionException {
		return ResponseEntity.ok().body(songService.getAllSongByArtist(name));
	}
	
	@GetMapping("/all/song/{name}")
	public ResponseEntity<List<Song>> getAllSongByName(@PathVariable String name) throws InterruptedException, ExecutionException {
		return ResponseEntity.ok().body(songService.getAllSongByName(name));
	}
	
	@SuppressWarnings("unused")
	@GetMapping("/count/song")
	public ResponseEntity<Integer> getCountAllSong() throws InterruptedException, ExecutionException {
		Integer count = songService.getAllSong().size();
		
		if(count != null) {
			return ResponseEntity.ok().body(count);
		} else {
			return ResponseEntity.ok().body(0);
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<Void> addSong(@RequestBody Song song) throws InterruptedException, ExecutionException {
		Utils util = new Utils();
		song.set_id(util.geradorId());
		
		song.setArtist_id(artistsService.getNameArtistById(song.getArtist()));
	
		String result = songService.addSong(song);
		
		if(result != null) {
			countersService.incrementCountersSong();
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteSongById(@PathVariable String id) throws InterruptedException, ExecutionException {
		String result = songService.deleteSongById(id);;
		
		if(result != null) {
			countersService.decrementCountersSong();
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/update")
	public ResponseEntity<Void> updateSong(@RequestBody Song song) throws InterruptedException, ExecutionException {
		songService.updateSong(song);
		return ResponseEntity.noContent().build();
	}
	
	public String addSongFile() 
			throws InterruptedException, ExecutionException {
		Song song = new Song();
		Integer count = 0;
		String path = "C:\\drivers\\song.txt";
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			String line = br.readLine();
			
			while(line != null) {
				String[] field = line.split(";");
				song.setArtist(field[0]);
				
				Integer bpm = Integer.parseInt(field[1]);
				song.setBpm(bpm);
				
				Integer genre = Integer.parseInt(field[2]);
				song.setGenre(genre);
				
				song.setLink_audio(field[3]);
				song.setTitle(field[4]);
				song.setChord_sheet(field[5]);
				
				List<String> chords = new ArrayList<String>();
				String[] fieldChords = field[6].split(",");
				for(String c : fieldChords) {
					chords.add(c);
				}
				song.setChords(chords);
				
				song.setSource_url(field[7]);
				
				Integer difficulty = Integer.parseInt(field[8]);
				song.setDifficulty(difficulty);
				
				song.setVersion(field[9]);
				addSong(song);
				line = br.readLine();
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return count + " Músicas Cadastradas";
	}
}
