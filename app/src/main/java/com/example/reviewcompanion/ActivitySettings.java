package com.example.reviewcompanion;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.textfield.TextInputLayout;

public class ActivitySettings extends AppCompatActivity {
    TextInputLayout BGMChooser;
    AutoCompleteTextView MusicChoice;
    Switch toggleMusic;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor prefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        BGMChooser = findViewById(R.id.background_music_select);
        MusicChoice = findViewById(R.id.background_music);
        toggleMusic = findViewById(R.id.toggle_sounds); // Corrected initialization for toggleMusic

    }

    public void ToggleMusic(View view) {

    }
}
