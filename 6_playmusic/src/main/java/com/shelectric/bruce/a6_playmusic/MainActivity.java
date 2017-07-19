package com.shelectric.bruce.a6_playmusic;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MusicInterface musicInterface;
    private Intent intent;
    private mServiceConn conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this,MusicService.class);
        startService(intent);
        conn = new mServiceConn();

        bindService(intent, conn,BIND_AUTO_CREATE);
    }

    public class mServiceConn implements ServiceConnection {


        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
           musicInterface = (MusicInterface) iBinder;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }

    public void play(View view) {
        musicInterface.play();
    }

    public void pause(View view) {
        musicInterface.pause();
    }

    public void continuePlay(View view) {
        musicInterface.continuePlay();
    }
    public void stop(View view){
        unbindService(conn);
        stopService(intent);
        finish();
    }
}
