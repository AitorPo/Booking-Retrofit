package com.androidavanzado.bookingaitorretrofit;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidavanzado.bookingaitorretrofit.usuario.LoginActivity;


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
                //startActivity(intent);
                transitions();
            }
        }, 4000);
    }

    public void transitions(){
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Splashscreen.this);
        Intent intent = new Intent(Splashscreen.this, LoginActivity.class);
        startActivity(intent, options.toBundle());
    }
}
