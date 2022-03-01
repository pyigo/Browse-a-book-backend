package com.pauyigo.capstone.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pauyigo.capstone.models.Books;


@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {
	
	List<Books> findByTitle(String title);
}
