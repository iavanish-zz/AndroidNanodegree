package com.nanodegree.iavanish.popularmovies;

import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://api.themoviedb.org/3/movie/popular?api_key=";
        new NetworkOperation().execute(url);

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(MainActivity.this, MovieDetails.class);
                intent.putExtra("MovieIndex", position);
                startActivity(intent);
            }
        });

    }

    public static String getURL(String url){
        InputStream is = null;
        String res = "";
        try {
            HttpClient hClient = new DefaultHttpClient();
            HttpResponse hResponse = hClient.execute(new HttpGet(url));
            is = hResponse.getEntity().getContent();
            if(is != null)
                res = convertInputStreamToString(is);
            else
                res = "Something went wrong!";
        }
        catch(Exception exception) {
            Log.d("InputStream", exception.getLocalizedMessage());
        }
        return res;
    }

    private static String convertInputStreamToString(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String l = "";
        String res = "";
        try {
            while((l = br.readLine()) != null)
                res += l;
            is.close();
        }
        catch(IOException exception) {
            System.out.println("IO Exception occured!");
        }
        return res;
    }

    private class NetworkOperation extends AsyncTask <String, Void, String> {

        AndroidHttpClient newClient = AndroidHttpClient.newInstance("");

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            return getURL(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            if (newClient != null)
                newClient.close();
            try {
                SingletonMovieList movies = SingletonMovieList.getInstanceFirst();
                JSONObject json_object = new JSONObject(result);
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
                Log.d("Movie response", result);
            }
            catch (Exception exception) {
                // TODO Auto-generated catch block
                exception.printStackTrace();
            }
        }

    }

}