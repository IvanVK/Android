package com.example.ivan.gestureimage;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;


public class MyActivity extends Activity {
    private float lastX;
    private float lastY;
    private ImageView imageView;
    private float scale = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        imageView = (ImageView) findViewById(R.id.picture);
        final ScaleGestureDetector detector;
        detector = new ScaleGestureDetector(this, new ScaleListener());
        imageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                int action = event.getActionMasked();
                switch (action){
                    case MotionEvent.ACTION_DOWN:{
                        lastX = event.getRawX() - imageView.getX();
                        lastY = event.getRawY() - imageView.getY();
                        break;
                    }
                    case MotionEvent.ACTION_POINTER_DOWN:{
                        break;
                    }
                    case MotionEvent.ACTION_MOVE:{
                        final float dx = event.getRawX() - lastX;
                        final float dy = event.getRawY() - lastY;
                        imageView.setTranslationX(dx);
                        imageView.setTranslationY(dy);
                    }
                }
                return true;
            }
        });
    }

    
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            imageView.setScaleX(scale);
            imageView.setScaleY(scale);
            return true;
        }
    }
}
