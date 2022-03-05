package com.pauyigo.capstone.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pauyigo.capstone.exceptions.ResourceNotFoundException;
import com.pauyigo.capstone.dto.UserToReturn;
import com.pauyigo.capstone.models.User;
import com.pauyigo.capstone.repositories.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class UsersController {

	@Autowired
	private UserRepository usersRepo;

	@GetMapping("users")
	public List<UserToReturn> getAllusers() {

		List<User> dbUsers = usersRepo.findAll();
		List<UserToReturn> usersToReturn = new ArrayList<>();

		for (User dbuser : dbUsers) {
			UserToReturn userToReturn = new UserToReturn();
			userToReturn.setId(dbuser.getId());
			userToReturn.setFirstname(dbuser.getFirstname());
			userToReturn.setLastname(dbuser.getLastname());
			userToReturn.setUsername(dbuser.getUsername());
			userToReturn.setEmail(dbuser.getEmail());

			usersToReturn.add(userToReturn);
		}

		return usersToReturn;
	}

	// ResponseEntity represents an HTTP response, including headers, body, and
	// status.
	@GetMapping("users/{id}")
	public ResponseEntity<UserToReturn> getUserById(@PathVariable int id) {

		User user = usersRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found."));

		UserToReturn userToReturn = new UserToReturn();
		userToReturn.setId(user.getId());
		userToReturn.setFirstname(user.getFirstname());
		userToReturn.setLastname(user.getLastname());
		userToReturn.setUsername(user.getUsername());
		userToReturn.setEmail(user.getEmail());

		return ResponseEntity.ok(userToReturn);
	}

	@GetMapping("users/username/{username}")
	public ResponseEntity<UserToReturn> getUserByUsername(@PathVariable String username) {

		User user = usersRepo.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User not found."));

		UserToReturn userToReturn = new UserToReturn();
		userToReturn.setId(user.getId());
		userToReturn.setFirstname(user.getFirstname());
		userToReturn.setLastname(user.getLastname());
		userToReturn.setUsername(user.getUsername());
		userToReturn.setEmail(user.getEmail());

		return ResponseEntity.ok(userToReturn);

	}

	@GetMapping("users/email/{email}")
	public ResponseEntity<UserToReturn> getUserByEmail(@PathVariable String email) {
		User user = usersRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found."));
		UserToReturn userToReturn = new UserToReturn();
		userToReturn.setId(user.getId());
		userToReturn.setFirstname(user.getFirstname());
		userToReturn.setLastname(user.getLastname());
		userToReturn.setUsername(user.getUsername());
		userToReturn.setEmail(user.getEmail());

		return ResponseEntity.ok(userToReturn);

	}
	
	@PostMapping("users")
	public User newUser(@RequestBody User user) {
		return usersRepo.save(user);
		
	}

}
