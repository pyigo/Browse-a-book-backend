package com.pauyigo.capstone.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pauyigo.capstone.exceptions.EmailExistsException;
import com.pauyigo.capstone.exceptions.InvalidCredentialsException;
import com.pauyigo.capstone.exceptions.ResourceNotFoundException;
import com.pauyigo.capstone.dto.UserLogin;
import com.pauyigo.capstone.dto.UserToReturn;
import com.pauyigo.capstone.dto.UserUpdate;
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
		userToReturn.setEmail(user.getEmail());

		return ResponseEntity.ok(userToReturn);
	}

	@PostMapping("users")
	public ResponseEntity<UserToReturn> newUser(@RequestBody User user) {
		Optional<User> existingUser = usersRepo.findByEmail(user.getEmail());
		if (!existingUser.isEmpty()) {
			throw new EmailExistsException("An account with this email:" + user.getEmail() + " already exists ");

		}
		user = usersRepo.save(user);
		UserToReturn userToReturn = new UserToReturn();
		userToReturn.setId(user.getId());
		userToReturn.setFirstname(user.getFirstname());
		userToReturn.setLastname(user.getLastname());
		userToReturn.setEmail(user.getEmail());

		return ResponseEntity.ok(userToReturn);
	}

	@PostMapping("users/login")
	public ResponseEntity<UserToReturn> login(@RequestBody @Validated UserLogin userLogin) {
		User user = usersRepo.findByEmail(userLogin.getEmail())
				.orElseThrow(() -> new InvalidCredentialsException("Please enter valid credentials"));

		if (!user.getPassword().equals(userLogin.getPassword())) {
			throw new InvalidCredentialsException("Please enter valid credentials");
		}

		UserToReturn userToReturn = new UserToReturn();
		userToReturn.setId(user.getId());
		userToReturn.setFirstname(user.getFirstname());
		userToReturn.setLastname(user.getLastname());
		userToReturn.setEmail(user.getEmail());

		return ResponseEntity.ok(userToReturn);
	}

//	creating delete method
	@DeleteMapping("users/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id) {
		User user = usersRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found."));
		

		usersRepo.deleteById(id);
		return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
	}

//	creating update method
	@PutMapping("users/{id}")
	public ResponseEntity<UserToReturn> updateUser(@PathVariable int id, @RequestBody User userToUpdate){
		User userFound = usersRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found."));	
		
		
		userFound.setFirstname(userToUpdate.getFirstname());
		userFound.setLastname(userToUpdate.getLastname());
		
		User updatedUser = usersRepo.save(userFound);
		
		UserToReturn userToReturn = new UserToReturn();
		userToReturn.setId(updatedUser.getId());
		userToReturn.setFirstname(updatedUser.getFirstname());
		userToReturn.setLastname(updatedUser.getLastname());
		userToReturn.setEmail(updatedUser.getEmail());

		return  ResponseEntity.ok(userToReturn);
	}
	
	
}
