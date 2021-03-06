package com.example.ivan.sudoku;

/**
 * Created by IVAN on 07.1.2015 г..
 */
import android.content.Context;
import android.media.MediaPlayer;

public class Music {
    public static MediaPlayer mp = null;

    public static void play(Context context, int resource) {
        stop(context);
        mp = MediaPlayer.create(context, resource);
        mp.setLooping(true);
        mp.start();
    }

    public static void stop(Context context) {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
