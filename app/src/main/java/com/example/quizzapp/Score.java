package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Score extends AppCompatActivity {
    TextView tvScore ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        initializeBtnBackToQuizzesEvent();
        tvScore = findViewById(R.id.tvScore);
        getScore();



    }
    private void getScore()
    {
        int[] extras = this.getIntent().getExtras().getIntArray("score");
        int score = extras[0];
        int questionsCount = extras[1];

        tvScore.setText(score+"/"+questionsCount);
    }
    private void initializeBtnBackToQuizzesEvent(){
        Button btnBackToQuizzes =  findViewById(R.id.btnBackToQuizzes);
        btnBackToQuizzes.setOnClickListener(view -> {
            backToQuizzes();
        });
    }
    private void backToQuizzes(){
        startActivity(new Intent(this,QuizList.class));
    }
}