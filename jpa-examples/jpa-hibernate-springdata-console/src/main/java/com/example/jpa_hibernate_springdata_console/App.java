package com.example.jpa_hibernate_springdata_console;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.jpa_hibernate_springdata_console.dto.ActorDTO;
import com.example.jpa_hibernate_springdata_console.dto.FilmInfoDTO;
import com.example.jpa_hibernate_springdata_console.model.Actor;
import com.example.jpa_hibernate_springdata_console.model.Film;
import com.example.jpa_hibernate_springdata_console.model.FilmActor;
import com.example.jpa_hibernate_springdata_console.model.FilmList;
import com.example.jpa_hibernate_springdata_console.model.Language;
import com.example.jpa_hibernate_springdata_console.model.Staff;
import com.example.jpa_hibernate_springdata_console.model.Store;
import com.example.jpa_hibernate_springdata_console.service.BusinessAnalyticService;
import com.example.jpa_hibernate_springdata_console.service.BusinessOperationService;

/**
 * Hello world!
 *
 */
public class App 
{
	private ClassPathXmlApplicationContext ctx;
	private BusinessAnalyticService baService;
	private BusinessOperationService boService;
		
	
    public static void main( String[] args )
    {
    	long tmTestJpaViewRepository = 0;
    	long tmTestJpaLazyLoadingViaModel = 0;
    	long tmTestJpaLazyLoadingViaDTO = 0;
    	long tmTestJpaCreateRepository = 0;
    	long tmTestJpaUpdateRepository = 0;
    	long tmTestJpaDeleteRepository = 0;
    	long tmTestReadStoreInfo = 0;
    	long tmTestQueryPerformance = 0;
    	
    	//float viewQueryTime, lazyQueryTime; 
    	
    	System.out.println("SpringData + Hibernate/JPA console");
    	 	
    	
    	App app = new App();
    	
    	/*
    	tmTestJpaViewRepository = app.testJpaViewRepository();
        
    	tmTestJpaLazyLoadingViaModel = app.testJpaLazyLoadingviaModel();
    	
    	tmTestJpaLazyLoadingViaDTO = app.testJpaLazyLoadingviaDTO();
    	*/
    	
    	// tmTestJpaCreateRepository = app.testJpaCreateRepository();
    	
    	// tmTestJpaUpdateRepository = app.testJpaUpdateRepository();
    	
    	// tmTestJpaDeleteRepository = app.testJpaDeleteRepository();
    	
    	//tmTestReadStoreInfo = app.testReadStoreInfo();    	
    	
    	tmTestQueryPerformance = app.testQueryPerformance();
        
        /*
		startTime = System.currentTimeMillis();
		filmService.DisplayFilmList();
		endTime = System.currentTimeMillis();
		viewQueryTime = (endTime-startTime)/1000.0f;

		startTime = System.currentTimeMillis();
        filmService.DisplayFilmInfo();
		endTime = System.currentTimeMillis();
		lazyQueryTime = (endTime-startTime)/1000.0f;
		*/
		
		System.out.format("TestJpaViewRepository Time : %.3f sec\n", tmTestJpaViewRepository/1000.0f);
		System.out.format("TestJpaLazyLoadingViaModel Time : %.3f sec\n", tmTestJpaLazyLoadingViaModel/1000.0f);
		System.out.format("TestJpaLazyLoadingViaDTO Time : %.3f sec\n", tmTestJpaLazyLoadingViaDTO/1000.0f);
		System.out.format("TestJpaCreateRepository Time : %.3f sec\n", tmTestJpaCreateRepository/1000.0f);
		System.out.format("TestJpaUpdateRepository Time : %.3f sec\n", tmTestJpaUpdateRepository/1000.0f);
		System.out.format("TestJpaDeleteRepository Time : %.3f sec\n", tmTestJpaDeleteRepository/1000.0f);
		System.out.format("TestReadStoreInfo Time : %.3f sec\n", tmTestReadStoreInfo/1000.0f);
		System.out.format("TestQueryPerformance Time : %.3f sec\n", tmTestQueryPerformance/1000.0f);
		
        System.out.println("Main End");
    }  
    
    public App() {
    	// Create Spring application context
    	ctx = new ClassPathXmlApplicationContext("classpath:/spring/spring-root.xml");
    	
    	// Create Spring application context
    	baService = ctx.getBean(BusinessAnalyticService.class);
    	boService = ctx.getBean(BusinessOperationService.class);
    }
    
