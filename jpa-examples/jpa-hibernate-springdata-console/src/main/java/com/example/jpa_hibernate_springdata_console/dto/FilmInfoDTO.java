package com.example.jpa_hibernate_springdata_console.dto;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class FilmInfoDTO {
	private int filmId;
	private String title;
	private String description;
	private Year releaseYear;
	private List<String> specialFeatures;
	private List<ActorDTO> filmActors;
	
	public FilmInfoDTO() {
		this.specialFeatures = new ArrayList<String>();
		this.filmActors = new ArrayList<ActorDTO>();
	}
	
	public int getFilmId() {
		return filmId;
	}
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Year getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(Year releaseYear) {
		this.releaseYear = releaseYear;
	}
	public List<String> getSpecialFeatures() {
		return specialFeatures;
	}
	public void setSpecialFeatures(List<String> specialFeatures) {
		this.specialFeatures = specialFeatures;
	}	
	public List<ActorDTO> getFilmActors() {
		return filmActors;
	}
	public void setFilmActors(List<ActorDTO> filmActors) {
		this.filmActors = filmActors;
	}
}