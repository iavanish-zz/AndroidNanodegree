package com.example.iavanish.popularmovies.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.iavanish.popularmovies.R;
import com.example.iavanish.popularmovies.entities.MoviesList;
import com.squareup.picasso.Picasso;

/**
 * Created by iavanish on 10/8/16.
 */
public class ImageAdapter extends BaseAdapter {

    private Context context;
    private MoviesList movies;

    public ImageAdapter(Context context, MoviesList movies) {
        this.context = context;
        this.movies = movies;
    }

    public int getCount() {
        return movies.movies.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
        }
        else {
            imageView = (ImageView) convertView;
        }
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        String posterURL = context.getResources().getString(R.string.posterURL) + movies.movies.get(position).getPoster_path();
        Picasso.with(context).load(posterURL).into(imageView);

        return imageView;
    }

}