    @Override
	protected void finalize() throws Throwable {
		super.finalize();
		ctx.close();
		System.out.println("finalize Call");
	}
    
    public long testQueryPerformance() {
    	long startTime, endTime;
    	
    	System.out.println("Demo: Test Query performance");
    	System.out.println("============================");
    	System.out.println("");
    	
    	startTime = System.currentTimeMillis();
    	Optional<Film> film = boService.getFilmInfo(1000);
    	endTime = System.currentTimeMillis();
    	
    	film.ifPresent( o -> {
    		System.out.format("Film Id: %d\n ", o.getFilmId());
    		System.out.format("Film Title: %s\n ", o.getTitle());
    		System.out.format("Film Description: %s\n ", o.getDescription());
    		System.out.format("Film Year Release: %s\n ", o.getReleaseYear().toString());
    		System.out.format("Film Language: %s\n ", o.getLanguage().getName());
    	});
    	
      	return endTime - startTime; 
    }
    
    
    
    
    public long testReadStoreInfo() {
        int counter1 = 0;
    	int counter2 = 0;
    	long startTime, endTime;
    
    	System.out.println("Demo: Read Store Information");
    	System.out.println("============================");
    	System.out.println("");
    	
    	startTime = System.currentTimeMillis();
    	List<Store> stores = boService.getStoreModelviaLazyLoading();
    	endTime = System.currentTimeMillis();
    	
    	System.out.println("Stores Info");
    	System.out.println("-----------");
    	for(Store store : stores) {
        	counter1++;
        	System.out.format("%5d: (%s)\n", 
        					counter1, 
        					store.getLastUpdate().toString());
        	
        	counter2 = 0;
        	for(Staff staff : store.getStaffs()) {
        		counter2++;
        		System.out.format("---%2d: %s %s\n", 
        					counter2, 
        					staff.getFirstName(),
        					staff.getLastName());
        	}
    	}
    	
    	return endTime - startTime; 
    }
    

	public long testJpaViewRepository() {
        int counter1 = 0;
    	int counter2 = 0;
    	long startTime, endTime; 
    	
    	System.out.println("Demo: Accessing DB view using JPA View Repository");
    	System.out.println("=================================================");
    	System.out.println("");
    	
    	startTime = System.currentTimeMillis();
    	List<FilmList> filmList = baService.getFilmStatistic();
    	endTime = System.currentTimeMillis();
    	
    	System.out.println("Films Info");
    	System.out.println("---------------");
    	for(FilmList film : filmList) {
        	counter1++;
        	System.out.format("%5d: %s (%s)\n", 
        					counter1, 
        					film.getTitle(), 
        					film.getDescription());
        	
        	counter2 = 0;
        	for(String actorName : film.getActors()) {
        		counter2++;
        		System.out.format("---%2d: %s\n", 
        					counter2, 
        					actorName);
        	}
    	}
    	
    	return endTime - startTime; 
    }
	
	public long testJpaLazyLoadingviaModel() {
		int counter = 0;
    	long startTime, endTime; 
    	
    	System.out.println("Demo: Accessing DB table using JPA lazy loading - via model");
    	System.out.println("===========================================================");
    	System.out.println("");
		
		startTime = System.currentTimeMillis();
		List<Film> films = boService.getFilmInfoModelviaLazyLoading();
		endTime = System.currentTimeMillis();
	    
	    System.out.println("List of Films");
		System.out.println("-------------");
		for(Film film : films) {
	    	counter++;
	    	System.out.format("--%5d: %s (%s)\n", 
	    					counter, 
	    					film.getTitle(), 
	    					film.getLanguage().getName());
	    	
	    	// iterate the Special Features
	    	System.out.println("       - SpecialFeatures");
	    	for(String s : film.getSpecialFeatures()) {
	    		System.out.format("       |-- %s\n", s);
	    	}
	    	
	    	// iterate the film actors
	    	Set<FilmActor> actors = film.getFilmActors();
	    	System.out.format("       - Actors (%d)\n", actors.size());
	    	
	    	for(FilmActor actor : actors) {
	    		System.out.format("       |-- %s %s\n", 
	    					actor.getActor().getFirstName(), 
	    					actor.getActor().getLastName());
	    	}
		}
		
		return endTime - startTime; 
	}
	
