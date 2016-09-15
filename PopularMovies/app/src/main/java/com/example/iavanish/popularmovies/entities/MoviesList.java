package com.example.iavanish.popularmovies.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iavanish on 9/8/16.
 */
public class MoviesList {

    public List<Movie> movies;
    private static MoviesList instance;

    private MoviesList() {
        movies = new ArrayList<>();
    }

    public static void resetInstance() {
        instance = null;
    }

    public static MoviesList getInstance() {
        if(instance == null) {
            instance = new MoviesList();
        }
        return instance;
    }

}
