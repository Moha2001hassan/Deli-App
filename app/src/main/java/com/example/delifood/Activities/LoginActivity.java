package com.example.delifood.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.delifood.MainActivity;
import com.example.delifood.R;

public class LoginActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = findViewById(R.id.button);

    }

    public void register(View view) {
        startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
    }

    public void home(View view) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}