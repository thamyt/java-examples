package com.example.jpa_hibernate_springdata_console.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa_hibernate_springdata_console.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Byte> {
	
}
