package com.example.iavanish.popularmovies.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.iavanish.popularmovies.R;
import com.example.iavanish.popularmovies.db.AccessDatabase;
import com.example.iavanish.popularmovies.entities.MoviesList;
import com.example.iavanish.popularmovies.util.ImageAdapter;
import com.example.iavanish.popularmovies.util.JSONParser;
import com.example.iavanish.popularmovies.util.NetworkConnection;

public class MainFragment extends Fragment implements ActionBar.OnNavigationListener {

    private static Context context;
    private ActionBar actionBar;
    private static MoviesList movies;

    private static GridView gridView;

    private View view;

    public static class MyFragment extends Fragment {

        public static final String ARG_SECTION_NUMBER = "";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            String url;
            MoviesList.resetInstance();
            movies = MoviesList.getInstance();
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                movies = new AccessDatabase(getActivity()).getMovies(movies);
                gridView = updateGrid(gridView, movies);
            }
            else {
                if (getArguments().getInt(ARG_SECTION_NUMBER) == 0) {
                    url = getResources().getString(R.string.popular_url) + getResources().getString(R.string.apiKey);
                } else {
                    url = getResources().getString(R.string.top_rated_url) + getResources().getString(R.string.apiKey);
                }

                RequestQueue queue = Volley.newRequestQueue(getActivity());
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        movies = new JSONParser().getMovies(movies, response);
                        gridView = updateGrid(gridView, movies);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.err.println("Something went wrong while retrieving data from theMovieDB!");
                    }
                });

                queue.add(stringRequest);
            }

            return gridView;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }
    }

    private static GridView updateGrid(GridView gridView, MoviesList movies) {
        gridView.setAdapter(new ImageAdapter(context, movies));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Bundle args = new Bundle();
                args.putInt("MovieIndex", position);
                ((OnFragmentInteractionListener) context).onFragmentInteraction(args);
            }
        });
        return gridView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(!NetworkConnection.isOnline()) {
            Toast.makeText(context, "Internet connection is not available. Use the app when the device is connected to internet", Toast.LENGTH_SHORT).show();
        }
        else {
            view = inflater.inflate(R.layout.fragment_main, container, false);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
            final String[] dropdownValues = getResources().getStringArray(R.array.dropdown);
            ArrayAdapter adapter = new ArrayAdapter(actionBar.getThemedContext(), android.R.layout.simple_spinner_item, android.R.id.text1, dropdownValues);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            actionBar.setListNavigationCallbacks(adapter, this);
        }
        return view;
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        gridView = (GridView) (view.findViewById(R.id.gridOfMovies));
        if(gridView.getParent() != null) {
            ((ViewGroup) gridView.getParent()).removeView(gridView);
        }
        Fragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putInt(MyFragment.ARG_SECTION_NUMBER, itemPosition);
        fragment.setArguments(args);
        getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        return true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        actionBar = ((ActionBarActivity)this.context).getSupportActionBar();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Bundle args);
    }

}
