package com.example.reviewcompanion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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
                        + COLUMN_SUBJECT + " TEXT, "
                        + COLUMN_QUESTION + " TEXT, "
                        + COLUMN_CHOICE_A + " TEXT, "
                        + COLUMN_CHOICE_B + " TEXT, "
                        + COLUMN_CHOICE_C + " TEXT, "
                        + COLUMN_CHOICE_D + " TEXT, "
                        + COLUMN_ANSWERS + " TEXT);";

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
    boolean isTableEmpty() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT count(*) FROM " + TABLE_NAME, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            cursor.close();
            return count == 0;
        }
        return true;
    }
    public ArrayList<DatabaseVariableHolder> fetchQuestionsForQuiz(String category, int limit) {
        SQLiteDatabase getDB = this.getReadableDatabase();
        ArrayList<DatabaseVariableHolder> arrayList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE Subject = ? LIMIT ?";
        String[] selectionArgs = new String[]{category, String.valueOf(limit)};

        try (Cursor cursor = getDB.rawQuery(query, selectionArgs)) {
            while (cursor != null && cursor.moveToNext()) {
                DatabaseVariableHolder dbVariableHolder = new DatabaseVariableHolder();
                dbVariableHolder.question = cursor.getString(1);
                dbVariableHolder.choice_1 = cursor.getString(2);
                dbVariableHolder.choice_2 = cursor.getString(3);
                dbVariableHolder.choice_3 = cursor.getString(4);
                dbVariableHolder.choice_4 = cursor.getString(5);
                dbVariableHolder.answer = cursor.getString(6);

                arrayList.add(dbVariableHolder);
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            getDB.close(); // Close the database when done
        }
        return arrayList;
    }


    public int getTotalQuestionsForCategory(String category) {
        SQLiteDatabase getDB = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE subject = ?";
        int totalQuestions = 0;

        Cursor cursor = getDB.rawQuery(query, new String[]{category});

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                totalQuestions = cursor.getInt(0); // Retrieves the count from the first column
            }
            cursor.close();
        }
        return totalQuestions;
    }


}
