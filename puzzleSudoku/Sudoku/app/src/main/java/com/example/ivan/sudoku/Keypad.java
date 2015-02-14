package com.example.ivan.sudoku;

/**
 * Created by IVAN on 07.1.2015 г..
 */
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class Keypad extends Dialog {
    private boolean isValid(int tile) {
        for (int t : useds) {
            if(tile == t) return false;
        }
        return true;
    }
    private void returnResult(int tile) {
        puzzleView.setSelectedTile(tile);
        dismiss();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.keypad_title);
        setContentView(R.layout.keypad);
        findViews();
        setListeners();
    }
    private void setListeners() {
        for (int i = 0; i < keys.length; i++) {
            final int t = i+1;
            keys[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    returnResult(t);
                }
            });
        }
        keypad.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                returnResult(0);
            }
        });
    }

    private void findViews() {
        keypad = findViewById(R.id.keypad);
        keys[0] = findViewById(R.id.keypad_1);
        keys[1] = findViewById(R.id.keypad_2);
        keys[2] = findViewById(R.id.keypad_3);
        keys[3] = findViewById(R.id.keypad_4);
        keys[4] = findViewById(R.id.keypad_5);
        keys[5] = findViewById(R.id.keypad_6);
        keys[6] = findViewById(R.id.keypad_7);
        keys[7] = findViewById(R.id.keypad_8);
        keys[8] = findViewById(R.id.keypad_9);
    }

    public Keypad(Context game, int[] useds, PuzzleView puzzleView) {
        super(game);
        this.useds = useds;
        this.puzzleView = puzzleView;
    }

//    protected static final String TAG = "Sudoku";
    private final View keys[] = new View[9];
    private View keypad;
    private final int useds[];
    private final PuzzleView puzzleView;
}
