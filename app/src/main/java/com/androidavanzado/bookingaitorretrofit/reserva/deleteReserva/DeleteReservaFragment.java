package com.androidavanzado.bookingaitorretrofit.reserva.deleteReserva;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
import com.androidavanzado.bookingaitorretrofit.reserva.reservar.ReservarPresenter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeleteReservaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeleteReservaFragment extends Fragment implements DeleteReservaContract.View{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "idReserva";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "DeleteReservaFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private LinearLayout linearLayout;
    private Button btnReturn;
    private ProgressBar progressBar;
    private DeleteReservaPresenter presenter;
    private TextView tvConfirm;
    private int idReserva;

    public DeleteReservaFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DeleteReservaFragment newInstance(int idReserva) {
        DeleteReservaFragment fragment = new DeleteReservaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, idReserva);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DeleteReservaPresenter(this);
        if (getArguments() != null) {
            idReserva = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservar, container, false);

        linearLayout = view.findViewById(R.id.ll_confirm);
        linearLayout.setVisibility(View.GONE);

        tvConfirm = view.findViewById(R.id.tvConfirm);

        btnReturn = view.findViewById(R.id.btnReturn);

        Bundle extras = this.getArguments();
        int idReserva = extras.getInt("idReserva");
        Log.d(TAG, "onCreateView: " + idReserva);
        presenter.deleteReservaPresenter(idReserva);
        progressBar = view.findViewById(R.id.pb_reserva_confirm);
        progressBar.setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public void onSuccess(String string) {
        linearLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        tvConfirm.setText(string);
    }

    @Override
    public void onFailure(Throwable throwable) {
        linearLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), R.string.internet_error, Toast.LENGTH_LONG).show();
    }
}