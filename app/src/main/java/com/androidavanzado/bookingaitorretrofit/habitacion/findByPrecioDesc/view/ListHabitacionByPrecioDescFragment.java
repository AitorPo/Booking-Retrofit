package com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioDesc.view;

import android.annotation.SuppressLint;
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
import android.widget.TextView;
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
import com.androidavanzado.bookingaitorretrofit.beans.Habitacion;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioDesc.contract.ListHabitacionByPrecioDescContract;
import com.androidavanzado.bookingaitorretrofit.habitacion.detailsHabitacion.view.DataHabitacionFragment;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByHotel.view.ListHabitacionAdapter;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioAsc.presenter.ListHabitacionByPrecioAscPresenter;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioAsc.view.ListHabitacionByPrecioAscFragment;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioDesc.presenter.ListHabitacionByPrecioDescPresenter;

import java.util.ArrayList;

public class ListHabitacionByPrecioDescFragment extends Fragment implements ListHabitacionByPrecioDescContract.View {
    private static final String TAG = "ListHabitacionByPrecioDescFragment";
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_ID_HOTEL = "ARG_ID_HOTEL";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private int mIdHotel = 0;
    private RecyclerView recyclerViewHabitacion;
    private ListHabitacionByPrecioDescPresenter presenter;
    private ListHabitacionAdapter adapter;
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
    public ListHabitacionByPrecioDescFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ListHabitacionByPrecioDescFragment newInstance(int mColumnCount) {
        ListHabitacionByPrecioDescFragment fragment = new ListHabitacionByPrecioDescFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, mColumnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ListHabitacionByPrecioDescPresenter(this);
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

        presenter.getHabitacionPrecioDesc();

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
        adapter = new ListHabitacionAdapter(habitacionArrayList, getContext(), idHabitacion -> getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_dashboard_fragment_container,
                        DataHabitacionFragment.newInstance(idHabitacion))
                .addToBackStack(null)
                .commit());

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
                presenter.getHabitacionPrecioDesc();
            }
        });
        Toast.makeText(getContext(), R.string.internet_error, Toast.LENGTH_LONG).show();
    }
}
