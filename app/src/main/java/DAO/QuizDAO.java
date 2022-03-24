package DAO;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Models.Quiz;
import MyUtil.IOnLoadData;

public class QuizDAO {

    final private FirebaseDatabase firebaseDatabase;
    final private DatabaseReference dbRef;
    final private String TAG = Quiz.class.getSimpleName()+"/FB";
    List<Quiz> quizList ;

    public QuizDAO(){
        firebaseDatabase = FirebaseDatabase.getInstance("https://quiz-24676-default-rtdb.europe-west1.firebasedatabase.app/");
        dbRef = firebaseDatabase.getReference(Quiz.class.getSimpleName());
        quizList = new ArrayList<>();

    }

    public Task<Void> add(Quiz quiz){
        return dbRef.push().setValue(new Quiz("Test",null));
    }

    public void loadQuizzes(IOnLoadData onLoadData){

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    quizList.clear();
                    for (DataSnapshot dataSnapshot :
                            snapshot.getChildren()) {
                        quizList.add((dataSnapshot.getValue(Quiz.class)));
                    }
                    onLoadData.load(quizList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }

}
