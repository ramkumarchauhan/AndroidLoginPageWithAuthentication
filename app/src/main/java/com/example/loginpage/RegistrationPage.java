package com.example.loginpage;

import static android.widget.Toast.LENGTH_LONG;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationPage extends AppCompatActivity {

    EditText regEmail;
    EditText regPass;
    Button regBtn;
    TextView loginLink;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        regEmail = findViewById(R.id.regEmail);
        regPass = findViewById(R.id.regPass);
        regBtn = findViewById(R.id.signUpButton);
        loginLink = findViewById(R.id.loginLink);

        mAuth = FirebaseAuth.getInstance();


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginEmail =regEmail.getText().toString();
                String logPwd = regPass.getText().toString();

                if (loginEmail.isEmpty() || logPwd.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Some fields are empty.",LENGTH_LONG).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(loginEmail,logPwd)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegistrationPage.this,"Successfully registered.",LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(), NowRegistered.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), "Registration Failed, Try later.", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });
            }
        });

// For loginLink
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginPage.class));
            }
        });


    }
}
