package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;
public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
		// app.test();
		app.launch();

	}

	private void test() {
		Film film = db.findFilmById(1);
		System.out.println(film);
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		FilmQueryApp app = new FilmQueryApp();
		
		boolean showMenu = true;
		while (showMenu) {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("@                 Menu                  @");
			System.out.println("@ 1. Look up a film by its ID.          @");
			System.out.println("@ 2. Look up a film by a search keyword @");
			System.out.println("@ 3. Quit                               @");
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

			int userInput = 0;
			userInput = input.nextInt();

			if (userInput < 4) {
				switch (userInput) {
				case 1:
					System.out.println("Please enter a film ID #");
					int id = input.nextInt();
					Film film = db.findFilmById(id);
					
					
					if(film != null) {
						System.out.println("Film ID: " + film.getId());
						System.out.println("Cast: " + db.findActorsByFilmId(id));
						System.out.println("Title: " + film.getTitle());
						System.out.println("Description: " + film.getDescription());
						System.out.println("Language: " + film.getLanguageType());
						System.out.println("Release Year: " + film.getReleaseYear());
						System.out.println("Rating: " + film.getRating());
					
					}
					else {
						System.out.println("There is not a film associated with that ID.");
						System.out.println("Please make another selection.");
					}
					break;
				case 2:
					
					System.out.println("Please enter a search keyword. ");
					String keyword = input.next();
					List<Film> film3 =  db.findFilmByKeyWord(keyword);
					if(film3.size() == 0) {
						System.out.println("There are no films with that keyword.");
						System.out.println("Please make another selection");
					}
					else {
						for (Film film2 : film3) {
						System.out.println(film2.films());
						}
					}
					break;
				case 3:
					System.out.println("Goodbye!");
					System.exit(0);
					break;

				}
		
			}
		}
	}
}
