package com.ttth.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.ttth.model.Song;

public class MyService extends Service {
    public static final String TAG = "My service";
    private Song song;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"----------onBind");
        return null;
    }


    @Override
    public void onDestroy() {
        Log.e(TAG,"----------on destroy");
        if (song != null){
            song.stop();
        }
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"----------on start command");
        if (song != null){
            song.play();
        }
        return START_STICKY_COMPATIBILITY;
    }

    @Override
    public void onCreate() {
        Log.e(TAG,"----------onCreate");
        song = new Song(this);
        super.onCreate();
    }
}
