package com.example.sbohr002.myapplication.com.example.sbohr002.myapplication.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;


/**
 * C++ Class which will provide the data for the list view.
 */
public class MoviesController {
    ArrayList<Movie> movies = new ArrayList<>();
    HashMap<String, MovieDetails> map = new HashMap<>();

    public MoviesController() {
        for (int i = 0; i < 10; i++) {
            Movie movie = new Movie();
            movie.name = "Top Gun" + " " + i;
            movie.lastUpdated = i * 10000;
            movies.add(movie);

            MovieDetails movieDetails = new MovieDetails();
            movieDetails.name = movie.name;
            Random random = new Random();
            movieDetails.score = random.nextInt() % 10;
            movieDetails.description = "As students at the United States Navy's elite fighter weapons school compete to be best in the class, one daring young pilot learns a few things from a civilian instructor that are not taught in the classroom.";

            Actors tomCruise = new Actors();
            tomCruise.name = "Tom Cruise";
            tomCruise.age = 50;

            Actors valKilmer = new Actors();
            valKilmer.name = "Val Kilmer";
            valKilmer.age = 46;
            valKilmer.imageUrl = "https://m.media-amazon.com/images/M/MV5BMTk3ODIzMDA5Ml5BMl5BanBnXkFtZTcwNDY0NTU4Ng@@._V1_UY317_CR4,0,214,317_AL_.jpg";
            movieDetails.actors = new ArrayList<>();
            movieDetails.actors.add(tomCruise);
            movieDetails.actors.add(valKilmer);
            if (i % 2 == 0) {
                Actors timRobbins = new Actors();
                timRobbins.name = "Tim Robbins";
                timRobbins.age = 55;
                timRobbins.imageUrl = "https://m.media-amazon.com/images/M/MV5BMTI1OTYxNzAxOF5BMl5BanBnXkFtZTYwNTE5ODI4._V1_UY317_CR16,0,214,317_AL_.jpg";

                movieDetails.actors.add(timRobbins);
            } else {
                Actors jenniferConnelly = new Actors();
                jenniferConnelly.name = "Jennifer Connelly";
                jenniferConnelly.age = 39;
                jenniferConnelly.imageUrl = "https://m.media-amazon.com/images/M/MV5BOTczNTgzODYyMF5BMl5BanBnXkFtZTcwNjk4ODk4Mw@@._V1_UY317_CR12,0,214,317_AL_.jpg";

                movieDetails.actors.add(jenniferConnelly);
            }

            map.put(movie.name, movieDetails);
        }
    }

    //Returns list of movies
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    //Returns details about a specific movie
    public MovieDetails getMovieDetail(String name) {
        Iterator<Map.Entry<String, MovieDetails>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            if (pair.getKey().equals(name)) {
                return (MovieDetails) pair.getValue();
            }
        }

        return null;
    }

    public class Actors {
        private String name;
        private int age;
        private String imageUrl;

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }

    public class Movie {
        private String name;
        private int lastUpdated;

        public String getName() {
            return name;
        }

        public int getLastUpdated() {
            return lastUpdated;
        }
    }

    public class MovieDetails {
        private String name;
        private float score;
        private ArrayList<Actors> actors;
        private String description;

        public String getName() {
            return name;
        }

        public float getScore() {
            return score;
        }

        public ArrayList<Actors> getActors() {
            return actors;
        }

        public String getDescription() {
            return description;
        }
    }
}
