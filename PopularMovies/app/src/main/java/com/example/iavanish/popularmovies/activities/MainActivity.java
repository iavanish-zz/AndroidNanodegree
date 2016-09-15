package com.example.iavanish.popularmovies.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

import com.example.iavanish.popularmovies.R;

public class MainActivity extends ActionBarActivity implements MainFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.left_container, mainFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Bundle args) {
        if (findViewById(R.id.right_container) != null) {
            Fragment movieDetailsFragment = new MovieDetailsFragment();
            movieDetailsFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.right_container, movieDetailsFragment).commit();
        }
        else {
            Intent intent = new Intent(this, MovieDetails.class);
            intent.putExtras(args);
            startActivity(intent);
        }
    }

}
