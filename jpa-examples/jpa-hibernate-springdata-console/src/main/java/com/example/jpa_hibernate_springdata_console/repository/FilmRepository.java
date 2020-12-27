package com.example.jpa_hibernate_springdata_console.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.jpa_hibernate_springdata_console.model.Film;

public interface FilmRepository extends JpaRepository<Film, Integer> {
	
	@Query("SELECT f FROM Film f WHERE f.filmId > ?1")
	List<Film> findFilmAboveId(Integer filmId);
	
	List<Film> findByFilmIdGreaterThan(Integer filmId);
}
