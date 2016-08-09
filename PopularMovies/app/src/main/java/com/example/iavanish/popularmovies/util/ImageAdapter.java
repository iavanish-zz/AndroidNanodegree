package com.example.iavanish.popularmovies.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by iavanish on 10/8/16.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private MoviesList movies;

    public ImageAdapter(Context c, MoviesList movies) {
        mContext = c;
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

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
        } else {
            imageView = (ImageView) convertView;
        }

        String posterURL = "http://image.tmdb.org/t/p/w500/" + movies.movies.get(position).getPoster_path();
        //String posterURL = "http://image.tmdb.org/t/p/w342/mFb0ygcue4ITixDkdr7wm1Tdarx.jpg";
        Picasso.with(mContext).load(posterURL).into(imageView);

        return imageView;
    }

}
