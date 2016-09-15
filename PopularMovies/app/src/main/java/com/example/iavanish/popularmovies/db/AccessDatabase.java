package com.example.iavanish.popularmovies.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.iavanish.popularmovies.R;
import com.example.iavanish.popularmovies.entities.Movie;
import com.example.iavanish.popularmovies.entities.MoviesList;

/**
 * Created by iavanish on 16/9/16.
 */
public class AccessDatabase {

    SQLiteDatabase database;

    public AccessDatabase(Context context) {
        database = context.openOrCreateDatabase(context.getResources().getString(R.string.database), context.MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS Movie(id integer primary key, original_title varchar, poster_path varchar," +
                "overview varchar, vote_average varchar, release_date varchar);");
    }

    public void insertMovie(Movie movie) {
        if(!isMoviePresentInDB(movie)) {
            database.execSQL("INSERT INTO Movie VALUES('" + movie.getId() + "','" + movie.getOriginal_title() + "','" + movie.getPoster_path()
                    + "','" + movie.getOverview() + "','" + movie.getVote_average() + "','" + movie.getRelease_date() + "');");
        }
    }

    public void deleteMovie(Movie movie) {
        if(isMoviePresentInDB(movie)) {
            database.execSQL("DELETE FROM Movie where id = '" + movie.getId() + "';");
        }
    }

    public MoviesList getMovies(MoviesList movies) {
        Cursor cursor = database.rawQuery("select * from Movie", null);
        if(cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(0);
                String original_title = cursor.getString(1);
                String poster_path = cursor.getString(2);
                String overview = cursor.getString(3);
                String vote_average = cursor.getString(4);
                String release_date = cursor.getString(5);
                Movie movie = new Movie(id, original_title, poster_path, overview, vote_average, release_date);
                movies.movies.add(movie);
            }   while(cursor.moveToNext());
        }
        return movies;
    }

    public boolean isMoviePresentInDB(Movie movie) {
        Cursor cursor = database.rawQuery("select * from Movie where id = '" + String.valueOf(movie.getId()) + "'", null);
        if(cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }
    }

}
