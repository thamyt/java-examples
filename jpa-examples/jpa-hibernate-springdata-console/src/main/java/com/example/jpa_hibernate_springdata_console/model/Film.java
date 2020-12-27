package com.example.jpa_hibernate_springdata_console.model;

import java.math.BigDecimal;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.example.jpa_hibernate_springdata_console.converter.StringListConverter;
import com.example.jpa_hibernate_springdata_console.converter.YearAttributeConverter;
import com.example.jpa_hibernate_springdata_console.model.base.BaseModel;


/**
 * The persistent class for the film database table.
 * 
 */
@Entity
@NamedQuery(name="Film.findAll", query="SELECT f FROM Film f")
public class Film extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="film_id")
	private int filmId;

	@Lob
	private String description;

	private int length;

	private String rating;

	@Convert(converter=YearAttributeConverter.class)
	@Column(name="release_year")
	private Year releaseYear;

	@Column(name="rental_duration")
	private byte rentalDuration;

	@Column(name="rental_rate")
	private BigDecimal rentalRate;

	@Column(name="replacement_cost")
	private BigDecimal replacementCost;

	@Convert(converter=StringListConverter.class)
	@Column(name="special_features")
	private List<String> specialFeatures;

	private String title;

	//bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name="original_language_id")
	private Language originalLanguage;

	//bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name="language_id")
	private Language language;
	
	//bi-directional many-to-one association to FilmActor
	@OneToMany(mappedBy="film", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<FilmActor> filmActors = new HashSet<FilmActor>();

	public Film() {
	}

	public int getFilmId() {
		return this.filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Year getReleaseYear() {
		return this.releaseYear;
	}

	public void setReleaseYear(Year releaseYear) {
		this.releaseYear = releaseYear;
	}

	public byte getRentalDuration() {
		return this.rentalDuration;
	}

	public void setRentalDuration(byte rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public BigDecimal getRentalRate() {
		return this.rentalRate;
	}

	public void setRentalRate(BigDecimal rentalRate) {
		this.rentalRate = rentalRate;
	}

	public BigDecimal getReplacementCost() {
		return this.replacementCost;
	}

	public void setReplacementCost(BigDecimal replacementCost) {
		this.replacementCost = replacementCost;
	}

	public List<String> getSpecialFeatures() {
		return this.specialFeatures;
	}

	public void setSpecialFeatures(List<String> specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Language getOriginalLanguage() {
		return this.originalLanguage;
	}

	public void setOriginalLanguage(Language originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Set<FilmActor> getFilmActors() {
		return this.filmActors;
	}

	public void setFilmActors(Set<FilmActor> filmActors) {
		this.filmActors = filmActors;
	}

	public FilmActor addFilmActor(FilmActor filmActor) {
		getFilmActors().add(filmActor);
		filmActor.setFilm(this);

		return filmActor;
	}

	public FilmActor removeFilmActor(FilmActor filmActor) {
		getFilmActors().remove(filmActor);
		filmActor.setFilm(null);

		return filmActor;
	}

}