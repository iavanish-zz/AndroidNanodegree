package com.example.iavanish.popularmovies.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.iavanish.popularmovies.R;

public class MovieDetails extends AppCompatActivity {

    private MovieDetailsFragment movieDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("movieDetailsFragment");
        if(fragment != null) {
            movieDetailsFragment = (MovieDetailsFragment) fragment;
        }

        else {
            Intent intent = getIntent();
            Bundle args = intent.getExtras();
            movieDetailsFragment = new MovieDetailsFragment();
            movieDetailsFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction().replace(R.id.movie_details_container, movieDetailsFragment, "movieDetailsFragment").commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Nothing to save
    }

}
