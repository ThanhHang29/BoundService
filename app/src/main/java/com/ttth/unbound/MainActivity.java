package com.ttth.unbound;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ttth.service.BoundService;
import com.ttth.service.MyService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnPlay,btnStop,btnFF;
    private boolean isBound = false;
    private BoundService mBoundService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnPlay = (Button) this.findViewById(R.id.btnPlay);
        btnStop = (Button) this.findViewById(R.id.btnStop);
        btnFF = (Button) this.findViewById(R.id.btnFF);
        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnFF.setOnClickListener(this);
    }
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.MyBinder binder = (BoundService.MyBinder) service;
            mBoundService = binder.getmBoundService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPlay:
                play();
                Toast.makeText(this,"Play",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnStop:
                stop();
                Toast.makeText(this,"Stop",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnFF:
                if (isBound){
                    mBoundService.fastForward();
                }else {
                    Toast.makeText(this,"service not active",Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
    public void play(){
        //unbound
//        Intent intent = new Intent(this, MyService.class);
//        startService(intent);


        //bound service
        Intent intent = new Intent(this, BoundService.class);
        bindService(intent,connection, Context.BIND_AUTO_CREATE);
    }
    public void stop(){
//        Intent intent = new Intent(this, MyService.class);
//        stopService(intent);

        //bound service
        unbindService(connection);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
        Toast.makeText(this,"service is stop", Toast.LENGTH_SHORT).show();
    }
}
