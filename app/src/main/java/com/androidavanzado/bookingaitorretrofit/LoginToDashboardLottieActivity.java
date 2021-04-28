package com.androidavanzado.bookingaitorretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.androidavanzado.bookingaitorretrofit.DashboardActivity;
import com.androidavanzado.bookingaitorretrofit.R;

public class LoginToDashboardLottieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_to_dashboard_lottie);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                transitions();
            }
        }, 3000);


    }

    public void transitions(){
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginToDashboardLottieActivity.this);
        Intent intent = new Intent(LoginToDashboardLottieActivity.this, DashboardActivity.class);
        startActivity(intent, options.toBundle());
    }
}