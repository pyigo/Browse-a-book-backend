package com.pauyigo.capstone.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.pauyigo.capstone.dto.UserToReturn;
import com.pauyigo.capstone.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	
	Optional<User> findByEmail(String email);

	
}
