package com.example.jpa_hibernate_springdata_console.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.jpa_hibernate_springdata_console.model.Store;

public interface StoreRepository extends JpaRepository<Store, Byte> {
	
	@Query
	List<Store> findAll();
	
	List<Store> findByStoreIdGreaterThan(Byte storeId);
	
	@Query("SELECT DISTINCT s FROM Store s " + 
	       "JOIN FETCH s.address " + 
	       "JOIN FETCH s.manager m JOIN FETCH m.address")
	List<Store> findAllRecords();
}
