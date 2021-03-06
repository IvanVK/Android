package com.example.ivan.sudoku;

/**
 * Created by IVAN on 07.1.2015 г..
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class Game extends Activity {

    private int difficulty;
    private int count=0;

    @Override
    protected void onPause() {
        super.onPause();
        Music.stop(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Music.play(this, R.raw.main);
    }

    private final int[][][] used = new int[9][9][];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        int diff = getIntent().getIntExtra(KEY_DIFFICULTY, DIFFICULTY_EASY);
        puzzle = getPuzzle(diff);
        calculateUsedTiles();

        puzzleView = new PuzzleView(this);
        setContentView(puzzleView);
        puzzleView.requestFocus();
    }

    private void calculateUsedTiles() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                used[x][y] = calculateUsedTiles(x, y);
            }
        }
    }

    private int[] calculateUsedTiles(int x, int y) {
        int c[] = new int[9];

        for (int i = 0; i < 9; i++) {
            if (i == x) {
                continue;
            }

            int t = getTile(i, y);
            if (t != 0) {
                c[t - 1] = t;
            }
        }
        int startx = (x / 3) * 3;
        int starty = (y / 3) * 3;
        for (int i = startx; i < startx + 3; i++) {
            for (int j = starty; j < starty + 3; j++) {
                if (i == x && j == y) {
                    continue;
                }

                int t = getTile(i, j);
                if (t != 0) {
                    c[t - 1] = t;
                }
            }
        }

        int nused = 0;
        for (int t : c) {
            if (t != 0) {
                nused++;
            }
        }

        int c1[] = new int[nused];
        nused = 0;
        for (int t : c) {
            if (t != 0) {
                c1[nused++] = t;
            }
        }
        return c1;
    }

    private int[] getPuzzle(int diff) {
        switch (diff) {
            case DIFFICULTY_EASY:
                puz = easyPuzzle;
                difficulty=diff;
                break;
            case DIFFICULTY_HARD:
                puz = hardPuzzle;
                difficulty=diff;
                break;
            case DIFFICULTY_MEDIUM:
                puz = mediumPuzzle;
                difficulty=diff;
                break;
            default:
                puz = easyPuzzle;
                difficulty=diff;
                break;
        }

        return fromPuzzleString(puz);
    }

    static protected int[] fromPuzzleString(String strpuz) {
        int[] puz = new int[strpuz.length()];
        for (int i = 0; i < puz.length; i++) {
            puz[i] = strpuz.charAt(i) - '0';
        }
        return puz;
    }

    static private String toPuzzleString(int[] puz) {
        StringBuilder buf = new StringBuilder();
        for (int element : puz) {
            buf.append(element);
        }
        return buf.toString();
    }

//    public boolean isSolved() {
//        for (char element :  puz.toCharArray()) {
//            if (element ==  '0' ) return false;
//        }
//        return true;
//    }

    private static final String TAG = "Sudoku";
    private static String puz;
    public static final String KEY_DIFFICULTY = "difficulty";
    public static final int DIFFICULTY_EASY = 0;
    public static final int DIFFICULTY_MEDIUM = 1;
    public static final int DIFFICULTY_HARD = 2;

    private int puzzle[] = new int[9 * 9];
    private final String easyPuzzle =
//            "406000209570206000001005008" +
//                    "603481700700500300005000000" +
//                    "089000430060000001304000067";
    "254186397173925846689743125" +
            "526314978497258613831679452" +
            "915862734748531269362497580";
    private final String mediumPuzzle =
            "730294060001006000450080000" +
                    "000300086283007400060000010" +
                    "070025000800070000005400790";
    private final String hardPuzzle =
            "070285010008903500000000000" +
                    "500010008010000090900040003" +
                    "000000000002408600090632080";
    private PuzzleView puzzleView;

    public String getTileString(int x, int y) {
        int v = getTile(x, y);
        if (v == 0) {
            return "";
        } else {
            return String.valueOf(v);
        }
    }

    private int getTile(int x, int y) {
        return puzzle[y * 9 + x];
    }

    public void showKeypad(int x, int y) {
        int tiles[] = getUsedTiles(x, y);
        if (tiles.length == 9) {
            Toast toast = Toast.makeText(this, R.string.no_moves_label, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            Dialog v = new Keypad(this, tiles, puzzleView);
            v.show();
        }
    }

    private int[] getUsedTiles(int x, int y) {
        return used[x][y];
    }

    public boolean setTileIfValid(int x, int y, int i) {
        int tiles[] = getUsedTiles(x, y);
        if (i != 0) {
            for (int tile : tiles) {
                if (tile == i) {
                    return false;
                }
            }
        }
        setTile(x, y, i);
        count++;
        switch (difficulty){
            case DIFFICULTY_EASY:
                if(count==1) {
                    double score=(System.currentTimeMillis()- Sudoku.getStarttime())/1000;
                    AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
                    builder.setTitle("sudoku solved");
                    builder.setMessage("scores" + score);
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.show();
                }
                break;
            case DIFFICULTY_HARD:
                if(count==55) {
                    String nickname = getIntent().getStringExtra(Sudoku.NICKNAME_EXTRA);
                    double score=(System.currentTimeMillis()- Sudoku.getStarttime())/1000;
                    AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
                    builder.setTitle("sudoku solved");
                    builder.setMessage( nickname + "scores" + score);
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.show();
                }
                break;
            case DIFFICULTY_MEDIUM:
                if(count==51) {
                    double score=(System.currentTimeMillis()- Sudoku.getStarttime())/1000;
                    AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
                    builder.setTitle("sudoku solved");
                    builder.setMessage("scores" + score);
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.show();
                }
                break;
            default:
                puz = easyPuzzle;
                break;
        }
        calculateUsedTiles();
        return true;
    }

    private void setTile(int x, int y, int i) {
        puzzle[9 * y + x] = i;
    }
}