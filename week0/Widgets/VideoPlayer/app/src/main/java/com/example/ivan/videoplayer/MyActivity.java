package com.example.ivan.videoplayer;


import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;
import java.io.File;


public class MyActivity extends Activity {
    private VideoView video_view;
    private Button prevButton;
    private Button playButton;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        video_view = (VideoView) findViewById(R.id.videoView);
        prevButton = (Button) findViewById(R.id.prev);
        playButton = (Button) findViewById(R.id.play);
        nextButton = (Button) findViewById(R.id.next);

        final File video = new File(Environment.getExternalStorageDirectory(),
                "100.mp4");
        Uri vid = Uri.fromFile(video);
        video_view.setVideoURI(vid);
        final int time = 3000;

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (video_view.isPlaying()) {
                    playButton.setBackgroundResource(R.drawable.play);
                    video_view.pause();
                }else {
                    playButton.setBackgroundResource(R.drawable.pause);
                    video_view.start();
                }
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                video_view.seekTo(video_view.getCurrentPosition() - time );
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                video_view.seekTo( video_view.getCurrentPosition() + time );
            }
        });


    }
}