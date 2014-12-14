package com.example.ivan.medium;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import java.util.Random;


public class MyActivity extends Activity {
    private View view;





//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.main);
//
//
//    final View a = findViewById(R.id.m);
//    final Random color = new Random();
//    final Paint p = new Paint();
//
//
//    b.setOnClickListener(new View.OnClickListener() {
//
//
//        @Override
//        public void onClick(View v) {
//            p.setARGB(256,color.nextInt(256),color.nextInt(256),color.nextInt(256));
//            a.setBackgroundColor((p.getColor()));
//
//        }
//    });
//    }


//BG or IT flag

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bgflag);
////        setContentView(R.layout.activity_itflag);
//
//    }

//Switch colors on touch.

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bgflag);
//        view = findViewById(R.id.bgflag);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Random random = new Random();
//                int index = random.nextInt(12);
//                int arr[] = getResources().getIntArray(R.array.colorss);
//                findViewById(R.id.first).setBackgroundColor(arr[index]);
//                index = random.nextInt(12);
//                findViewById(R.id.second).setBackgroundColor(arr[index]);
//                index = random.nextInt(12);
//                findViewById(R.id.third).setBackgroundColor(arr[index]);
//            }
//        });
//
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
//        setContentView(R.layout.activity_itflag);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
