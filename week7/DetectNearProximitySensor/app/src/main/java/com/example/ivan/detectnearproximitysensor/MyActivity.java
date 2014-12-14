package com.example.ivan.cetectnearproximitysensor;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MyActivity extends Activity  {

    SensorManager sensorMan;
    Sensor sensorProx;
    MediaPlayer mp;



    SensorEventListener sensorEventListener = new SensorEventListener() {


        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.values[0] == 0) {
                mp = MediaPlayer.create(MyActivity.this, R.raw.hell);
                mp.start();
            }
        }
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        sensorMan = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorProx = sensorMan.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorMan.registerListener(sensorEventListener, sensorProx, SensorManager.SENSOR_DELAY_NORMAL);

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }



}
