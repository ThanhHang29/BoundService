package com.ttth.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.ttth.model.Song;

public class BoundService extends Service {
    public static final String TAG = "Bound Service";
    private Song song;
    private MyBinder myBinder;
    public BoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"+++++++++onbind");
//        MyBinder myBinder = new MyBinder(this);
        song.play();
        return myBinder;
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"+++++++++on destroy");
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        song = new Song(this);
        Log.e(TAG,"+++++++++onCreate");
        myBinder = new MyBinder(this);
        super.onCreate();
    }
    public void fastForward(){
        song.fastForward(10000);
        Log.e(TAG,"+++++++++on ff");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        song.stop();
        Log.e(TAG,"+++++++++on unbind");
        return super.onUnbind(intent);
    }
    public class MyBinder extends Binder{
        public  BoundService mBoundService;

        public MyBinder(BoundService mBoundService) {
            Log.e(TAG,"+++++++++on bound");
            this.mBoundService = mBoundService;
        }

        public BoundService getmBoundService() {
            Log.e(TAG,"+++++++++ get bound");
            return mBoundService;
        }
    }
}
