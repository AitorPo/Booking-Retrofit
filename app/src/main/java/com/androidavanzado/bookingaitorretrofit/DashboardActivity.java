package com.androidavanzado.bookingaitorretrofit;

import android.os.Bundle;
import android.util.Log;

import com.androidavanzado.bookingaitorretrofit.app.MyApp;
import com.androidavanzado.bookingaitorretrofit.ciudad.listCiudad.view.ListCiudadFragment;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.HotelFilterFragment;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.view.AllHotelFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class DashboardActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        Fragment f = null;

        switch (item.getItemId()){
            case R.id.navigation_home:
                f = ListCiudadFragment.newInstance(1);
                Log.d("navigation_home", "Inicio");
                break;
                // true porque empezamos desde esta página
            case R.id.navigation_hotels:
                Log.d("navigation_hotels", "Hoteles");
                f = AllHotelFragment.newInstance(1);
                break;
            case R.id.navigation_more:
                Log.d("navigation_more", "Más");
                f = HotelFilterFragment.newInstance(1);
                break;
            case R.id.navigation_profile:
                Log.d("navigation_profile", "Mi perfil");
                break;
        }

        if (f != null){
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


        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_bar_dashboard);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Ocultamos el título de la App
        getSupportActionBar().hide();

        // Seteamos el contenido de AllHotelFragment como view a cargar al crearse la vista de DashboardActivity
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_dashboard_fragment_container, ListCiudadFragment.newInstance(1))
                .commit();
    }
}