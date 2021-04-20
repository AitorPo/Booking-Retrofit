package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidavanzado.bookingaitorretrofit.R;
import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.hotel.detailsHotel.view.HotelDataFragment;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.contract.ListHotelContract;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.presenter.ListHotelPresenter;
import com.androidavanzado.bookingaitorretrofit.utils.OnDetailExternalListener;

import java.util.ArrayList;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ID_HOTEL;

/**
 * A fragment representing a list of Items.
 */
public class AllHotelFragment extends Fragment implements ListHotelContract.View {

    private final String TAG = "AllHotelFragment";
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private RecyclerView recyclerView;
    private ListHotelAdapter adapter;
    private ListHotelPresenter presenter;
    private LinearLayout linearLayout;
    private Button btnRetry;
    private ProgressBar pbProgress;
    private ConstraintLayout constraintLayout;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AllHotelFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static AllHotelFragment newInstance(int columnCount) {
        AllHotelFragment fragment = new AllHotelFragment();
        Bundle args = new Bundle();

        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ListHotelPresenter(this);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_hotel, container, false);

        Context context = view.getContext();

        constraintLayout = view.findViewById(R.id.constraint_all_hoteles);
        constraintLayout.setVisibility(View.GONE);

        pbProgress = view.findViewById(R.id.pbProgress);
        pbProgress.setVisibility(View.VISIBLE);

        linearLayout = view.findViewById(R.id.ll_error);
        linearLayout.setVisibility(View.GONE);

        btnRetry = view.findViewById(R.id.btnRetry);
        presenter.getListHotel();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    @Override
    public void onSuccess(ArrayList<Hotel> hoteles) {
        constraintLayout.setVisibility(View.VISIBLE);
        pbProgress.setVisibility(View.GONE);
        linearLayout.setVisibility(View.GONE);
        adapter = new ListHotelAdapter(hoteles, getContext(), idHotel -> getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_dashboard_fragment_container,
                        HotelDataFragment.newInstance(idHotel))
                                // Para que recuerde el contenido anterior del fragment
                                .addToBackStack(null)
                                .commit());
        recyclerView.setAdapter(adapter);
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
                presenter.getListHotel();
            }
        });
        Toast.makeText(getContext(), R.string.internet_error, Toast.LENGTH_LONG).show();
    }
}