package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
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
    /*public void fillQuizList(){
        QuizQuestion q1 = new QuizQuestion("naruto is a girl ?","quizzimage" , 0);
        Map<Integer, String> choices1 = new HashMap<>();
        choices1.put(0, "nop");
        choices1.put(1, "yes");
        q1.setChoices(choices1);


        QuizQuestion q2 = new QuizQuestion("where to go ?","quizzimage" , 2);
        Map<Integer, String> choices2 = new HashMap<>();
        choices2.put(0, "right");
        choices2.put(1, "left");
        choices2.put(2, "down");
        q2.setChoices(choices2);

        List<QuizQuestion> ql = new ArrayList<>(Arrays.asList(q1,q2));

        Quiz quiz1 = new Quiz("Anime",ql);

        quizLists.add(quiz1);

    }*/
    public void fillQuizListLayout(List<Quiz> quizLists){
        lyQuizList.removeAllViews();
        for (int i = 0; i < quizLists.size(); i++) {
            Button button = new Button(this.getApplicationContext());
            button.setText(quizLists.get(i).getName());
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setBackgroundColor((int)R.color.main_color);
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