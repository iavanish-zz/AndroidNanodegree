package com.example.iavanish.popularmovies.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iavanish.popularmovies.R;
import com.example.iavanish.popularmovies.util.MoviesList;
import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {

    private ImageView movieThumbnail;

    private TextView showDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movieThumbnail = (ImageView) findViewById(R.id.movieThumbnail);

        showDetails = (TextView) findViewById(R.id.showDetails);

        MoviesList movies = MoviesList.getInstance();

        Intent intent = getIntent();
        int movieIndex = intent.getIntExtra("MovieIndex", 0);

        String posterURL = getResources().getString(R.string.posterURL) + movies.movies.get(movieIndex).getPoster_path();
        Picasso.with(this).load(posterURL).into(movieThumbnail);

        StringBuilder str = new StringBuilder("\n\n");
        str.append("Original Title: " + movies.movies.get(movieIndex).getOriginal_title() + "\n\n");
        str.append("Overview: " + movies.movies.get(movieIndex).getOverview() + "\n\n");
        str.append("Vote Average: " + movies.movies.get(movieIndex).getVote_average() + "\n\n");
        str.append("Release Date: " + movies.movies.get(movieIndex).getRelease_date() + "\n\n");

        showDetails.setText(str.toString());
    }
}
