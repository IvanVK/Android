package com.example.ivan.sudoku;

/**
 * Created by IVAN on 2.2.2015 г..
 */
import android.widget.ImageView;
public class Users {
     String nickname;
    private double time;
    private ImageView imageView;
    public Users (String nickname, double time) {
        this.nickname = nickname;
        this.time = time;
    }

    public class  ScoreColumns{
        public static final String TABLE_NAME="nickname";
        public static final String TABLE_SCORE="score";

    }


    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public double getTime() {
        return time;
    }
    public void setTime(double time) {
        this.time = time;
    }
}