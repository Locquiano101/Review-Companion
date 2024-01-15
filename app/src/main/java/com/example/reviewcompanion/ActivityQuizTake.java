package com.example.reviewcompanion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

public class ActivityQuizTake extends AppCompatActivity {
    TextView question_text, remaining_question, remaining_time;
    RadioGroup group_choice;
    RadioButton choice_a, choice_b, choice_c, choice_d, selectedRadioButton;
    Button next_button;
    DatabaseQuestions databaseQuestions;
    String _quiz_category, _selected_answer,
            _question, _choice_1, _choice_2,
            _choice_3, _choice_4, _answer;
    int _quiz_limit, _quiz_time_limit, _current_question_num, _quiz_score, selectedRadioButtonId;
    private CountDownTimer countDownTimer;
    ArrayList<DatabaseVariableHolder> questionArray = new ArrayList<>();
    DatabaseScore databaseScore = new DatabaseScore(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);
        initializeLayoutID();

        databaseScore = new DatabaseScore(this);
        databaseQuestions = new DatabaseQuestions(this);

        _quiz_limit = getIntent().getIntExtra("totalQuestionNumInput", 0);
        _quiz_time_limit = getIntent().getIntExtra("totalTimeLimitInput", 0);
        _quiz_category = getIntent().getStringExtra("quiz_category");

        setData();
        _quiz_time_limit =( _quiz_time_limit +1 ) * 1000;

        setTimer(_quiz_time_limit);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }
    private void initializeLayoutID() {

        question_text = findViewById(R.id.question_text);
        remaining_question = findViewById(R.id.remaining_question);
        remaining_time = findViewById(R.id.remaining_time);
        group_choice = findViewById(R.id.group_choice);
        choice_a = findViewById(R.id.choice_a);
        choice_b = findViewById(R.id.choice_b);
        choice_c = findViewById(R.id.choice_c);
        choice_d = findViewById(R.id.choice_d);
        next_button = findViewById(R.id.next_button);

    }
    public void setData() {
        questionArray = databaseQuestions.fetchQuestionsForQuiz(_quiz_category, _quiz_limit);

        if (questionArray.size() > 0) {
            Collections.shuffle(questionArray);

            _question = questionArray.get(_current_question_num).question;
            _choice_1 = questionArray.get(_current_question_num).choice_1;
            _choice_2 = questionArray.get(_current_question_num).choice_2;
            _choice_3 = questionArray.get(_current_question_num).choice_3;
            _choice_4 = questionArray.get(_current_question_num).choice_4;
            _answer = questionArray.get(_current_question_num).answer;

            ArrayList<String> choices = new ArrayList<>();
            choices.add(_choice_1);
            choices.add(_choice_2);
            choices.add(_choice_3);
            choices.add(_choice_4);

            Collections.shuffle(choices);

            choice_a.setText(choices.get(0));
            choice_b.setText(choices.get(1));
            choice_c.setText(choices.get(2));
            choice_d.setText(choices.get(3));

            remaining_question.setText((_current_question_num + 1) + "/" + questionArray.size());

            question_text.setText(_question);
        } else {
            Toast.makeText(this, "No questions available for this category", Toast.LENGTH_SHORT).show();
        }
    }
    private void setTimer(long totalTimeInMillis) {
        countDownTimer = new CountDownTimer(totalTimeInMillis, 1000) {

            public void onTick(long millisUntilFinished) {
                // Format the remaining time to display in mm:ss format
                int minutes = (int) (millisUntilFinished / 1000) / 60;
                int seconds = (int) (millisUntilFinished / 1000) % 60;

                @SuppressLint("DefaultLocale") String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);

                // Update the TextView with the formatted time
                remaining_time.setText(timeLeftFormatted);
            }

            @SuppressLint("SetTextI18n")
            public void onFinish() {
                // Countdown is finished
                remaining_time.setText("Times Up");
                timeRunsOut();
            }
        };
        countDownTimer.start();
    }
    private void timeRunsOut() {
        if (countDownTimer != null) {
            // Stop the countdown timer if it's running
            countDownTimer.cancel();
            countDownTimer = null;
            // Perform actions after the timer finishes
            Toast.makeText(this, "Time's Up!", Toast.LENGTH_SHORT).show();
            DelayButton();
        }
    }
    // BUTTON QUESTION MANAGER
    public void nextQuestion(View view) {

        selectedRadioButtonId = group_choice.getCheckedRadioButtonId();

        if (selectedRadioButtonId == -1) {
            Toast.makeText(this, "No answer selected", Toast.LENGTH_SHORT).show();
            next_button.setEnabled(true);
        } else {
            AnswerChecker();
        }
    }
    public void AnswerChecker() {
        selectedRadioButton = findViewById(selectedRadioButtonId);
        _selected_answer = selectedRadioButton.getText().toString();
        _answer = questionArray.get(_current_question_num).answer;
        // this part should be corrected into color changing the answer
        if (_selected_answer.equals(_answer)) { // this is checking if its right
            AnsweredCorrect();
        } else {
            AnsweredWrong();
        }
        DelayButton();
    }
    public void AnsweredCorrect() {
        Toast.makeText(this, "You're Right!", Toast.LENGTH_SHORT).show();
        _quiz_score++;
    }
    public void AnsweredWrong() {
        Toast.makeText(this, "You're Wrong! The right answer is: " + _answer, Toast.LENGTH_SHORT).show();
    }
    public void DelayButton() {
        next_button.setEnabled(false); // Disable button before delay

        final AtomicBoolean canClickButton = new AtomicBoolean(true);

        new Handler().postDelayed(() -> {
            _current_question_num++;

            if (_current_question_num < questionArray.size()) {
                group_choice.clearCheck();
                setData();
            } else {
                Toast.makeText(getApplicationContext(), "Test is completed\nCorrect Answers = " + _quiz_score, Toast.LENGTH_SHORT).show();
                addScore();
                finishQuiz();
            }
            setTimer(_quiz_time_limit);
            next_button.setEnabled(true); // Enable button after delay
            canClickButton.set(true);
        }, 2000);
    }

    public void finishQuiz() {
        Intent intent = new Intent(this, ActivityQuizScore.class);
        Log.d("ScoreView", "_quiz_limit: " + _quiz_limit);

        intent.putExtra("score", _quiz_score);
        intent.putExtra("quiz_limit", _quiz_limit);
        intent.putExtra("quiz_category", _quiz_category);

        startActivity(intent);
    }

    // TODO: CREATE AN ON STOP METHOD
    @SuppressLint("SimpleDateFormat")
    public void addScore() {
        long currentDateTimeMillis = System.currentTimeMillis();
        Date currentDateTime = new Date(currentDateTimeMillis);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String formattedDateTime = dateFormat.format(currentDateTime);

        DatabaseScore DatabaseScore = new DatabaseScore(this);

        DatabaseScore.addScore(_quiz_category, _quiz_score, _quiz_limit, formattedDateTime);
    }
}