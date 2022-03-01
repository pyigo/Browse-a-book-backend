package com.pauyigo.capstone.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pauyigo.capstone.models.Users;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class UsersController {
	
	@Autowired
	private UsersRepository usersRepo;
	
	@GetMapping("/allusers")
	public List <Users> getAllusers(){
		return usersRepo.findAll();
	}

}
