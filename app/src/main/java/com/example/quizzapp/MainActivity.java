package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvSignUp;
    TextView tvGuest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        tvGuest = findViewById(R.id.tvGuest);

        tvGuest.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,QuizList.class));
        });
        tvSignUp.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,SignUp.class));
        });
    }
}