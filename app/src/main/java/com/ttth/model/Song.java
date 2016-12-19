package com.ttth.model;

import android.content.Context;
import android.media.MediaPlayer;

import com.ttth.unbound.R;

/**
 * Created by Thanh Hang on 19/12/16.
 */

public class Song {
    private MediaPlayer mediaPlayer;

    public Song(Context context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.higher);
        mediaPlayer.setLooping(true);
    }
    public void play(){
        if (mediaPlayer != null){
            mediaPlayer.start();
        }
    }
    public void stop(){
        if (mediaPlayer != null){
            mediaPlayer.stop();
        }
    }
    public void fastForward(int msec){
        mediaPlayer.seekTo(msec);
    }
}
