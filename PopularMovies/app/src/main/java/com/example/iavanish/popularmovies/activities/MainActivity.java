package com.example.iavanish.popularmovies.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import com.example.iavanish.popularmovies.R;

public class MainActivity extends ActionBarActivity implements MainFragment.OnFragmentInteractionListener {

    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("mainFragment");
        if(fragment != null) {
            // ensures app doesn't crash on device rotation by re-assigning the previous instance of fragment
            mainFragment = (MainFragment) fragment;
        }
        else {
            mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, mainFragment, "mainFragment").commit();
        }
    }

    @Override
    public void onFragmentInteraction(Bundle args) {
        if (findViewById(R.id.movie_details_container) != null) {
            Fragment movieDetailsFragment = new MovieDetailsFragment();
            movieDetailsFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.movie_details_container, movieDetailsFragment).commit();
        }
        else {
            Intent intent = new Intent(this, MovieDetails.class);
            intent.putExtras(args);
            startActivity(intent);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

}
