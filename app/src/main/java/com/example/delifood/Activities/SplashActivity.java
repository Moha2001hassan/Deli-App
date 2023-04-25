package com.example.delifood.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.delifood.R;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView app_name_text;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = findViewById(R.id.imageView);
        app_name_text = findViewById(R.id.app_name_text);
        progressBar = findViewById(R.id.progressBar);


        imageView.setVisibility(View.INVISIBLE);
        app_name_text.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);


        int START_TIME = 1500;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fade_in);
                imageView.startAnimation(fade);
                app_name_text.startAnimation(fade);
                imageView.setVisibility(View.VISIBLE);
                app_name_text.setVisibility(View.VISIBLE);

            }
        }, START_TIME);

        int LAUNCH_TIME = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
                Intent intentMain = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intentMain);

                finish();
            }
        }, LAUNCH_TIME);
    }
}