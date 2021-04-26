package com.androidavanzado.bookingaitorretrofit.ciudad.listCiudad.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.bookingaitorretrofit.R;
import com.androidavanzado.bookingaitorretrofit.beans.Ciudad;
import com.androidavanzado.bookingaitorretrofit.ciudad.listCiudad.contract.ListCiudadContract;
import com.androidavanzado.bookingaitorretrofit.ciudad.listCiudad.presenter.ListCiudadPresenter;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.presenter.ListHotelPresenter;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.view.AllHotelFragment;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.view.ListHotelAdapter;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByCiudad.view.ListHotelByCiudadFragment;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByDestacado.view.ListHotelByDestacadoActivity;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByPuntuacion.view.ListHotelByPuntuacionActivity;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByReservas.view.ListHotelByReservasActivity;

import java.util.ArrayList;

public class ListCiudadFragment extends Fragment implements ListCiudadContract.View {


    private final String TAG = "AllHotelFragment";
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private RecyclerView recyclerViewCiudad;
    private LinearLayout linearLayout;
    private ListCiudadAdapter adapter;
    private ListCiudadPresenter presenter;
    private Button btnRetry;
    private ProgressBar pbProgress;
    private ConstraintLayout constraintLayout;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ListCiudadFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ListCiudadFragment newInstance(int columnCount) {
        ListCiudadFragment fragment = new ListCiudadFragment();
        Bundle args = new Bundle();

        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ListCiudadPresenter(this);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_ciudad, container, false);

        Context context = view.getContext();

        constraintLayout = view.findViewById(R.id.constraint_all_ciudades);
        constraintLayout.setVisibility(View.GONE);

        pbProgress = view.findViewById(R.id.pbProgress);
        pbProgress.setVisibility(View.VISIBLE);

        linearLayout = view.findViewById(R.id.ll_error);
        linearLayout.setVisibility(View.GONE);

        btnRetry = view.findViewById(R.id.btnRetry);
        presenter.getCiudades();

        recyclerViewCiudad = view.findViewById(R.id.recyclerViewHabitacion);
        recyclerViewCiudad.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    @Override
    public void onSuccess(ArrayList<Ciudad> ciudadArrayList) {
        constraintLayout.setVisibility(View.VISIBLE);
        pbProgress.setVisibility(View.GONE);
        linearLayout.setVisibility(View.GONE);
        adapter = new ListCiudadAdapter(ciudadArrayList, getContext(), idCiudad -> {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_dashboard_fragment_container,
                            ListHotelByCiudadFragment.newInstance(idCiudad))
                            .addToBackStack(null)
                            .commit();
        });
        recyclerViewCiudad.setAdapter(adapter);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.e(TAG, throwable.toString());
        showError();
    }

    public void showError(){

        linearLayout.setVisibility(View.VISIBLE);
        pbProgress.setVisibility(View.GONE);
        btnRetry.setClickable(true);

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getCiudades();
            }
        });
        Toast.makeText(getContext(), R.string.internet_error, Toast.LENGTH_LONG).show();
    }
}