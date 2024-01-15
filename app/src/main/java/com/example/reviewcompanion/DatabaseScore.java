package com.example.reviewcompanion;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseScore extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ScoreHolder.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_score";
    private static final String COLUMN_ID = "score_id";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_SCORE = "Score";
    private static final String COLUMN_ITEM = "QuestionItems";
    private static final String COLUMN_DATE = "date";

    public DatabaseScore(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME
                + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CATEGORY + " VARCHAR(255), "
                + COLUMN_SCORE + " INTEGER, "
                + COLUMN_ITEM + " INTEGER, " // Fixed the column name here
                + COLUMN_DATE + " VARCHAR(255));";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addScore(String category, int score, int item, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CATEGORY, category);
        cv.put(COLUMN_SCORE, score);
        cv.put(COLUMN_ITEM, item); // Fixed the column name here
        cv.put(COLUMN_DATE, date);

        db.insert(TABLE_NAME, null, cv);
    }


}
