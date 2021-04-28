package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
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
import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.data.local.HotelRoomDataBase;
import com.androidavanzado.bookingaitorretrofit.hotel.detailsHotel.view.HotelDataFragment;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.contract.ListHotelContract;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.presenter.ListHotelPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

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
    private TextView tvCiudad;
    private int animationTime;
    private Context context;

    private List<Hotel> hotelArrayList;


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

        context = view.getContext();

        hotelArrayList = new ArrayList<>();
        animationTime = getResources().getInteger(
                R.integer.config_navAnimTime
        );

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

    private void loadHotelsFromRoomDb(){
        hotelArrayList = HotelRoomDataBase.getInstance(context).getHotelDAO().getHotelsRoomDatabase();

        for (Hotel hotel : hotelArrayList){
            Log.d(TAG, hotel.toString());
        }
        adapter.setData(hotelArrayList);
    }

    @Override
    public void onSuccess(ArrayList<Hotel> hoteles) {
        //constraintLayout.setVisibility(View.VISIBLE);
        //pbProgress.setVisibility(View.GONE);
        crossfade();
        linearLayout.setVisibility(View.GONE);
        adapter = new ListHotelAdapter(hoteles, getContext(), idHotel -> getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_dashboard_fragment_container,
                        HotelDataFragment.newInstance(idHotel))
                // Para que recuerde el contenido anterior del fragment
                .addToBackStack(null)
                .commit());
        recyclerView.setAdapter(adapter);
        loadHotelsFromRoomDb();
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
        Log.e(TAG, throwable.toString());
        showError();
    }

    public void showError() {

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