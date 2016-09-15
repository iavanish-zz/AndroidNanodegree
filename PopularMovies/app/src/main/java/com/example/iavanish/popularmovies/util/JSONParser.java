package com.example.iavanish.popularmovies.util;

import com.example.iavanish.popularmovies.entities.Movie;
import com.example.iavanish.popularmovies.entities.MoviesList;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by iavanish on 16/9/16.
 */
public class JSONParser {

    public MoviesList getMovies(MoviesList movies, String response) {
        try {
            JSONObject json_object = new JSONObject(response);
            JSONArray json_array = json_object.getJSONArray("results");
            int n = json_array.length();
            for(int i = 0; i < n; i++) {
                JSONObject tempJSON = json_array.getJSONObject(i);
                long id = tempJSON.getLong("id");
                String original_title = tempJSON.getString("original_title");
                String poster_path = tempJSON.getString("poster_path");
                String overview = tempJSON.getString("overview");
                String vote_average = tempJSON.getString("vote_average");
                String release_date = tempJSON.getString("release_date");
                Movie tempMovie = new Movie(id, original_title, poster_path, overview, vote_average, release_date);
                movies.movies.add(tempMovie);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return movies;
    }

}
