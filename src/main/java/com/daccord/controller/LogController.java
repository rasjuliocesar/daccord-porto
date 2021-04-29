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

import com.daccord.entities.Log;
import com.daccord.service.LogService;
import com.daccord.utils.Utils;

@RestController
@RequestMapping("/log")
public class LogController {

	@Autowired
	private LogService logService;
	
	@GetMapping("/all")
	public List<Log> getAllLog() throws InterruptedException, ExecutionException {
		return logService.getAllLog();
	}
	
	@GetMapping("/{id}")
	public Log getLogById(@PathVariable String id) throws InterruptedException, ExecutionException {
		return logService.getLogById(id);
	}
	
	@PostMapping("/add")
	public String addLog(@RequestBody Log log) throws InterruptedException, ExecutionException {
		Utils util = new Utils();
		log.set_id(util.geradorId());
		
		return logService.addLog(log);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteLog(@PathVariable String id) {
		return logService.deleteLogById(id);
	}
	
	@PutMapping("/update")
	public String updateLog(@RequestBody Log log) throws InterruptedException, ExecutionException {
		return logService.updateLog(log);
	}
}
