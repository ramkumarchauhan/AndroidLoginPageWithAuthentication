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

public class LoginPage extends AppCompatActivity {

    EditText logEmail;
    EditText logPass;
    Button loginBtn;
    TextView regLink;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        logEmail = findViewById(R.id.loginEmail);
        logPass = findViewById(R.id.loginPass);
        loginBtn = findViewById(R.id.loginButton);
        regLink = findViewById(R.id.signUpLink);

        mAuth = FirebaseAuth.getInstance();

//        For Login Button

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginEmail =logEmail.getText().toString();
                String logPwd = logPass.getText().toString();

                if (loginEmail.isEmpty() || logPwd.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Some fields are empty.",LENGTH_LONG).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(loginEmail,logPwd)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),"Successfully Logged in.",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), ShowUserData.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), "You're not registered.", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });
            }
        });

//        For SignUp link

        regLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegistrationPage.class);
                startActivity(i);
            }
        });



    }
}
