package com.example.jpa_hibernate_springdata_console.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa_hibernate_springdata_console.model.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
	
}
