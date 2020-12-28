package com.example.jpa_hibernate_springdata_console.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.example.jpa_hibernate_springdata_console.dto.ActorDTO;
import com.example.jpa_hibernate_springdata_console.dto.FilmInfoDTO;
import com.example.jpa_hibernate_springdata_console.model.Actor;
import com.example.jpa_hibernate_springdata_console.model.Film;
import com.example.jpa_hibernate_springdata_console.model.FilmActor;

@Component
public class ModelMapperEx extends ModelMapper {

	public ModelMapperEx() {
		super();

		// configure explicit mappings
		configureExplicitMapping();
	}

	private void configureExplicitMapping() {
		
		Converter<Set<FilmActor>, List<ActorDTO>> setToList = new AbstractConverter<Set<FilmActor>, List<ActorDTO>>() {
			protected List<ActorDTO> convert(Set<FilmActor> source) {				
				List<ActorDTO> result = new ArrayList<ActorDTO>();
				if( source != null ) {					
					for(FilmActor actor : source) {
						ActorDTO actorDTO = map(actor.getActor(), ActorDTO.class);
						result.add(actorDTO);
					}					
				}
				return result;
			}
		};

		this.addMappings(new PropertyMap<Actor, ActorDTO>() {
			protected void configure() {
				map().setActorId(source.getActorId());
				map().setFirstName(source.getFirstName());
				map().setLastName(source.getLastName());
			}
		});

		this.addMappings(new PropertyMap<ActorDTO, Actor>() {
			protected void configure() {
				map().setActorId(source.getActorId());
				map().setFirstName(source.getFirstName());
				map().setLastName(source.getLastName());
			}
		});

		this.addMappings(new PropertyMap<Film, FilmInfoDTO>() {
			protected void configure() {
				map().setFilmId(source.getFilmId());
				map().setTitle(source.getTitle());
				map().setDescription(source.getDescription());
				map().setReleaseYear(source.getReleaseYear());
				map().setSpecialFeatures(source.getSpecialFeatures());
				using(setToList).map(source.getFilmActors()).setFilmActors(null);
			}
		});
		
		this.addMappings(new PropertyMap<FilmInfoDTO, Film>() {
			protected void configure() {
				map().setFilmId(source.getFilmId());
				map().setTitle(source.getTitle());
				map().setDescription(source.getDescription());
				map().setReleaseYear(source.getReleaseYear());
				map().setSpecialFeatures(source.getSpecialFeatures());
				skip().setFilmActors(null);
			}
		});		
	}
}
