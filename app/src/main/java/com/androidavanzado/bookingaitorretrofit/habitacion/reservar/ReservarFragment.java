package com.androidavanzado.bookingaitorretrofit.habitacion.reservar;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidavanzado.bookingaitorretrofit.R;
import com.androidavanzado.bookingaitorretrofit.beans.Reserva;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static android.content.Context.MODE_PRIVATE;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ID_HAB;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ID_USUARIO;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReservarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReservarFragment extends Fragment implements ReservarContract.View {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "idReserva";
    private static final String ARG_PARAM2 = "idUsuario";
    private static final String ARG_PARAM3 = "idHabitacion";
    private static final String ARG_PARAM4 = "in";
    private static final String ARG_PARAM5 = "out";

    private SharedPreferences sharedPreferences;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int idReserva, idUsuario, idHabitacion;
    private long in, out;

    private LinearLayout linearLayout;
    private Button btnReturn;
    private ProgressBar progressBar;
    private ReservarPresenter presenter;

    private Reserva r;

    public ReservarFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ReservarPresenter(this);

        if (getArguments() != null) {
            //idReserva = getArguments().getInt(ARG_PARAM1);
            idUsuario = getArguments().getInt(ARG_PARAM2);
        }
    }

    // TODO: Rename and change types and number of parameters
    public static ReservarFragment newInstance(int idUsuario) {
        ReservarFragment fragment = new ReservarFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM2, idUsuario);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservar, container, false);

        linearLayout = view.findViewById(R.id.ll_confirm);
        linearLayout.setVisibility(View.GONE);

        btnReturn = view.findViewById(R.id.btnReturn);

        progressBar = view.findViewById(R.id.pb_reserva_confirm);
        progressBar.setVisibility(View.VISIBLE);

        Bundle extras = this.getArguments();
        String inDate = extras.getString("CHECK_IN");
        String outDate = extras.getString("CHECK_OUT");
        int userId = extras.getInt(ID_USUARIO);


        java.util.Date dateIn = null;
        try {
            dateIn = new SimpleDateFormat("yyyy-MM-dd").parse(inDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDateIn= new java.sql.Date(dateIn.getTime());

        java.util.Date dateOut = null;
        try {
            dateOut = new SimpleDateFormat("yyyy-MM-dd").parse(outDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDateOut= new java.sql.Date(dateOut.getTime());



        int idHab = extras.getInt(ID_HAB);
        r= new Reserva();
        r.setIdUsuario(userId);
        r.setIdHabitacion(idHab);
        r.setIn(sqlDateIn);
        r.setOut(sqlDateOut);

        presenter.doReservaPresenter(r);
        return view;
    }

    @Override
    public void onSuccess(String message) {
        linearLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(Throwable throwable) {
        linearLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), R.string.internet_error, Toast.LENGTH_LONG).show();
    }
}