package com.example.iavanish.popularmovies.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iavanish.popularmovies.R;
import com.example.iavanish.popularmovies.util.MoviesList;
import com.squareup.picasso.Picasso;

public class MovieDetailsFragment extends Fragment {

    private Context context;
    private View view;

    private ImageView movieThumbnail;

    private TextView showDetails;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        movieThumbnail = (ImageView) view.findViewById(R.id.movieThumbnail);

        showDetails = (TextView) view.findViewById(R.id.showDetails);

        MoviesList movies = MoviesList.getInstance();

        Bundle bundle = getArguments();
        int movieIndex = bundle.getInt("MovieIndex");

        String posterURL = getResources().getString(R.string.posterURL) + movies.movies.get(movieIndex).getPoster_path();
        Picasso.with(context).load(posterURL).into(movieThumbnail);

        StringBuilder str = new StringBuilder("\n\n");
        str.append("Original Title: " + movies.movies.get(movieIndex).getOriginal_title() + "\n\n");
        str.append("Overview: " + movies.movies.get(movieIndex).getOverview() + "\n\n");
        str.append("Vote Average: " + movies.movies.get(movieIndex).getVote_average() + "\n\n");
        str.append("Release Date: " + movies.movies.get(movieIndex).getRelease_date() + "\n\n");

        showDetails.setText(str.toString());
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
