package com.pauyigo.capstone.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pauyigo.capstone.models.Book;


@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
	
	List<Book> findByTitle(String title);
}
