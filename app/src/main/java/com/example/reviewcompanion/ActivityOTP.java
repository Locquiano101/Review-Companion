package com.example.reviewcompanion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityOTP extends AppCompatActivity {
    EditText otp1, otp2, otp3, otp4, otp5, otp6;
    String finalOTP = null;
    String OTP = "123457";
    SharedPreferences sharedPreferences;

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

        sharedPreferences = getSharedPreferences("OTP", Context.MODE_PRIVATE);

        if (isStringInSharedPreferences()) {
            Intent intent = new Intent(this, ActivityPreInterface.class);
            startActivity(intent);
            finish();
        } else {
            nextOTP();
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
    private boolean isStringInSharedPreferences() {
        return sharedPreferences.contains("savedOTP");
    }
    private void saveOTPToSharedPreferences(String otp) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("savedOTP", otp);
        editor.apply();
    }
    public void activate_app(View view) {
        if (!otp1.getText().toString().isEmpty() &&
                !otp2.getText().toString().isEmpty() &&
                !otp3.getText().toString().isEmpty() &&
                !otp4.getText().toString().isEmpty() &&
                !otp5.getText().toString().isEmpty() &&
                !otp6.getText().toString().isEmpty()
        ) {
            finalOTP = otp1.getText().toString() +
                    otp2.getText().toString() +
                    otp3.getText().toString() +
                    otp4.getText().toString() +
                    otp5.getText().toString() +
                    otp6.getText().toString();

            if (finalOTP.equals(OTP)) {
                saveOTPToSharedPreferences(finalOTP);

                Intent intent = new Intent(this, ActivityPreInterface.class);
                startActivity(intent);
                finish();
            } else {
                showAlertDialog("Invalid OTP, Please ask Sir RC Secretario for Confirmation.");
            }
        }
    }

    private void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ALERT!!!")
                .setMessage(message)
                .show();
    }
}