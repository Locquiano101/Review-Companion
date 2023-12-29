package com.example.reviewcompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ActivityPreInterface extends AppCompatActivity {
    CountDownTimer countDownTimer;
    int totalTime = 10000; // Total time in milliseconds (e.g., 10 seconds)
    ProgressBar progressBar;
    TextView progressText;
    DatabaseVariableHolder variableCategory = new DatabaseVariableHolder();
    String[] Category = variableCategory.Category;
    String[][] Questions = variableCategory.questions;
    String[][] Choice_A = variableCategory.ChoiceA;
    String[][] Choice_B = variableCategory.ChoiceB;
    String[][] Choice_C = variableCategory.ChoiceC;
    String[][] Choice_D = variableCategory.ChoiceD;
    String[][] Answers = variableCategory.CorrectAns;
    int x, y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_interface);
        progressBar = findViewById(R.id.progress_bar);
        progressText = findViewById(R.id.indicator);

        DatabaseQuestions databaseHelper = new DatabaseQuestions(this);
        boolean isTableEmpty = databaseHelper.isTableEmpty();

        if (isTableEmpty) {
            InsertInitialQuestions();//Table is empty, perform your desired actions here
        } else {
            startProgressBarCountup();// Table is not empty, perform other actions here
            progressText.setText("Loading Questions...");
        }
    }
    private void startProgressBarCountup() {
        final int maxProgress = 100; // Assuming maximum progress value
        countDownTimer = new CountDownTimer(totalTime, 500) {
            int progress = 0;

            @Override
            public void onTick(long millisUntilFinished) {
                // Calculate progress in reverse
                progress = (int) (((totalTime - millisUntilFinished) * maxProgress) / totalTime);

                // Set progress for the ProgressBar
                progressBar.setProgress(progress);
            }
            @Override
            public void onFinish() {
                // Hide the progress bar when the countdown finishes
                progressBar.setVisibility(View.GONE);
                progressText.setVisibility(View.GONE);
                Intent intent = new Intent(ActivityPreInterface.this, ActivityMainInterface.class);
                startActivity(intent);
            }
        }.start();
    }


    @SuppressLint("StaticFieldLeak")
    public void InsertInitialQuestions() {

        DatabaseQuestions myDB = new DatabaseQuestions(this);

        final int[] totalQuestions = {0};
        final int[] currentQuestion = {0};

        new AsyncTask<Void, Integer, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (x = 0; x < Questions.length; x++) {
                    totalQuestions[0] += Questions[x].length;
                }

                for (x = 0; x < Questions.length; x++) {
                    for (y = 0; y < Questions[x].length; y++) {
                        myDB.insertQuestion(
                                Category[x], Questions[x][y],
                                Choice_A[x][y], Choice_B[x][y],
                                Choice_C[x][y], Choice_D[x][y],
                                Answers[x][y]
                        );

                        currentQuestion[0]++;

                        try {
                            Thread.sleep(100); // Adjust delay time as needed
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // Update progress based on the total number of questions
                        publishProgress((currentQuestion[0] * 100) / totalQuestions[0]);
                    }
                }
                return null;
            }
            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                // Update the progress bar here with values[0]
                progressBar.setProgress(values[0]);
                if (x < Category.length) { // Change from <= to <
                    progressText.setText("Adding questions from:" + Category[x]);
                } else {
                    progressText.setVisibility(View.GONE);
                }
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                // Task completed, hide or handle the progress bar as needed
                progressBar.setVisibility(View.GONE); // Hide the progress bar
                // Perform any UI updates or operations after the insertion completes
                Intent intent = new Intent(ActivityPreInterface.this, ActivityMainInterface.class);
                startActivity(intent);
            }
        }.execute();
    }
}