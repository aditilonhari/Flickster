package com.codepath.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flickster.R;
import com.codepath.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by aditi on 10/10/2016.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie>{

    public static class ViewHolder {
        TextView title;
        TextView overview;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get data item for position
        Movie movie = getItem(position);

        //check if existing view being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if(convertView == null){
            // If there is no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            if(getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                convertView = inflater.inflate(R.layout.item_movie, parent, false);
            else if(getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                convertView = inflater.inflate(R.layout.item_movie_land, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.overview = (TextView) convertView.findViewById(R.id.tvOverview);

            //cache the viewholder object inside the fresh view
            convertView.setTag(viewHolder);
        }
        else {
            // view is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder)convertView.getTag();
        }

        // find the image view
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
        //clear out image from convertView
        ivImage.setImageResource(0);

        //populate data into the template view using data object
        viewHolder.title.setText(movie.getOriginalTitle());
        viewHolder.overview.setText(movie.getOverview());
        Picasso.with(getContext()).load(movie.getPosterPath()).into(ivImage);

        //return the completed view to render on screen
        return convertView;
    }

}
