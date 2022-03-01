package com.pauyigo.capstone.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="selection")
public class Selection {

private int id;
	
	public int getId() {
	return id;
}

public int getBook_id() {
	return book_id;
}
public void setBook_id(int book_id) {
	this.book_id = book_id;
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
	@Column
	private int book_id;
	@Column
	private int user_id;
}
