package com.example.ivan.sudoku;

/**
 * Created by IVAN on 07.1.2015 г..
 */
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.EditText;

public class Sudoku extends Activity implements OnClickListener {

    private EditText nickname;
    public static final String NICKNAME_EXTRA = "nickname";

    @Override
    protected void onPause() {
        super.onPause();
        Music.stop(this);
    }

    public static long getStarttime() {
        return starttime;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Music.play(this, R.raw.main);
    }
    private static long starttime;

    private static final String TAG = "Sudoku";

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_button:
                openNewGame();
                break;
            case R.id.exit_button:
                finish();
                break;
            default:
                break;
        }
    }

    private void openNewGame() {
        new AlertDialog.Builder(this).setTitle(R.string.new_game_title)
                .setItems(R.array.difficulty,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startGame(which);
                                starttime= System.currentTimeMillis();

                            }
                        }).show();
    }

    private void startGame(int i) {
        Log.d(TAG, "clicked on " + i);
        Intent intent = new Intent(Sudoku.this,Game.class);
        if (nickname.getText().toString() != null) {
            intent.putExtra(NICKNAME_EXTRA, nickname.getText().toString());
        }
//        intent.putExtra()
        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        nickname = (EditText) findViewById(R.id.name);
        View newButton = this.findViewById(R.id.new_button);
        if ("".equals(nickname.getText().toString())) {
            newButton.setOnClickListener(this);
        }

        View exitButton = this.findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
    }
}