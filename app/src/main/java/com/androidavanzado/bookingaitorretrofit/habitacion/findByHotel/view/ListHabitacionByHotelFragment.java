package com.androidavanzado.bookingaitorretrofit.habitacion.findByHotel.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.bookingaitorretrofit.R;
import com.androidavanzado.bookingaitorretrofit.beans.Habitacion;
//import com.androidavanzado.bookingaitorretrofit.ciudad.listCiudad.findAll.view.ListCiudadActivity;
import com.androidavanzado.bookingaitorretrofit.habitacion.detailsHabitacion.view.DataHabitacionFragment;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByHotel.contract.ListHabitacionByHotelContract;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByHotel.presenter.ListHabitacionByHotelPresenter;
/*import com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioAsc.view.ListHabitacionByPrecioAscActivity;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioDesc.view.ListHabitacionByPrecioDescActivity;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.view.ListHotelActivity;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByDestacado.view.ListHotelByDestacadoActivity;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByPuntuacion.view.ListHotelByPuntuacionActivity;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByReservas.view.ListHotelByReservasActivity;*/

import java.util.ArrayList;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.HABITACION_ID;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.HOTEL_ID;

public class ListHabitacionByHotelFragment extends Fragment implements ListHabitacionByHotelContract.View {
    private static final String TAG = "ListHabitacionByHotelFragment";
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_ID_HOTEL = "ARG_ID_HOTEL";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private int mIdHotel = 0;
    private RecyclerView recyclerViewHabitacion;
    private ListHabitacionByHotelPresenter presenter;
    private ListHabitacionByHotelAdapter adapter;
    private ConstraintLayout constraintLayout;
    private ProgressBar pbProgress;
    private Button btnRetry;
    private LinearLayout linearLayout, llNoData;
    private TextView tvError;

    private int idHotel;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ListHabitacionByHotelFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ListHabitacionByHotelFragment newInstance(int idHotel) {
        ListHabitacionByHotelFragment fragment = new ListHabitacionByHotelFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID_HOTEL, idHotel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ListHabitacionByHotelPresenter(this);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            idHotel = getArguments().getInt(ARG_ID_HOTEL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_habitacion, container, false);

        Context context = view.getContext();

        constraintLayout = view.findViewById(R.id.constraint_habitaciones_por_hotel);
        constraintLayout.setVisibility(View.GONE);

        pbProgress = view.findViewById(R.id.pbProgressHabitaciones);
        pbProgress.setVisibility(View.VISIBLE);

        linearLayout = view.findViewById(R.id.ll_error);
        linearLayout.setVisibility(View.GONE);
        llNoData = view.findViewById(R.id.ll_no_data);
        llNoData.setVisibility(View.GONE);

        btnRetry = view.findViewById(R.id.btnRetry);

        presenter.getHabitacionList(idHotel);

        recyclerViewHabitacion = view.findViewById(R.id.recyclerViewHabitacion);
        recyclerViewHabitacion.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    @Override
    public void onSuccess(ArrayList<Habitacion> habitacionArrayList) {
        if (habitacionArrayList.isEmpty()) {
            llNoData.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "No existen habitaciones actualmente", Toast.LENGTH_SHORT).show();
            pbProgress.setVisibility(View.GONE);
            return;
        }
        constraintLayout.setVisibility(View.VISIBLE);
        pbProgress.setVisibility(View.GONE);
        linearLayout.setVisibility(View.GONE);
        adapter = new ListHabitacionByHotelAdapter(habitacionArrayList, getContext(), (id, position) -> {
            Intent intent = new Intent(getContext(), DataHabitacionFragment.class);
            int idHabitacion = habitacionArrayList.get(position).getIdHabitacion();
            int idHotel = habitacionArrayList.get(position).getIdHotel();
            intent.putExtra(HOTEL_ID, idHotel);
            intent.putExtra(HABITACION_ID, idHabitacion);
            startActivity(intent);
        });

        recyclerViewHabitacion.setAdapter(adapter);
    }

    @SuppressLint("LongLogTag")
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
                presenter.getHabitacionList(idHotel);
            }
        });
        Toast.makeText(getContext(), R.string.internet_error, Toast.LENGTH_LONG).show();
    }
}
