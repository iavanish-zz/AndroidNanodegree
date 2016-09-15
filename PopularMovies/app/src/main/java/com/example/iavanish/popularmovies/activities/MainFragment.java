package com.example.iavanish.popularmovies.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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
import com.example.iavanish.popularmovies.entities.Movie;
import com.example.iavanish.popularmovies.util.ImageAdapter;
import com.example.iavanish.popularmovies.util.MoviesList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class MainFragment extends Fragment implements ActionBar.OnNavigationListener {

    private Context context;

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
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 0) {
                url = getResources().getString(R.string.popular_url) + getResources().getString(R.string.apiKey);
            }
            else {
                url = getResources().getString(R.string.top_rated_url) + getResources().getString(R.string.apiKey);
            }

            RequestQueue queue = Volley.newRequestQueue(getActivity());
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject json_object = new JSONObject(response);
                        JSONArray json_array = json_object.getJSONArray("results");
                        int n = json_array.length();
                        for(int i = 0; i < n; i++) {
                            JSONObject tempJSON = json_array.getJSONObject(i);
                            String original_title = tempJSON.getString("original_title");
                            String poster_path = tempJSON.getString("poster_path");
                            String overview = tempJSON.getString("overview");
                            String vote_average = tempJSON.getString("vote_average");
                            String release_date = tempJSON.getString("release_date");
                            Movie tempMovie = new Movie(original_title, poster_path, overview, vote_average, release_date);
                            movies.movies.add(tempMovie);
                        }
                        Log.d("Movie response", response);

                        gridView.setAdapter(new ImageAdapter(getActivity(), movies));
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> parent, View v,
                                                    int position, long id) {
                                Bundle args = new Bundle();
                                args.putInt("MovieIndex", position);
                                ((OnFragmentInteractionListener) getActivity()).onFragmentInteraction(args);
                            }
                        });
                    }
                    catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.err.println("That didn't work!");
                }
            });

            queue.add(stringRequest);

            return gridView;
        }
    }

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_main, container, false);

        if(!isOnline()) {
            Toast.makeText(context, "Internet connection is not available. Use the app when the device is connected to internet", Toast.LENGTH_SHORT).show();
        }

        else {
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

    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);

        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        actionBar = ((ActionBarActivity)this.context).getSupportActionBar();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Bundle args);
    }
}
