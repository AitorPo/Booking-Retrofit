package com.androidavanzado.bookingaitorretrofit;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class LoginToDashboardLottieActivity extends AppCompatActivity {
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_to_dashboard_lottie);

        // Ocultamos el título de la App
        //getSupportActionBar().hide();



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                transitions();
            }
        }, 3000);

    }

    private void transitions(){
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginToDashboardLottieActivity.this);
        Intent intent = new Intent(LoginToDashboardLottieActivity.this, DashboardActivity.class);
        email = getIntent().getStringExtra("Email");
        intent.putExtra("Email", email);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent, options.toBundle());
    }
}