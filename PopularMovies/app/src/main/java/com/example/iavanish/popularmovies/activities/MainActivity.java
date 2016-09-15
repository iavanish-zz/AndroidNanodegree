package com.example.iavanish.popularmovies.activities;

import android.net.Uri;
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
    public void onFragmentInteraction(Uri uri) {
    }

}
