package com.example.jpa_hibernate_springdata_console.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa_hibernate_springdata_console.dto.FilmInfoDTO;
import com.example.jpa_hibernate_springdata_console.mapper.ModelMapperEx;
import com.example.jpa_hibernate_springdata_console.model.Actor;
import com.example.jpa_hibernate_springdata_console.model.Film;
import com.example.jpa_hibernate_springdata_console.model.FilmActor;
import com.example.jpa_hibernate_springdata_console.model.FilmActorPK;
import com.example.jpa_hibernate_springdata_console.model.Staff;
import com.example.jpa_hibernate_springdata_console.model.Store;
import com.example.jpa_hibernate_springdata_console.repository.ActorRepository;
import com.example.jpa_hibernate_springdata_console.repository.FilmActorRepository;
import com.example.jpa_hibernate_springdata_console.repository.FilmRepository;
import com.example.jpa_hibernate_springdata_console.repository.StoreRepository;

@Service
public class BusinessOperationService {

	@Autowired
	private ActorRepository actorRepository;
	
	@Autowired
	private FilmRepository filmRepository;
	
	@Autowired
	private FilmActorRepository filmActorRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private ModelMapperEx modelMapperEx;
	
	@Transactional
	public void createFilm(Film film, List<Actor> actors) {
		
		// save the actors
		actors = actorRepository.saveAll(actors);
		
		// save the film
		film = filmRepository.save(film);
		
		// set the actors in the film
		//Set<FilmActor> filmActors = new HashSet<FilmActor>();
		for(Actor actor : actors) {
			FilmActorPK filmActorPK = new FilmActorPK();
			filmActorPK.setActorId(actor.getActorId());
			filmActorPK.setFilmId(film.getFilmId());
			
			FilmActor filmActor = new FilmActor();
			filmActor.setId(filmActorPK);
			filmActor.setActor(actor);
			filmActor.setFilm(film);			
			filmActor = filmActorRepository.save(filmActor);
			film.getFilmActors().add(filmActor);
		}
	}
	
	@Transactional
	public void updateFilms(List<Film> films) {
		
		// save the film
		films = filmRepository.saveAll(films);
	}
	
	@Transactional
	public void deleteFilms(List<Film> films) {
		
		// save the film
		filmRepository.deleteAll(films);
	}
	
	@Transactional(readOnly=true)
	public List<Store> getStoreModelviaLazyLoading() {
		List<Store> stores = storeRepository.findAll();
		
		for(Store store : stores) {
			// call the getter to load the data to cache
			for(Staff staff : store.getStaffs()) {
				// do nothing here
	    	}
		}
		
		return stores;
	}
	
	@Transactional(readOnly=true)
	public Optional<Film> getFilmInfo(int id) {
	
		//List<Film> films = filmRepository.findFilmAboveId(id);	

		/*
		System.out.println("Get Store using findById");
		Optional<Store> store = storeRepository.findById((byte) 1);
		store.ifPresent( o -> {
			System.out.format("Store id = %d", o.getStoreId());
		});
		
		System.out.println("\n\n\n");
		*/
		System.out.println("Get All Store using findAll()");
		List<Store> stores = storeRepository.findAll();
		for(Store s : stores) {
			System.out.format("Store id = %d\n", s.getStoreId());
			System.out.format("- Address = %s\n", s.getAddress().getAddress());
			System.out.format("- Manager = %s %s\n", s.getManager().getFirstName(), s.getManager().getLastName());
			System.out.format("  - Address = %s\n", s.getManager().getAddress().getAddress());
			
			System.out.println("------------------");	
		}
		
		System.out.println("\n\n\n");
		
/*		
		System.out.println("Get All Store using findByStoreIdGreaterThan()");
		List<Store> stores = storeRepository.findByStoreIdGreaterThan((byte) 0);
		
		System.out.format("Stores size = %d\n", stores.size());
		for(Store s : stores) {
			System.out.format("Store id = %d\n", s.getStoreId());
			System.out.format("Manager = %s %s\n", s.getManager().getFirstName(), s.getManager().getLastName());
			System.out.format("Address = %s\n", s.getAddress().getAddress());
			System.out.println("------------------");	
		}
		
		System.out.println("Get All Film using findAll()");
		List<Film> films = filmRepository.findAll();
*/		
		System.out.println("Get Film using findById()");
		return filmRepository.findById(id);
	}
	
	
	
	@Transactional(readOnly=true)
	public List<Film> getFilmInfoAboveId(int id) {
	
		//List<Film> films = filmRepository.findFilmAboveId(id);
		List<Film> films = filmRepository.findByFilmIdGreaterThan(id);
		
		for(Film film : films) {
			// call the getter to load the data to cache
			for(FilmActor actor : film.getFilmActors()) {
				// do nothing here
	    	}
		}
	
		return films;
	}
	
	@Transactional(readOnly=true)
	public List<Film> getFilmInfoModelviaLazyLoading() {
		
		// Get the list of films
		List<Film> films = filmRepository.findAll();
				
		// Do some business logic here
		
		
		// Because the filmActors is configured as lazy loading
		// we need to iterate each film, call getFilmActors() methods
		// and iterate the FilmActors list inside the service class. 
		// If not, the caller calling the getFilmActors() method
		// outside the service class will get "could not initialize proxy - no Session" error
		for(Film film : films) {
			// call the getter to load the data to cache
			for(FilmActor actor : film.getFilmActors()) {
				// do nothing here
	    	}
		}
				
		// return the data 
		return films;
	}
	
	@Transactional(readOnly=true)
	public List<FilmInfoDTO> getFilmInfoDTOviaLazyLoading() {
		// Get the list of films
		List<Film> films = filmRepository.findAll();
						
		// Method 1: Do some business logic here
		List<FilmInfoDTO> result = new ArrayList<FilmInfoDTO>();
		for(Film film : films) {
			// map film to filmInfoDTO
			FilmInfoDTO filmInfoDTO = modelMapperEx.map(film, FilmInfoDTO.class);
			result.add(filmInfoDTO);
			
			/*
			 * This section for mapping the set property and list property is no longer needed 
			 * The converter logic is added in the in modelMapperEx  
			 
			// iterate the actor list
			List<ActorDTO> actorsDTO = new ArrayList<ActorDTO>();
			filmInfoDTO.setFilmActors(actorsDTO);
			for(FilmActor actor : film.getFilmActors()) {
				ActorDTO actorDTO = modelMapperEx.map(actor.getActor(), ActorDTO.class);
				actorsDTO.add(actorDTO);
	    	}
	    	*/
		}	
				
		// return the data 
		return result;
	}
}
