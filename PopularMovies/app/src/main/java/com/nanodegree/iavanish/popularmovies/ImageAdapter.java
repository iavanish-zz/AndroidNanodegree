package com.nanodegree.iavanish.popularmovies;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by iavanish on 6/3/2016.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        if(movies == null) {
            return 0;
        }
        else {
            return movies.movies.size();
        }
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

        String posterURL = "http://image.tmdb.org/t/p/w342/" + movies.movies.get(position).getPoster_path();
        Picasso.with(mContext).load(posterURL).into(imageView);

        return imageView;
    }

    private SingletonMovieList movies = SingletonMovieList.getInstanceSecond();

}
