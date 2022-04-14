package com.example.quizzapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    TextView tvLogin;
    Button btnSignUp;
    EditText tvUserName;
    EditText tvEmail;
    EditText tvPassword;
    EditText tvRePassword;
    private FirebaseAuth mAuth;
    private final String TAG = "SIGN_UP";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        tvLogin = findViewById(R.id.tvLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvUserName= findViewById(R.id.tvUserName);
        tvEmail= findViewById(R.id.tvEmail);
        tvPassword= findViewById(R.id.tvPassword);
        tvRePassword= findViewById(R.id.tvRePassword);

        tvLogin.setOnClickListener(view -> startActivity(new Intent(this,MainActivity.class)));
        btnSignUp.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            if(!tvEmail.getText().toString().isEmpty() && !tvPassword.getText().toString().isEmpty()
                && !tvUserName.getText().toString().isEmpty() && !tvRePassword.getText().toString().isEmpty())
                signUp();
            else
                Toast.makeText(SignUp.this, "Veuillez remplire tous les champs",
                        Toast.LENGTH_SHORT).show();
        });
    }

    private void signUp(){
        mAuth.createUserWithEmailAndPassword(tvEmail.getText().toString(),tvPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG,"user created successfully");
                    goToQuizList();
                }
                else{
                    Log.d(TAG,"failure :"+task.getException());
                    Toast.makeText(SignUp.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private void goToQuizList()
    {
        startActivity(new Intent(this,QuizList.class));
    }
}