package com.daccord.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daccord.entities.User;
import com.daccord.service.AuthService;

@RestController
@RequestMapping("/login")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping
	public ResponseEntity<User> authenticate(@RequestBody User auth)
			throws InterruptedException, ExecutionException {
		User user = authService.getAuthentication(auth.getEmail(), auth.getPassword());
		if (user != null) {
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}
