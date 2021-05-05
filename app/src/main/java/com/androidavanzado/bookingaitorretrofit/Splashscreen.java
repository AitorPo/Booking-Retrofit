package com.androidavanzado.bookingaitorretrofit;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidavanzado.bookingaitorretrofit.usuario.LoginActivity;
import com.androidavanzado.bookingaitorretrofit.utils.Util;


public class Splashscreen extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        sharedPreferences = getSharedPreferences("Preferences", MODE_PRIVATE);

        // Ocultamos el título de la App
        //getSupportActionBar().hide();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                //startActivity(intent);
                manageIntents();
            }
        }, 4000);
    }

    private void manageIntents(){
        Intent intentToLogin = new Intent(this, LoginActivity.class);
        Intent intentToDashboard = new Intent(this, DashboardActivity.class);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Splashscreen.this);

        if (!Util.getUserMailPrefs(sharedPreferences).isEmpty() &&
               !Util.getUserPasswordPrefs(sharedPreferences).isEmpty()){
            startActivity(intentToDashboard, options.toBundle());
        } else {
            startActivity(intentToLogin, options.toBundle());
        }
        finish();
    }

}
