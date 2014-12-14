package com.example.ivan.multipleactivities;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MyActivity extends Activity {

    EditText MyString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Button callButton = (Button)findViewById(R.id.call);
        MyString = (EditText)findViewById(R.id.string);

        callButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                    String uri = ("tel:" + MyString.getText().toString());
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(uri));
                    startActivity(intent);
            }
        });

        Button browseButton = (Button)findViewById(R.id.browse);
        browseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String uri = ("https://" + MyString.getText().toString());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });

        Button alarmButton = (Button)findViewById(R.id.alarm);
        alarmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                String text = MyString.getText().toString();
                int index = text.indexOf(":");
                int hours = Integer.parseInt(text.substring(0,index));
                int minutes = Integer.parseInt(text.substring(index+1,text.length()));

                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_HOUR, hours);
                i.putExtra(AlarmClock.EXTRA_MINUTES, minutes);
                startActivity(i);
            }
        });



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
