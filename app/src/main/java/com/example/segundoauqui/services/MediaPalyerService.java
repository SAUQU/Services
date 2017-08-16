package com.example.segundoauqui.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by segundoauqui on 8/16/17.
 */

public class MediaPalyerService extends Service{

    private  MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }


    //Settings.System.DEFAULT_RINGTONE_URI
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player = MediaPlayer.create(this, R.raw.dogg);
        player.setLooping(true);
        player.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
    }
}
