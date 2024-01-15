package com.example.reviewcompanion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityMainInterface extends AppCompatActivity {

    SharedPreferences _preferences;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);

        MusicManager.initialize(this);

        _preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);

        boolean isMusicPlaying = _preferences.getBoolean("musicPlaying", false); // Change `false` to your desired default boolean value
        if (isMusicPlaying) {
            MusicManager.startMusic();
        } else {
            MusicManager.stopMusic();
        }
    }

    public void startActivityBasedOnButton(View view) {
        Intent intent = null;
        if (view.getId() == R.id.take_quiz) {
            intent = new Intent(this, ActivityQuizChooseCategory.class);
        } else if (view.getId() == R.id.score_history) {
            intent = new Intent(this, ActivityQuizScore.class);
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