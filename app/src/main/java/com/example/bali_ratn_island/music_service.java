package com.example.bali_ratn_island;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class music_service extends Service {
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.bg_music);
        mediaPlayer.setVolume(10,10);


        if (mediaPlayer.isPlaying()==true)
        {
            mediaPlayer.setLooping(true);
        }
        else if (mediaPlayer.isPlaying()==false)
        {
            mediaPlayer.setLooping(false);
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mediaPlayer.start();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();

        mediaPlayer.setVolume(0,0);

    }

}
