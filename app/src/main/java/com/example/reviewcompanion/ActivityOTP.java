package com.example.reviewcompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityOTP extends AppCompatActivity {
    EditText otp1, otp2, otp3, otp4, otp5,otp6;
    String finalOTP = null;
    DatabaseOTP databaseOTP = new DatabaseOTP(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);
        otp5 = findViewById(R.id.otp5);
        otp6 = findViewById(R.id.otp6);
        boolean noOTP = databaseOTP.isThereAnOTP();

        if (noOTP) {
            nextOTP();
        } else {
            Intent intent = new Intent(this, ActivityPreInterface.class);
            startActivity(intent);
        }
    }
    private void nextOTP() {
        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence edit, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence edit, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable edit) {
                if (edit.length() == 1) {
                    otp2.requestFocus();
                }
            }
        });
        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence edit, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence edit, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable edit) {
                if (edit.length() == 1) {
                    otp3.requestFocus();
                }
            }
        });
        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence edit, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence edit, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable edit) {
                if (edit.length() == 1) {
                    otp4.requestFocus();
                }
            }
        });
        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence edit, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence edit, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable edit) {
                if (edit.length() == 1) {
                    otp5.requestFocus();
                }
            }
        });
        otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence edit, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence edit, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable edit) {
                if (edit.length() == 1) {
                    otp6.requestFocus();
                }
            }
        });
    }
    public void activate_app(View view) {
        if (!otp1.getText().toString().isEmpty() &&
                !otp2.getText().toString().isEmpty() &&
                !otp3.getText().toString().isEmpty() &&
                !otp4.getText().toString().isEmpty() &&
                !otp5.getText().toString().isEmpty() &&
                !otp6.getText().toString().isEmpty()
        ) {

            finalOTP =
                    otp1.getText().toString() +
                            otp2.getText().toString() +
                            otp3.getText().toString() +
                            otp4.getText().toString() +
                            otp5.getText().toString() +
                            otp6.getText().toString();

            if(finalOTP.equals("123457")){
                databaseOTP.insertOTP(finalOTP);
                Intent intent = new Intent(this, ActivityPreInterface.class);
                startActivity(intent);
                Toast.makeText(this, "Your OTP is " + finalOTP, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Invalid OTP, try again", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please fill all the text boxes", Toast.LENGTH_SHORT).show();
        }
    }
}