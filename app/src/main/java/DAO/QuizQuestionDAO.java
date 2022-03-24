package DAO;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Models.Quiz;
import Models.QuizQuestion;
import MyUtil.IOnLoadData;

public class QuizQuestionDAO {
    private DatabaseReference dbRef;

    public QuizQuestionDAO() {
        dbRef = FirebaseDatabase.getInstance("https://quiz-24676-default-rtdb.europe-west1.firebasedatabase.app").getReference(QuizQuestion.class.getSimpleName());

    }

    public void loadQuestions(Quiz quiz, IOnLoadData iOnLoadData){
        dbRef.orderByChild("quizName").equalTo(quiz.getName()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        QuizQuestion model = dataSnapshot.getValue(QuizQuestion.class);
                        quiz.getQuizQuestions().add(model);
                    }
                    Log.d("loadSize",quiz.getQuizQuestions().size()+"");
                    iOnLoadData.load(quiz.getQuizQuestions());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
