package com.example.jpa_hibernate_springdata_console.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import com.example.jpa_hibernate_springdata_console.model.base.BaseModel;


/**
 * The persistent class for the language database table.
 * 
 */
@Entity
@NamedQuery(name="Language.findAll", query="SELECT l FROM Language l")
public class Language extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="language_id")
	private byte languageId;

	private String name;
/*
	//bi-directional many-to-one association to Film
	@OneToMany(mappedBy="originalLanguage")
	private List<Film> filmsOriginalLanguage;

	//bi-directional many-to-one association to Film
	@OneToMany(mappedBy="language")
	private List<Film> films;
*/
	public Language() {
	}

	public byte getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(byte languageId) {
		this.languageId = languageId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

/*	
	public List<Film> getFilmsOriginalLanguage() {
		return this.filmsOriginalLanguage;
	}

	public void setFilmsOriginalLanguage(List<Film> filmsOriginalLanguage) {
		this.filmsOriginalLanguage = filmsOriginalLanguage;
	}

	public Film addFilmsOriginalLanguage(Film filmsOriginalLanguage) {
		getFilmsOriginalLanguage().add(filmsOriginalLanguage);
		filmsOriginalLanguage.setOriginalLanguage(this);

		return filmsOriginalLanguage;
	}

	public Film removeFilmsOriginalLanguage(Film filmsOriginalLanguage) {
		getFilmsOriginalLanguage().remove(filmsOriginalLanguage);
		filmsOriginalLanguage.setOriginalLanguage(null);

		return filmsOriginalLanguage;
	}

	public List<Film> getFilms() {
		return this.films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public Film addFilm(Film film) {
		getFilms().add(film);
		film.setLanguage(this);

		return film;
	}

	public Film removeFilm(Film film) {
		getFilms().remove(film);
		film.setLanguage(null);

		return film;
	}
*/
}