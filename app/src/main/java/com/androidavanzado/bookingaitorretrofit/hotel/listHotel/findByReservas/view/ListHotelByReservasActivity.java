package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByReservas.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.bookingaitorretrofit.R;
import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.view.ListHotelAdapter;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByDestacado.view.ListHotelByDestacadoActivity;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByPuntuacion.view.ListHotelByPuntuacionActivity;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByReservas.contract.ListHotelByReservasContract;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByReservas.presenter.ListHotelByReservasPresenter;

import java.util.ArrayList;

public class ListHotelByReservasActivity extends AppCompatActivity implements ListHotelByReservasContract.View {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ListHotelAdapter adapter;
    private ListHotelByReservasPresenter presenter;

    public static int idHotel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hotel);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void onSuccess(ArrayList<Hotel> hotelArrayList) {

    }

    @Override
    public void onFailure(Throwable throwable) {

    }
}
