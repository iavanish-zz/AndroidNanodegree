package com.nanodegree.iavanish.popularmovies;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iavanish on 6/29/2016.
 */
public class SingletonMovieList {

    public List <Movie> movies = new ArrayList <Movie>();
    private static SingletonMovieList instance;

    private SingletonMovieList() {
    }

    public static SingletonMovieList getInstanceFirst() {
        if(instance == null) {
            instance = new SingletonMovieList();
        }
        return instance;
    }

    public static SingletonMovieList getInstanceSecond() {
        return instance;
    }
}
