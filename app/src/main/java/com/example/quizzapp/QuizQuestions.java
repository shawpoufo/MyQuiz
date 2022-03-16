 package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

import DAO.QuizQuestionDAO;
import Models.Quiz;
import Models.QuizQuestion;

 public class QuizQuestions extends AppCompatActivity {

     Quiz quiz;
     TextView tvQuestion;
     ImageView ivQuestion;
     RadioGroup rgQuestion;
     Button btnNext;
     int points;
     private  static int questionCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_questions);

        quiz = (Quiz) getIntent().getExtras().getSerializable("questionList");
        new QuizQuestionDAO().loadQuestions(quiz,questions -> nextQuestion());
        questionCount = -1;

        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        ivQuestion = (ImageView) findViewById(R.id.ivQuestion);
        rgQuestion = findViewById(R.id.rgQuestion);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(view -> {
            // user gain more points if response is correct
            if(rgQuestion.getCheckedRadioButtonId() == -1)
            {
                Toast.makeText(this,"Please make a choice",Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton radioButtonResponse = (RadioButton) findViewById(rgQuestion.getCheckedRadioButtonId());
            String response = radioButtonResponse.getTag().toString();
            if(quiz.getQuizQuestions().get(questionCount).verify(response))
                points++;
            nextQuestion();
        });



    }

    public void nextQuestion(){
        questionCount++;
        if(questionCount < quiz.getQuizQuestions().size()) {
            // clear radioGroup items
            rgQuestion.removeAllViews();
            // pass and display next question
            QuizQuestion question = quiz.getQuizQuestions().get(questionCount);
            tvQuestion.setText(question.getQuestion());
            ivQuestion.setImageResource(getDrawableId(question.getImageName()));
            for(Map.Entry<String,Object> entry : question.getOptions().entrySet()){
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(entry.getValue().toString());
                radioButton.setTag(entry.getKey());
                int id = View.generateViewId();
                radioButton.setId(id);
                rgQuestion.addView(radioButton);
            }
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(),Score.class);
            intent.putExtra("score",new int[]{points,quiz.getQuizQuestions().size()});
            startActivity(intent);

        }
    }


    private int getDrawableId(String imageName)
    {
        int resId = this.getResources().getIdentifier(imageName, "drawable", this.getPackageName());
        return resId;
    }

}