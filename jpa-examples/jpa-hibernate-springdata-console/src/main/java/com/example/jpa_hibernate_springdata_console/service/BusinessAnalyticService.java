package com.example.jpa_hibernate_springdata_console.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa_hibernate_springdata_console.model.FilmList;
import com.example.jpa_hibernate_springdata_console.repository.FilmListRepository;

@Service
public class BusinessAnalyticService {

	@Autowired
	private FilmListRepository filmListRepository;
	
	@Transactional(readOnly=true)
	public List<FilmList> getFilmStatistic() {
		
		// Get the list of films
		List<FilmList> filmList = filmListRepository.findAll();
		
		// Do some business logic here
		//
		
		// return the data 
		return filmList;
	}
}
