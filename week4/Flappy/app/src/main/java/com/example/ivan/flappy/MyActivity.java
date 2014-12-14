package com.example.ivan.flappy;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MyActivity extends Activity {

    MediaPlayer mp;
    private Bitmap bmp = null;

//    private int newWidth = 0;
//    private int newHeight = 0;
//    private Bitmap bmpScaled = null;
//    private int move = 0;
//    private float scale = 0;
//    private GameView gameView = null;
//
//    public MyActivity(GameView gameView, Bitmap bmp, int x, int y)
//    {
//        this.gameView = gameView;
//        this.bmp = bmp;
//        scale = (float) bmp.getHeight() / (float) gameView.getHeight();
//        newWidth = Math.round(bmp.getWidth() / scale);
//        newHeight = Math.round(bmp.getHeight() / scale);
//        bmpScaled = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, true);
//    }
//
//    private void start()
//    {
//        move++;
//    }
//
//    private void update()
//    {
//        start();
//    }
//
//    public void onDraw(Canvas canvas)
//    {
//        update();
//        canvas.drawBitmap(bmpScaled, move, 0, null);
//    }



    //networkcode
    //PUT to server http
    @Override
    protected void onResume() {
        super.onResume();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        URL url = null;
        try {
            url = new URL("http://95.111.103.224:8080/Flappy/scores");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);

            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-type", "application/json");
            OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
            String string = "{ \"name\" : \"ivan\" , \"mail\" : \"hack@bul.bg\" , \"whereFrom\" : \"FMI\" , \"score\" : 0}";
            streamWriter.write(string);
            streamWriter.close();

            Log.d("RESPONSE", connection.getResponseMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mp = MediaPlayer.create(MyActivity.this, R.raw.prey_overture);
        mp.setLooping(true);
        mp.start();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
