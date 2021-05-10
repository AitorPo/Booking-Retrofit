package com.androidavanzado.bookingaitorretrofit.reserva.myReservas;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidavanzado.bookingaitorretrofit.R;
import com.androidavanzado.bookingaitorretrofit.beans.Reserva;
import com.androidavanzado.bookingaitorretrofit.habitacion.detailsHabitacion.view.DataHabitacionFragment;
import com.androidavanzado.bookingaitorretrofit.reserva.deleteReserva.DeleteReservaFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyReservasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyReservasFragment extends Fragment implements MyReservasContract.View{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "idUsuario";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "MyReservasFragment";

    private RecyclerView recyclerViewReserva;
    private MyReservasAdapter adapter;
    private ConstraintLayout constraintLayout;
    private ProgressBar pbProgress;
    private Button btnRetry;
    private LinearLayout linearLayout, llNoData;
    private MyReservasPresenter presenter;
    private TextView tvDelete;
    private Bundle extras;

    private int idUsuario;

    public MyReservasFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MyReservasFragment newInstance(int idUsuario) {
        MyReservasFragment fragment = new MyReservasFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, idUsuario);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MyReservasPresenter(this);
        if (getArguments() != null) {
            idUsuario = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.reserva_list, container, false);

        Context context = view.getContext();

        constraintLayout = view.findViewById(R.id.constraint_reservas);
        constraintLayout.setVisibility(View.GONE);

        pbProgress = view.findViewById(R.id.pbProgressReserva);
        pbProgress.setVisibility(View.VISIBLE);

        linearLayout = view.findViewById(R.id.ll_error);
        linearLayout.setVisibility(View.GONE);
        llNoData = view.findViewById(R.id.ll_no_data);
        llNoData.setVisibility(View.GONE);

        btnRetry = view.findViewById(R.id.btnRetry);
        presenter.getMyReservasList(idUsuario);

        recyclerViewReserva = view.findViewById(R.id.recyclerViewReservas);
        recyclerViewReserva.setLayoutManager(new LinearLayoutManager(context));
        return view;
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



    private void showError() {
        linearLayout.setVisibility(View.VISIBLE);
        pbProgress.setVisibility(View.GONE);
        btnRetry.setClickable(true);

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getMyReservasList(idUsuario);
            }
        });
        Toast.makeText(getContext(), R.string.internet_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(ArrayList<Reserva> reservas) {
        if (reservas.isEmpty()) {
            llNoData.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "No existen reservas actualmente", Toast.LENGTH_SHORT).show();
            pbProgress.setVisibility(View.GONE);
            return;
        }
        crossfade();
        linearLayout.setVisibility(View.GONE);

        for (Reserva r : reservas){
            int idReserva = r.getIdReserva();
            int idHabitacion = r.getIdHabitacion();
            extras = new Bundle();
            extras.putInt("idHabitacion", idHabitacion);
            extras.putInt("idReserva", idReserva);
        }
        adapter = new MyReservasAdapter(reservas, getContext(), new MyReservasAdapter.OnCardClickListener() {
            @Override
            public void onCardClick(int idHabitacion) {
                DataHabitacionFragment dataHabitacionFragment = DataHabitacionFragment.newInstance(extras.getInt("idHabitacion"));
                dataHabitacionFragment.setArguments(extras);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.activity_dashboard_fragment_container, dataHabitacionFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        recyclerViewReserva.setAdapter(adapter);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.e(TAG, throwable.toString());
        showError();
    }
}