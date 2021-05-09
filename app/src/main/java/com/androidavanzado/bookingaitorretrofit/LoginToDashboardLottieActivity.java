package com.androidavanzado.bookingaitorretrofit;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.androidavanzado.bookingaitorretrofit.utils.Util;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ID_USUARIO;

public class LoginToDashboardLottieActivity extends AppCompatActivity {
    private static final String TAG = "LoginToDashboardLottieActivity";
    private String email;
    private int idUsuario;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_to_dashboard_lottie);

        // Ocultamos el título de la App
        //getSupportActionBar().hide();

        sharedPreferences = getSharedPreferences("Preferences", MODE_PRIVATE);

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
        idUsuario = Util.getUserIntPrefs(sharedPreferences);
        intent.putExtra("Email", email);
        intent.putExtra(ID_USUARIO, idUsuario);
        Log.d(TAG, "transitions: " + idUsuario);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent, options.toBundle());
    }
}