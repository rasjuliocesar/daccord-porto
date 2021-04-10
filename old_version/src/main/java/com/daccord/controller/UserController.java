package com.daccord.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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

import com.daccord.entities.User;
import com.daccord.service.UserService;
import com.daccord.utilities.Utilities;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public String saveUser(@RequestBody User user) throws InterruptedException, ExecutionException, NoSuchAlgorithmException, UnsupportedEncodingException {
		
		Utilities util = new Utilities();
		user.set_id(util.geradorId());
		//user.setPassword(util.criptografar(user.getPassword()));
		
		return userService.saveUser(user);
	}
	
	@PutMapping("/update")
	public String update(@RequestBody User user) throws InterruptedException, ExecutionException {
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteMusic(@PathVariable String id) throws InterruptedException, ExecutionException {
		return userService.deleteUserById(id);
	}
	
	@GetMapping("/{id}")
	public User getMusicId(@PathVariable String id) throws InterruptedException, ExecutionException {
		return userService.getUserDetailsById(id);
	}
	
	@GetMapping("/all")
	public List<User>getAllMusic() throws InterruptedException, ExecutionException {
		return userService.getAllUserDetails();
	}
}