	public long testJpaLazyLoadingviaDTO() {
		int counter = 0;
    	long startTime, endTime; 
    	
    	System.out.println("Demo: Accessing DB table using JPA lazy loading - via DTO");
    	System.out.println("=========================================================");
    	System.out.println("");
		
		startTime = System.currentTimeMillis();
		List<FilmInfoDTO> films = boService.getFilmInfoDTOviaLazyLoading();
		endTime = System.currentTimeMillis();
	    
	    System.out.println("List of Films");
		System.out.println("-------------");
		for(FilmInfoDTO film : films) {
	    	counter++;
	    	System.out.format("--%5d: %s (%d)\n", 
	    					counter, 
	    					film.getTitle(), 
	    					film.getReleaseYear().getValue());
	    	
	    	// iterate the Special Features
	    	System.out.println("       - SpecialFeatures");
	    	for(String s : film.getSpecialFeatures()) {
	    		System.out.format("       |-- %s\n", s);
	    	}
	    	
	    	// iterate the film actors
	    	List<ActorDTO> actors = film.getFilmActors();
	    	System.out.format("       - Actors (%d)\n", actors.size());
	    	
	    	for(ActorDTO actor : actors) {
	    		System.out.format("       |-- %s %s\n", 
	    					actor.getFirstName(), 
	    					actor.getLastName());
	    	}
		}
		
		return endTime - startTime; 
	}	
	
	public long testJpaCreateRepository() {
    	long startTime, endTime;
    	
    	Language lang = new Language();
    	lang.setLanguageId((byte)1);
    	lang.setName("English");
    	
    	// Create a film
    	Film film = new Film();
    	film.setTitle("Spiderman");
    	film.setDescription("Spiderman Series");
    	film.setLength(120);
    	film.setRating("PG");
    	film.setReleaseYear(Year.of(2015));
    	film.setRentalDuration((byte) 7);
    	film.setRentalRate(BigDecimal.valueOf(7.50));
    	film.setReplacementCost(BigDecimal.valueOf(10.50)); 
    	film.setLanguage(lang);
    	
    	// Create 2 actors
    	List<Actor> actors = new ArrayList<Actor>();
    	actors.add(new Actor());
    	actors.get(0).setFirstName("Mary");
    	actors.get(0).setLastName("Tan");
    	actors.add(new Actor());
    	actors.get(1).setFirstName("Peter");
    	actors.get(1).setLastName("Parker");    	
    	
    	startTime = System.currentTimeMillis();
    	boService.createFilm(film, actors);
    	endTime = System.currentTimeMillis();
    	
    	System.out.format("%d - %s %s (%s)\n",
    					  film.getFilmId(),
    					  film.getTitle(),
    					  film.getDescription(),
    					  film.getReleaseYear().toString());				  
    	
    	for(FilmActor filmActor : film.getFilmActors()) {
    		System.out.format("%d - %s %s (%s)\n", 
    						  filmActor.getActor().getActorId(),
    						  filmActor.getActor().getFirstName(),
    						  filmActor.getActor().getLastName(),
    						  filmActor.getActor().getLastUpdate().toString()
    						  );    		
    	}
    	
    	return endTime - startTime; 
	}
	
	public long testJpaUpdateRepository() {
    	long startTime, endTime;
	
    	List<Film> films = boService.getFilmInfoAboveId(1000);
    	
    	System.out.println("Films before update");
    	for(Film f : films) {
    		System.out.format("%s (%s)\n", f.getTitle(), f.getDescription());
    	}
    	System.out.println("\n\n");
    	
    	// update the film description
    	for(Film f : films) {
    		f.setDescription(f.getDescription() + "-updated");    		
    	}
    	startTime = System.currentTimeMillis();
    	boService.updateFilms(films);
    	endTime = System.currentTimeMillis();
    	
    	System.out.println("Films after updated");
    	for(Film f : films) {
    		System.out.format("%s (%s)\n", f.getTitle(), f.getDescription());
    	}
    	
    	return endTime - startTime; 
	}	
	
	public long testJpaDeleteRepository() {
    	long startTime, endTime;
	
    	List<Film> films = boService.getFilmInfoAboveId(1000);
    	
    	System.out.println("Films before delete");
    	for(Film f : films) {
    		System.out.format("%s (%s)\n", f.getTitle(), f.getDescription());
    	}
    	System.out.println("\n\n");
    	
    	startTime = System.currentTimeMillis();
    	boService.deleteFilms(films);
    	films = boService.getFilmInfoAboveId(1000);
    	endTime = System.currentTimeMillis();
	
    	System.out.println("Films after delete");
    	for(Film f : films) {
    		System.out.format("%s (%s)\n", f.getTitle(), f.getDescription());
    	}
    	
    	return endTime - startTime;
	}
	
}