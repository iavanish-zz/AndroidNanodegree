package com.example.iavanish.popularmovies.entities;

/**
 * Created by iavanish on 9/8/16.
 */
public class Movie {

    private String original_title;
    private String poster_path;
    private String overview;
    private String vote_average;
    private String release_date;

    public Movie(String original_title, String poster_path, String overview, String vote_average, String release_date) {
        this.original_title = original_title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.vote_average = vote_average;
        this.release_date = release_date;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

}
