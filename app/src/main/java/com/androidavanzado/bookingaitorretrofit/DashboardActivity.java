package com.androidavanzado.bookingaitorretrofit;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.androidavanzado.bookingaitorretrofit.ciudad.listCiudad.view.ListCiudadFragment;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.HotelFilterFragment;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.view.AllHotelFragment;
import com.androidavanzado.bookingaitorretrofit.usuario.LoginActivity;
import com.androidavanzado.bookingaitorretrofit.utils.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {
    private static final String TAG = "DashboardActivity";

    private SharedPreferences sharedPreferences;
    private Toolbar toolbar;
    private TextView tvUsername;
    private String email;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        Fragment f = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                f = ListCiudadFragment.newInstance(1);
                Log.d("navigation_home", "Inicio");
                break;
            // true porque empezamos desde esta página
            case R.id.navigation_hotels:
                Log.d("navigation_hotels", "Hoteles");
                f = AllHotelFragment.newInstance(1);
                break;
            case R.id.navigation_reservas:
                Log.d("navigation_reservas", "Reservas");
                f = AllHotelFragment.newInstance(1);
                break;
            case R.id.navigation_more:
                Log.d("navigation_more", "Más");
                f = HotelFilterFragment.newInstance(1);
                break;
        }

        if (f != null) {
            //if (f == HotelDataFragment.newInstance(hotel.getIdHotel()))
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_dashboard_fragment_container, f)
                    .commit();
            return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initComponents();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_bar_dashboard);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Ocultamos el título de la App
        //getSupportActionBar().hide();
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_logout:
                        logout();
                        return true;
                    case R.id.menu_forget_logout:
                        removePreferences();
                        logout();
                        return true;
                    default:
                return false;
                }
            }
        });

        // Seteamos el contenido de AllHotelFragment como view a cargar al crearse la vista de DashboardActivity
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_dashboard_fragment_container, ListCiudadFragment.newInstance(1))
                .commit();
    }

    private void initComponents(){
        sharedPreferences = getSharedPreferences("Preferences", MODE_PRIVATE);

        // Seteamos el menú de "Log out" dentro de la Toolbar "personalizada" de nuestro layout
        // ya que al ocultar la ActionBar de la app no nos aparecería dicho menú.
        toolbar = findViewById(R.id.activity_dashboard_tb);
        toolbar.inflateMenu(R.menu.logout_menu);
        tvUsername = findViewById(R.id.activity_dashboard_tvUsername);
        setToolbarEmail();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void logout(){
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DashboardActivity.this);

        Intent intent = new Intent(this, LoginActivity.class);
        // Borramos el historial al pulsar atrás para no producir brecha de seguridad.
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent, options.toBundle());
    }

    private void removePreferences(){
        sharedPreferences.edit().clear().apply();
    }

    private void setToolbarEmail(){
        email = getIntent().getStringExtra("Email");
        if (Util.getUserMailPrefs(sharedPreferences).isEmpty())
            tvUsername.setText(email);
        else
            tvUsername.setText(Util.getUserMailPrefs(sharedPreferences));
    }
}