package com.example.reviewcompanion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseQuestions extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuizHolder.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_quiz";
    static final String COLUMN_SUBJECT = "Subject";
    static final String COLUMN_QUESTION = "question";
    static final String COLUMN_CHOICE_A = "choice_a";
    static final String COLUMN_CHOICE_B = "choice_b";
    static final String COLUMN_CHOICE_C = "choice_c";
    static final String COLUMN_CHOICE_D = "choice_d";
    static final String COLUMN_ANSWERS = "answer";

    public DatabaseQuestions(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME + " ("
                        + COLUMN_SUBJECT  + " TEXT, "
                        + COLUMN_QUESTION + " TEXT, "
                        + COLUMN_CHOICE_A + " TEXT, "
                        + COLUMN_CHOICE_B + " TEXT, "
                        + COLUMN_CHOICE_C + " TEXT, "
                        + COLUMN_CHOICE_D + " TEXT, "
                        + COLUMN_ANSWERS  + " TEXT);";

        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void insertQuestion(String subject, String question, String choiceA, String choiceB, String choiceC, String choiceD, String answersAns) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_SUBJECT, subject);
        cv.put(COLUMN_QUESTION, question);
        cv.put(COLUMN_CHOICE_A, choiceA);
        cv.put(COLUMN_CHOICE_B, choiceB);
        cv.put(COLUMN_CHOICE_C, choiceC);
        cv.put(COLUMN_CHOICE_D, choiceD);
        cv.put(COLUMN_ANSWERS, answersAns);

        db.insert(TABLE_NAME, null, cv);
    }

}
