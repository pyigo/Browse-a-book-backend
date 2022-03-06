package com.pauyigo.capstone.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pauyigo.capstone.exceptions.ResourceNotFoundException;
import com.pauyigo.capstone.models.Book;
import com.pauyigo.capstone.models.User;
import com.pauyigo.capstone.repositories.BookRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class BooksController {

	@Autowired
	private BookRepository bookRepo;

//	create get method to get list of all books in database
	@GetMapping("books")
	public List<Book> getAllBooks() {
		return bookRepo.findAll();
	}

//	create post method to add book to db
	@PostMapping("books")
	public Book addNewBook(@RequestBody Book bookToAdd) {
		return bookRepo.save(bookToAdd);
	}

//	get book by id method
	@GetMapping("books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable int id) {
		Book book = bookRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found."));
		return ResponseEntity.ok(book);
	}

//	create put method to update book using id
	@PutMapping("books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book bookToUpdate) {
		Book bookFound = bookRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found."));

		bookFound.setTitle(bookToUpdate.getTitle());
		bookFound.setDescription(bookToUpdate.getDescription());
		bookFound.setPublishedDate(bookToUpdate.getPublishedDate());
		bookFound.setAuthor(bookToUpdate.getAuthor());
		bookFound.setCategory(bookToUpdate.getCategory());

		Book updatedBook = bookRepo.save(bookFound);

		return ResponseEntity.ok(updatedBook);
	}

//	create delete method to delete book
	@DeleteMapping("books/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable int id) {
		Book book = bookRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found."));
		bookRepo.deleteById(id);
		return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
	}
}
