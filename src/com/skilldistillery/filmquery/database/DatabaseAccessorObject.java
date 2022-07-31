package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid";
	String user = "student";
	String pass = "student";

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT film.*, language.name FROM film JOIN language ON film.language_id = language.id WHERE film.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguageID(rs.getInt("language_id"));
				film.setLanguageType(rs.getString("language.name"));
				film.setRating(rs.getString("rating"));
			}
			rs.close();
			stmt.close();
			conn.close();
			return film;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT * FROM film WHERE id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actorByFilmId = new ArrayList<>();
		Actor actorID = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT * " + " FROM film JOIN film_actor ON film.id = film_actor.film_id WHERE actor_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			System.out.println(stmt);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				actorID = new Actor();
				actorID.setId(rs.getInt("id"));
				actorID.setFirstName(rs.getString("first_name"));
				actorID.setLastName(rs.getString("last_name"));

			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (List<Actor>) actorID;
	}

		public List<Film> findFilmByKeyWord(String keyword) {
			Film film = null ;
			List<Film> films = new ArrayList<>();
			try {
				Connection conn = DriverManager.getConnection(URL, user, pass);
				
						
				String sql = "SELECT * FROM film f JOIN film_category fc ON f.id = fc.film_id JOIN category c ON fc.category_id = c.id JOIN language l ON f.language_id = l.id WHERE title LIKE ? OR description LIKE ?";

				PreparedStatement stmt = conn.prepareStatement(sql);
				keyword = "%" + keyword + "%";
				stmt.setString(1, keyword);
				stmt.setString(2, keyword);

				
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					film = new Film();
					film.setId(rs.getInt("id"));
					film.setTitle(rs.getString("title"));
					film.setDescription(rs.getString("description"));
					film.setReleaseYear(rs.getInt("release_year"));
					film.setLanguageID(rs.getInt("language_id"));
					film.setLanguageType(rs.getString("l.name"));
					film.setRating(rs.getString("rating"));
					films.add(film);
					
					
			

				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return films;

		}

}

