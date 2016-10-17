package com.codepath.flickster.activities;

import android.os.Bundle;
import android.widget.Toast;

import com.codepath.flickster.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aditi on 10/15/2016.
 */

public class TrailerActivity extends YouTubeBaseActivity {
    public static final String YT_API_KEY = "AIzaSyCZLp4KZp_u_wUr02TQ7PGluxOLluUU1zw";

    @BindView(R.id.player) YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_youtube);

        ButterKnife.bind(this);

        youTubePlayerView.initialize(YT_API_KEY,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {
                        String vId = getIntent().getExtras().getString("video_id");
                        // do any work here to cue video, play video, etc.
                        //youTubePlayer.cueVideo(vId);
                        // or to play immediately
                         youTubePlayer.loadVideo(vId);
                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {
                        Toast.makeText(TrailerActivity.this, "Youtube Failed!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
