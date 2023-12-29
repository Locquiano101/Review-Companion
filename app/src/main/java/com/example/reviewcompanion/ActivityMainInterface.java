package com.example.reviewcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityMainInterface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);
    }

    public void startActivityBasedOnButton(View view) {
        Intent intent = null;
        if (view.getId() == R.id.take_quiz) {
            intent = new Intent(this, ActivityTakeQuiz.class);
        } else if (view.getId() == R.id.score_history) {
            intent = new Intent(this, ActivityScoreQuiz.class);
        } else if (view.getId() == R.id.settings) {
            intent = new Intent(this, ActivitySettings.class);
        } else if (view.getId() == R.id.about_us) {
            intent = new Intent(this, ActivityAboutUs.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}