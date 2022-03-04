package com.pauyigo.capstone.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.pauyigo.capstone.models.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

	Optional<Users> findByUsername(String username);	
	
	Optional<Users> findByEmail(String email);

	
}
