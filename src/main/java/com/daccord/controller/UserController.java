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

import com.daccord.entities.User;
import com.daccord.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public List<User> getAllUser() throws InterruptedException, ExecutionException {
		return userService.getAllUser();
	}
	
	@GetMapping("/{name}")
	public User getUserByName(@PathVariable String name) throws InterruptedException, ExecutionException {
		return userService.getUserByName(name);
	}
	
	@PostMapping("/add")
	public String addUser(@RequestBody User user) throws InterruptedException, ExecutionException {
		return userService.addUser(user);
	}
	
	@DeleteMapping("/delete/{name}")
	public String deleteUser(String name) {
		return userService.deleteUserByName(name);
	}
	
	@PutMapping("update")
	public String updateUser(@RequestBody User user) throws InterruptedException, ExecutionException {
		return userService.updateUser(user);
	}
}
