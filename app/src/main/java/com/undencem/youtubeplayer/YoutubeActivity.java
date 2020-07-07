package com.undencem.youtubeplayer;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener{

    private static final String TAG = "YoutubeActivity";
    
    // Add your Google API here    
    static final String GOOGLE_API_KEY="....";// Remove dots and add yr Google API key
    static final String YOUTUBE_VIDEO_ID="qpJRgr6HzAw"; 
    static final String YOUTUBE_PLAYLIST="PLgCYzUzKIBE9XqkckEJJA0I1wVKbUAOdv";
    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConstraintLayout layout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_youtube,null);
        setContentView(layout);

        //Dynamically added Layout
        //Button button1= new Button(this);
        //button1.setLayoutParams(new ConstraintLayout.LayoutParams(300,80));
        //button1.setText("Button added");
        //layout.addView(button1);

        YouTubePlayerView playerView = new YouTubePlayerView(this);
        // ViewGroup.LayoutParams.MATCH_PARENT  ---> width and height
        playerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(playerView);

        playerView.initialize(GOOGLE_API_KEY,this);
    }



    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Log.d(TAG, "onInitializationSuccess: provider is "+provider.getClass().toString());
        Toast.makeText(this,"Initialized Youtube Player successfully",Toast.LENGTH_LONG).show();

        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);

        if(!wasRestored) {
            youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

          final int REQUEST_CODE = 1;

          if(youTubeInitializationResult.isUserRecoverableError()) {
              youTubeInitializationResult.getErrorDialog(this,REQUEST_CODE).show();
          }
          else {
              String errorMessage = String.format("There was an error initiliazing the YoutubePlayer (%1$s)",youTubeInitializationResult.toString());
              Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show();
          }
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
             Toast.makeText(YoutubeActivity.this,"Good video is playing ok", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPaused() {
            Toast.makeText(YoutubeActivity.this,"Video was paused", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStopped() {
            Toast.makeText(YoutubeActivity.this,"Video was stopped", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener =new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {
            Toast.makeText(YoutubeActivity.this,"Ad started", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onVideoStarted() {
            Toast.makeText(YoutubeActivity.this,"Video started", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onVideoEnded() {
            Toast.makeText(YoutubeActivity.this,"Video ended ", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };
}
