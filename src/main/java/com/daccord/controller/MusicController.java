package com.daccord.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daccord.entities.Music;
import com.daccord.service.MusicService;

@RestController
@RequestMapping("/music")
public class MusicController {

	@Autowired
	private MusicService musicService;
	
	@GetMapping("/all")
	public List<Music>getAllMusic() throws InterruptedException, ExecutionException {
		return musicService.getAllMusicDetails();
	}
}
