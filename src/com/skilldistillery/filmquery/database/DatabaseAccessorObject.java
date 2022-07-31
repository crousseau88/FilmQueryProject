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
			String sql = "SELECT * FROM film WHERE id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet filmResult = stmt.executeQuery();

			if (filmResult.next()) {
				film = new Film();
				film.setId(filmResult.getInt("id"));
				film.setTitle(filmResult.getString("title"));
				film.setDescription(filmResult.getString("description"));
				film.setReleaseYear(filmResult.getInt("release_year"));
				film.setLanguageID(filmResult.getInt("language_id"));
				film.setRating(filmResult.getString("rating"));
			}
			filmResult.close();
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

			ResultSet actorResult = stmt.executeQuery();

			if (actorResult.next()) {
				actor = new Actor();
				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));
			}
			actorResult.close();
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
				
						
				String sql = "SELECT * FROM film WHERE title LIKE ? OR description LIKE ?";

				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%" + keyword + "%");
				stmt.setString(2, "%" + keyword + "%");
				ResultSet keywordResult = stmt.executeQuery();

				while (keywordResult.next()) {
					film = new Film();
					film.setId(keywordResult.getInt("id"));
					film.setTitle(keywordResult.getString("title"));
					film.setDescription(keywordResult.getString("description"));
					film.setReleaseYear(keywordResult.getInt("release_year"));
					film.setLanguageID(keywordResult.getInt("language_id"));
					film.setRating(keywordResult.getString("rating"));
					films.add(film);
					
					
			

				}
				keywordResult.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return films;

		}

}

