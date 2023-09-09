package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ShowUserData extends AppCompatActivity {

    Button logout;
    FirebaseAuth mAuth;
    FirebaseUser user;
    TextView showLoggedData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_user_data);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        showLoggedData = findViewById(R.id.ShowLoggedInData);

        if (user==null) {
            String receiveLogData = user.getEmail().toString();
            showLoggedData.setText(receiveLogData);
        }

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuth.signOut();

                startActivity(new Intent(getApplicationContext(), LoginPage.class));
            }
        });
    }
}
