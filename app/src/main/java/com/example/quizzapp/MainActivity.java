package com.example.quizzapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextView tvSignUp;
    TextView tvGuest;
    EditText etUsername;
    EditText etPassword;
    FirebaseAuth firebaseAuth;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        tvGuest = findViewById(R.id.tvGuest);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        firebaseAuth = FirebaseAuth.getInstance();
        tvGuest.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,QuizList.class));
        });
        tvSignUp.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,SignUp.class));
        });

        btnLogin.setOnClickListener( view -> login());

    }

    private void login()
    {
        if(firebaseAuth.getCurrentUser() != null)
        {
            if(etUsername.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty())
                Toast.makeText(this,"Veuillez remplire tous les champs",Toast.LENGTH_SHORT).show();
            else
            {
                firebaseAuth.signInWithEmailAndPassword(etUsername.getText().toString().toLowerCase(),etPassword.getText().toString().toLowerCase()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            startActivity(new Intent(MainActivity.this,QuizList.class));
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"UserName ou Password non valide",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }

    }
}