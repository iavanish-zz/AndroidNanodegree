package com.example.iavanish.popularmovies.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.iavanish.popularmovies.R;

public class MovieDetails extends AppCompatActivity implements MovieDetailsFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        Bundle args = intent.getExtras();
        MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
        movieDetailsFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.right_container, movieDetailsFragment)
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
