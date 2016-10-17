package com.codepath.flickster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flickster.R;
import com.codepath.flickster.activities.DetailsActivity;
import com.codepath.flickster.activities.TrailerActivity;
import com.codepath.flickster.models.Movie;
import com.codepath.flickster.models.YTVideos;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by aditi on 10/10/2016.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie>{

    public static class ViewHolder {
        @BindView(R.id.tvTitle) TextView title;
        @BindView(R.id.tvOverview) TextView overview;
        @BindView(R.id.ivMovieImage)ImageView ivMovieImage;
        @BindView(R.id.ivPlayButtonImage)ImageView ivButtonImage;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // get data item for position
        Movie movie = getItem(position);

        //check if existing view being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            if(getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                convertView = inflater.inflate(R.layout.item_movie, parent, false);
            else if(getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                convertView = inflater.inflate(R.layout.item_movie_land, parent, false);

            // If there is no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder(convertView);
            viewHolder.title.setTag(position);
            //cache the viewholder object inside the fresh view
            convertView.setTag(viewHolder);
        }
        else {
            // view is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder)convertView.getTag();
        }

        //populate data into the template view using data object
        viewHolder.title.setText(movie.getOriginalTitle());
        viewHolder.overview.setText(movie.getOverview());

        Picasso.with(getContext()).load(movie.getPosterPath())
                .transform(new RoundedCornersTransformation(10, 10))
                .placeholder(R.drawable.nomovieposter)
                .error(R.drawable.notfound)
                .into(viewHolder.ivMovieImage);
        Picasso.with(getContext()).load(R.drawable.playbutton)
                .resize(100, 100)
                .into(viewHolder.ivButtonImage);


        viewHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchDetailActivity(v, position);
            }
        });

        viewHolder.overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchDetailActivity(v, position);
            }
        });

        viewHolder.ivMovieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               launchTrailerActivity(v, position);
            }
        });

        viewHolder.ivButtonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchTrailerActivity(v, position);
            }
        });

        //return the completed view to render on screen
        return convertView;
    }

    public void launchDetailActivity(View v, int position){
        Movie currentMovie = getItem(position);
        Intent detailsIntent = new Intent(getContext(), DetailsActivity.class);
        detailsIntent.putExtra("poster", currentMovie.getPosterPath());
        detailsIntent.putExtra("title", currentMovie.getOriginalTitle());
        detailsIntent.putExtra("overview", currentMovie.getOverview());
        detailsIntent.putExtra("date",currentMovie.getReleaseDate());
        detailsIntent.putExtra("rating", currentMovie.getRating());

        getContext().startActivity(detailsIntent);
    }

    public void launchTrailerActivity(View v, int position){
        Movie currentMovie = getItem(position);
        String getYoutubeVideos = "https://api.themoviedb.org/3/movie/" + currentMovie.getMovieId() + "/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getYoutubeVideos, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                ArrayList<YTVideos> youtubeVideos = new ArrayList<>();
                JSONArray videosJsonResults = null;
                try {
                    videosJsonResults = response.getJSONArray("results");
                    if (videosJsonResults.length() != 0) {
                        youtubeVideos.addAll(YTVideos.fromJSONArray(videosJsonResults));
                        String videoKey = youtubeVideos.get(0).getVideoKey();
                        Intent playVideoIntent = new Intent(getContext(), TrailerActivity.class);
                        playVideoIntent.putExtra("video_id", videoKey);
                        getContext().startActivity(playVideoIntent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

}
