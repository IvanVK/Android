package com.example.ivan.colorpreviewerr;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MyActivity extends Activity {


    private Button buttonEdit;
    private EditText EditText;
    private View backgroundColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        backgroundColor = findViewById(R.id.background);
        buttonEdit = (Button) findViewById(R.id.button);
        EditText = (EditText) findViewById(R.id.get_color);
        buttonEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
//                LinearLayout  linearLayout = (LinearLayout) findViewById(R.id.background);
//                linearLayout.setBackgroundColor(Color.parseColor(EditText.getText().toString().trim()));
                backgroundColor.setBackgroundColor(Color.parseColor(EditText.getText().toString().trim()));

            }
        });
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