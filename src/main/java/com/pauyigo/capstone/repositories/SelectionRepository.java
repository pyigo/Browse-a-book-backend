package com.pauyigo.capstone.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pauyigo.capstone.models.Selection;

@Repository
public interface SelectionRepository extends JpaRepository<Selection, Integer>{
	
List <Selection> findbyTitle(Selection title);
}
