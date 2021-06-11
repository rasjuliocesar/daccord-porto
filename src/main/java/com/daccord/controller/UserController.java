package com.daccord.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

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

import com.daccord.entities.User;
import com.daccord.service.CountersService;
import com.daccord.service.UserService;
import com.daccord.utils.Utils;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private CountersService countersService;

	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUser() throws InterruptedException, ExecutionException {
		return ResponseEntity.ok().body(userService.getAllUser());
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<User>> getPageUser(Pageable pageable) 
			throws InterruptedException, ExecutionException {

		return ResponseEntity.ok().body(userService.getPageUser(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable String id) throws InterruptedException, ExecutionException {
		return ResponseEntity.ok().body(userService.getUserById(id));
	}

	@PostMapping("/add")
	public ResponseEntity<Void> addUser(@RequestBody User user) throws InterruptedException, ExecutionException {
		Utils util = new Utils();
		user.set_id(util.geradorId());

		String result = userService.addUser(user);

		if (result != null) {
			countersService.incrementCountersUser();
		}
		
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) throws InterruptedException, ExecutionException {
		String result = userService.deleteUserById(id);

		if (result != null) {
			countersService.decrementCountersUser();
		}
		return ResponseEntity.noContent().build();
	}

	@PutMapping("update")
	public ResponseEntity<Void> updateUser(@RequestBody User user) throws InterruptedException, ExecutionException {
		userService.updateUser(user);
		return ResponseEntity.noContent().build();
	}
}
