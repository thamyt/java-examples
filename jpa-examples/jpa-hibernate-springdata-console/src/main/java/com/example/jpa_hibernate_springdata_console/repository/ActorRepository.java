package com.example.jpa_hibernate_springdata_console.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa_hibernate_springdata_console.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
	
}
