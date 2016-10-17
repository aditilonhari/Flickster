package com.codepath.flickster.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.flickster.R;
import com.squareup.picasso.Picasso;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by aditi on 10/16/2016.
 */

public class DetailsActivity extends AppCompatActivity{

    @BindView(R.id.ivDetailsImage) ImageView ivImage;
    @BindView(R.id.tvDetailsTitle) TextView tvTitle;
    @BindView(R.id.tvDetailsOverview) TextView tvOverview;
    @BindView(R.id.tvReleaseDate) TextView tvReleaseDate;
    @BindView(R.id.ratingBar) RatingBar ratingBar;

    @BindDrawable(R.drawable.nomovieposter) Drawable noMoviePoster;
    @BindDrawable(R.drawable.notfound) Drawable imageNotFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        Intent i = getIntent();
        String poster = i.getStringExtra("poster");
        String title = i.getStringExtra("title");
        String overview = i.getStringExtra("overview");
        String releaseDate = i.getStringExtra("date");
        Float rating = i.getFloatExtra("rating",0);

        tvTitle.setText(title);
        tvOverview.setText("Overview: \n " + overview);
        tvReleaseDate.setText("Release Date: " + releaseDate);

        ratingBar.setIsIndicator(true);
        ratingBar.setNumStars(5);
        ratingBar.setRating(rating);
        ratingBar.setStepSize((float)0.1);
        Drawable progress = ratingBar.getProgressDrawable();
        DrawableCompat.setTint(progress, Color.rgb(218, 165, 32));

        Picasso.with(this).load(poster)
                .transform(new RoundedCornersTransformation(10, 10))
                .placeholder(noMoviePoster)
                .error(imageNotFound)
                .into(ivImage);

    }
}
