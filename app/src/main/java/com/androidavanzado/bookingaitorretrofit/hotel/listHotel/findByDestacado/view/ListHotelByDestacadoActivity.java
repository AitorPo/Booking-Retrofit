package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByDestacado.view;

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
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByDestacado.contract.ListHotelByDestacadoContract;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByDestacado.presenter.ListHotelByDestacadoPresenter;


import java.util.ArrayList;

public class ListHotelByDestacadoActivity extends AppCompatActivity implements ListHotelByDestacadoContract.View {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ListHotelAdapter adapter;
    private ListHotelByDestacadoPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hotel);


        presenter = new ListHotelByDestacadoPresenter(this);
        presenter.getHotelesDestacados();

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
