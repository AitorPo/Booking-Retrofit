package com.androidavanzado.bookingaitorretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class Splashscreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                Intent intent = new Intent(getBaseContext(), DashboardActivity.class);
                startActivity(intent);
            }
        }, 4000);
    }
}
