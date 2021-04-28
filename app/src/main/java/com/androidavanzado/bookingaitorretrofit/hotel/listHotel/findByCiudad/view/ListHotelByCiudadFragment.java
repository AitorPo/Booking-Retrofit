package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByCiudad.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.bookingaitorretrofit.R;
import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.hotel.detailsHotel.view.HotelDataFragment;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.view.ListHotelAdapter;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByCiudad.contract.ListHotelByCiudadContract;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByCiudad.presenter.ListHotelByCiudadPresenter;

import java.util.ArrayList;

public class ListHotelByCiudadFragment extends Fragment implements ListHotelByCiudadContract.View {

    private static final String TAG = "ListHotelByCiudadFragment";
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_ID_CIUDAD = "ARG_ID_CIUDAD";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private int mIdHotel = 0;
    private RecyclerView recyclerView;
    private ConstraintLayout constraintLayout;
    private ProgressBar pbProgress;
    private Button btnRetry;
    private LinearLayout linearLayout, llError;
    private TextView tvError;

    private ListHotelByCiudadPresenter presenter;
    private ListHotelAdapter adapter;

    public static int idCiudad;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ListHotelByCiudadFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ListHotelByCiudadFragment newInstance(int idCiudad) {
        ListHotelByCiudadFragment fragment = new ListHotelByCiudadFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID_CIUDAD, idCiudad);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new ListHotelByCiudadPresenter(this);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            idCiudad = getArguments().getInt(ARG_ID_CIUDAD);
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
        llError = view.findViewById(R.id.ll_error);
        llError.setVisibility(View.GONE);

        btnRetry = view.findViewById(R.id.btnRetry);

        presenter.getHotelByCiudad(idCiudad);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    @Override
    public void onSuccess(ArrayList<Hotel> hotelArrayList) {
        crossfade();
        linearLayout.setVisibility(View.GONE);
        adapter = new ListHotelAdapter(hotelArrayList, getContext(), idHotelCiduad -> {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_dashboard_fragment_container,
                            HotelDataFragment.newInstance(idHotelCiduad))
                    // Para que recuerde el contenido anterior del fragment
                    .addToBackStack(null)
                    .commit();
        });
        recyclerView.setAdapter(adapter);
    }

    private void crossfade() {

        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        constraintLayout.setAlpha(0f);
        constraintLayout.setVisibility(View.VISIBLE);

        // Animate the content view to 100% opacity, and clear any animation
        // listener set on the view.
        constraintLayout.animate()
                .alpha(1f)
                .setDuration(1000)
                .setListener(null);

        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        pbProgress.animate()
                .alpha(0f)
                .setDuration(1000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        pbProgress.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public void onFailure(Throwable throwable) {
        showError();
    }

    public void showError() {
        linearLayout.setVisibility(View.VISIBLE);
        pbProgress.setVisibility(View.GONE);
        btnRetry.setClickable(true);

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getHotelByCiudad(idCiudad);
            }
        });
        Toast.makeText(getContext(), R.string.internet_error, Toast.LENGTH_LONG).show();
    }
}
