package com.skilldistillery.filmquery.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Film {
	    private int id;
	    private String title;
	    private String description;
	    private String releaseYear;
	    private int languageID;
	    private int rentalDuration;
	    private double rentalRate;
	    private int length;
	    private double replacementCost;
	    private List<Film> films = new ArrayList<>();
	  
	    @Override
		public int hashCode() {
			return Objects.hash(description, id, languageID, length, rating, releaseYear, rentalDuration, rentalRate,
					replacementCost, specialFeatures, title);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Film other = (Film) obj;
			return Objects.equals(description, other.description) && id == other.id && languageID == other.languageID
					&& length == other.length
					&& Double.doubleToLongBits(rating) == Double.doubleToLongBits(other.rating)
					&& Objects.equals(releaseYear, other.releaseYear) && rentalDuration == other.rentalDuration
					&& Double.doubleToLongBits(rentalRate) == Double.doubleToLongBits(other.rentalRate)
					&& Double.doubleToLongBits(replacementCost) == Double.doubleToLongBits(other.replacementCost)
					&& Objects.equals(specialFeatures, other.specialFeatures) && Objects.equals(title, other.title);
		}
		
		
		
		@Override
		public String toString() {
			return "Film id=" + id + ", title=" + title + ", description=" + description + ", releaseYear="
					+ releaseYear + ", languageID=" + languageID + ", rentalDuration=" + rentalDuration
					+ ", rentalRate=" + rentalRate + ", length=" + length + ", replacementCost=" + replacementCost
					+ ", rating=" + rating + ", specialFeatures=" + specialFeatures + ". ";
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
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
		public String getReleaseYear() {
			return releaseYear;
		}
		public void setReleaseYear(String releaseYear) {
			this.releaseYear = releaseYear;
		}
		public int getLanguageID() {
			return languageID;
		}
		public void setLanguageID(int languageID) {
			this.languageID = languageID;
		}
		public int getRentalDuration() {
			return rentalDuration;
		}
		public void setRentalDuration(int rentalDuration) {
			this.rentalDuration = rentalDuration;
		}
		public double getRentalRate() {
			return rentalRate;
		}
		public void setRentalRate(double rentalRate) {
			this.rentalRate = rentalRate;
		}
		public int getLength() {
			return length;
		}
		public void setLength(int length) {
			this.length = length;
		}
		public double getReplacementCost() {
			return replacementCost;
		}
		public void setReplacementCost(double replacementCost) {
			this.replacementCost = replacementCost;
		}
		public double getRating() {
			return rating;
		}
		public void setRating(double rating) {
			this.rating = rating;
		}
		public String getSpecialFeatures() {
			return specialFeatures;
		}
		public void setSpecialFeatures(String specialFeatures) {
			this.specialFeatures = specialFeatures;
		}
		public List<Film> getFilms() {
			return films;
		}
		public void setFilms(List<Film> films) {
			this.films = films;
		}

		private double rating;
	    private String specialFeatures;
	}

