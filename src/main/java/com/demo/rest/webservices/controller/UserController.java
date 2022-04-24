package com.demo.rest.webservices.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.rest.webservices.model.User;
import com.demo.rest.webservices.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping
@RestController
@Slf4j
public class UserController {

	@Autowired
	private UserService service;

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		log.info("In Service Method :{}" + service.findAll());

		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public User retrievUser(@PathVariable Integer id) {
		log.info("In Service Method :{}" + service.findOne(id));

		User user = service.findOne(id);
		if (user == null)
			throw new UserNotFoundException("id-" + id);

		return user;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Validated @RequestBody User user) {

		log.info("UserCreation:{}" + user);

		User savedUser = service.save(user);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		
		if(user == null)
			throw new UserNotFoundException("id-" +id);
		
	}
}
