package com.example.reviewcompanion;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class ActivitySettings extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void ToggleMusic(View view) {
        ToggleButton toggleSounds = findViewById(R.id.toggle_sounds);
        if (toggleSounds.isChecked()) {
            // ToggleButton is checked, perform action
            Toast.makeText(this, "Sounds are on", Toast.LENGTH_SHORT).show();
        } else {
            // ToggleButton is unchecked, perform action
            Toast.makeText(this, "Sounds are off", Toast.LENGTH_SHORT).show();
        }
    }

    public void ToggleDarkMode(View view) {
    }

    public void ToggleAlarm(View view) {
    }
}
