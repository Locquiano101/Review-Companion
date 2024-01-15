package com.example.reviewcompanion;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityQuizScore extends AppCompatActivity {

    TextView percentage, percentageIndication, category, scores;
    int _score, quiz_limit, _score_percentage;
    String _quiz_category, score_message, String_score_percentage;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_quiz);

        percentage = findViewById(R.id.percentage);
        percentageIndication = findViewById(R.id.percentIndicator);
        category = findViewById(R.id.category);
        scores = findViewById(R.id.score_view);

        _score = getIntent().getIntExtra("score", 0);
        quiz_limit = getIntent().getIntExtra("quiz_limit", 0);
        _quiz_category = getIntent().getStringExtra("quiz_category");

        Log.d("ScoreValue", "_score: " + _score);
        Log.d("ScoreValue", "quiz_limit: " + quiz_limit);
        Log.d("ScoreValue", "_quiz_category: " + _quiz_category);

        _score_percentage = (_score / quiz_limit) * 100;

        if (_score_percentage >= 90) {
            score_message = "A";
        } else if (_score_percentage >= 80) {
            score_message = "B";
        } else if (_score_percentage >= 70) {
            score_message = "C";
        } else if (_score_percentage >= 60) {
            score_message = "D";
        } else {
            score_message = "F";
        }

        String_score_percentage = _score + "/" + quiz_limit;

        percentage.setText(_score_percentage + "%");
        percentageIndication.setText("You've got : " + score_message);
        category.setText(_quiz_category + " :");
        scores.setText(String_score_percentage);
    }
}