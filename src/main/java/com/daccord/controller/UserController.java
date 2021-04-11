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
import com.daccord.utils.Utils;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public List<User> getAllUser() throws InterruptedException, ExecutionException {
		return userService.getAllUser();
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable String id) throws InterruptedException, ExecutionException {
		return userService.getUserById(id);
	}
	
	@PostMapping("/add")
	public String addUser(@RequestBody User user) throws InterruptedException, ExecutionException {
		Utils util = new Utils();
		user.set_id(util.geradorId());
		
		return userService.addUser(user);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable String id) {
		return userService.deleteUserById(id);
	}
	
	@PutMapping("update")
	public String updateUser(@RequestBody User user) throws InterruptedException, ExecutionException {
		return userService.updateUser(user);
	}
}
