package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LogIn extends AppCompatActivity {


    EditText username;
    EditText password;
    Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("1234")) {
                    //Toast.makeText(LogIn.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LogIn.this,drawer_navigation.class);
                // Intent i = new Intent(LogIn.this,MainActivity.class);
                    i.putExtra("USER", username.getText().toString());
                    startActivity(i);
                } else {
                    Toast.makeText(LogIn.this, "Login Failed!,Please check ur credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
