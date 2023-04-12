package com.example.delifood.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.delifood.MainActivity;
import com.example.delifood.R;

public class RegistrationActivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        button = findViewById(R.id.button);

    }

    public void login(View view) {
        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
    }

    public void home(View view) {
        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
    }
}