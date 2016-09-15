package com.example.iavanish.popularmovies.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iavanish.popularmovies.R;
import com.example.iavanish.popularmovies.db.AccessDatabase;
import com.example.iavanish.popularmovies.entities.MoviesList;
import com.squareup.picasso.Picasso;

public class MovieDetailsFragment extends Fragment {

    private Context context;
    private View view;
    private ImageView movieThumbnail;
    private CheckBox favourite;
    private TextView showDetails;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        movieThumbnail = (ImageView) view.findViewById(R.id.movieThumbnail);
        favourite = (CheckBox) view.findViewById(R.id.favourite);
        showDetails = (TextView) view.findViewById(R.id.showDetails);

        final MoviesList movies = MoviesList.getInstance();

        Bundle bundle = getArguments();
        final int movieIndex = bundle.getInt("MovieIndex");

        movieThumbnail.setScaleType(ImageView.ScaleType.FIT_XY);
        String posterURL = getResources().getString(R.string.bigPosterURL) + movies.movies.get(movieIndex).getPoster_path();
        Picasso.with(context).load(posterURL).into(movieThumbnail);

        StringBuilder str = new StringBuilder("\n\n");
        str.append("Original Title: " + movies.movies.get(movieIndex).getOriginal_title() + "\n\n");
        str.append("Overview: " + movies.movies.get(movieIndex).getOverview() + "\n\n");
        str.append("Vote Average: " + movies.movies.get(movieIndex).getVote_average() + "\n\n");
        str.append("Release Date: " + movies.movies.get(movieIndex).getRelease_date() + "\n\n");

        showDetails.setText(str.toString());

        if(new AccessDatabase(context).isMoviePresentInDB(movies.movies.get(movieIndex))) {
            favourite.setChecked(true);
        }

        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    new AccessDatabase(context).insertMovie(movies.movies.get(movieIndex));
                }
                else {
                    new AccessDatabase(context).deleteMovie(movies.movies.get(movieIndex));
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

}
