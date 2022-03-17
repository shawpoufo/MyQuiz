package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import DAO.QuizDAO;

import Models.Quiz;

public class QuizList extends AppCompatActivity {

    LinearLayout lyQuizList;
    QuizDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list);
        dao = new QuizDAO();

        lyQuizList = (LinearLayout) findViewById(R.id.lyQuizList);
        dao.loadQuizzes(quizzes -> fillQuizListLayout((List<Quiz>) quizzes));
    }

    public void fillQuizListLayout(List<Quiz> quizLists){
        lyQuizList.removeAllViews();
        for (int i = 0; i < quizLists.size(); i++) {
            Button button = new Button(this.getApplicationContext());
            button.setText(quizLists.get(i).getName());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.topMargin = 10;
            button.setLayoutParams(params);
            button.setBackgroundColor(getResources().getColor(R.color.quiz_color));
            button.setTextColor(getResources().getColor(R.color.white));

            button.setTag(i);

            lyQuizList.addView(button);
            button.setOnClickListener(view -> {

                Intent intent = new Intent(this, com.example.quizzapp.QuizQuestions.class);
                intent.putExtra("questionList",quizLists.get((int)view.getTag()));
                startActivity(intent);
            });
        }


    }
}