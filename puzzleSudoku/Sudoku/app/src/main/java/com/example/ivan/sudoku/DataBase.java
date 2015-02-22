package com.example.ivan.sudoku;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by IVAN on 7.2.2015 г..
 */
public class DataBase extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "database.db";
    public static final String COLUMN_NICKNAME = "nickname";
    public static final String TIME = "time";
    public static final String TABLE_NAME = "SCORES";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " ("
            + COLUMN_NICKNAME + " INTEGER PRIMARY KEY, "
            + TIME + " TEXT UNIQUE, "
            + " )";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
