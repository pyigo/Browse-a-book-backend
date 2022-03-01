package com.pauyigo.capstone.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.pauyigo.capstone.models.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

	
	List<Users> findByFirstname(String firstname);
}
