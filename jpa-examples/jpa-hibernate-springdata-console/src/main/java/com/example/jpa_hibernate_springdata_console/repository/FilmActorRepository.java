package com.example.jpa_hibernate_springdata_console.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa_hibernate_springdata_console.model.FilmActor;
import com.example.jpa_hibernate_springdata_console.model.FilmActorPK;

public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorPK> {
	
}
