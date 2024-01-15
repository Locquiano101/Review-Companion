package com.example.reviewcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityQuizChooseCategory extends AppCompatActivity {


    private AutoCompleteTextView TotalQuestionNum, TotalTimerSet;

    DatabaseQuestions DatabaseQuestions = new DatabaseQuestions(this);
    int totalNumberOfQuestionPerCategory = 0;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_quiz);
        TotalQuestionNum = findViewById(R.id.total_number_of_questions);
        TotalTimerSet = findViewById(R.id.total_number_of_time);
        TotalQuestionNum.setEnabled(false);
        TotalTimerSet.setEnabled(false);
    }

    private void populateQuizTimer() {
        String[] Timer = new String[]{"5", "10", "15", "30", "45", "60"};
        ;

        if (totalNumberOfQuestionPerCategory == 0) {
            TotalTimerSet.setEnabled(false);
        } else {
            TotalTimerSet.setEnabled(true);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                Timer
        );

        TotalTimerSet.setAdapter(adapter);

        // If you want to handle item selection
        TotalTimerSet.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = (String) parent.getItemAtPosition(position);
            // Do something with the selected item
        });
    }

    private void populateTotalQuizNumber() {
        String[] TotalQuizNumber;

        if (totalNumberOfQuestionPerCategory == 0) {
            TotalQuestionNum.setEnabled(false);
            TotalQuizNumber = new String[]{"0"};
        } else {
            TotalQuestionNum.setEnabled(true);
            TotalQuizNumber = new String[]{"10", "20", "50", "75", "100", String.valueOf(totalNumberOfQuestionPerCategory)};
        }
        // eto nag se set ng numbers towards array
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                TotalQuizNumber
        );

        TotalQuestionNum.setAdapter(adapter);

        // If you want to handle item selection
        TotalQuestionNum.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = (String) parent.getItemAtPosition(position);
            // Do something with the selected item
        });
    }

    public void startActivityBasedOnButton(View view) {
        if (view.getId() == R.id.law) {
            category = "Law";
        } else if (view.getId() == R.id.auditing) {
            category = "Auditing";
        } else if (view.getId() == R.id.Tax) {
            category = "Tax";
        } else if (view.getId() == R.id.far) {
            category = "FAR";
        } else if (view.getId() == R.id.afar) {
            category = "AFAR";
        } else if (view.getId() == R.id.MS) {
            category = "MS";
        } else {
            category = null;
        }
        totalNumberOfQuestionPerCategory = DatabaseQuestions.getTotalQuestionsForCategory(category);
        populateTotalQuizNumber();
        populateQuizTimer();
        TotalQuestionNum.setEnabled(true);
        TotalTimerSet.setEnabled(true);
        Toast.makeText(this, "Total Questions for Subject " + category + ": " + totalNumberOfQuestionPerCategory, Toast.LENGTH_SHORT).show();
    }
    public void take_quiz(View view) {
        String selectedNumber = TotalQuestionNum.getText().toString().trim();
        String selectedTimeLimit = TotalTimerSet.getText().toString().trim();
        int totalTimeLimitInput = 0, totalQuestionNumInput = 0;

        if (category == null) {
            showAlertDialog("Please Select quiz category");

            return;
        }

        if (selectedNumber.isEmpty() && selectedTimeLimit.isEmpty()) {
            showAlertDialog("Please Fill up the Boxes");
            return;
        }

        try {
            totalTimeLimitInput = Integer.parseInt(selectedTimeLimit);
            totalQuestionNumInput = Integer.parseInt(selectedNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (totalTimeLimitInput <= 0) {
            showAlertDialog("Please Enter a valid time limit");
            return;
        }

        if (totalQuestionNumInput <= 0) {
            showAlertDialog("Please Select a valid number of questions");
            return;
        }

        if (totalQuestionNumInput > totalNumberOfQuestionPerCategory) {
            showAlertDialog("You have exceeded the total question limit for " + category
                    + "\nTotal Number of questions: " + totalNumberOfQuestionPerCategory);
            return;
        }

        Intent intent = new Intent(this, ActivityQuizTake.class);
        intent.putExtra("totalQuestionNumInput", totalQuestionNumInput);
        intent.putExtra("totalTimeLimitInput", totalTimeLimitInput);
        intent.putExtra("quiz_category", category);
        startActivity(intent);
        MusicManager.stopMusic();
    }
    private void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(null)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> TotalQuestionNum.setText(null))
                .show();
    }
}