package com.androidavanzado.bookingaitorretrofit.habitacion.detailsHabitacion.view;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;

import com.androidavanzado.bookingaitorretrofit.BuildConfig;
import com.androidavanzado.bookingaitorretrofit.R;
import com.androidavanzado.bookingaitorretrofit.beans.Habitacion;
import com.androidavanzado.bookingaitorretrofit.beans.Reserva;
import com.androidavanzado.bookingaitorretrofit.habitacion.detailsHabitacion.contract.DetailsHabitacionContract;
import com.androidavanzado.bookingaitorretrofit.habitacion.detailsHabitacion.presenter.DetailsHabitacionPresenter;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByHotel.view.ListHabitacionByHotelFragment;
import com.androidavanzado.bookingaitorretrofit.habitacion.reservar.ReservarFragment;
import com.androidavanzado.bookingaitorretrofit.utils.Util;
import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static android.content.Context.MODE_PRIVATE;
import static com.androidavanzado.bookingaitorretrofit.DashboardActivity.idUsuario;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.EU_DATE_FORMAT;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ID_HAB;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ID_USUARIO;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.IMG_FORMAT;

//import com.androidavanzado.bookingaitorretrofit.ciudad.listCiudad.findAll.view.ListCiudadActivity;
/*import com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioAsc.view.ListHabitacionByPrecioAscActivity;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioDesc.view.ListHabitacionByPrecioDescActivity;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.view.ListHotelActivity;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByDestacado.view.ListHotelByDestacadoActivity;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByPuntuacion.view.ListHotelByPuntuacionActivity;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByReservas.view.ListHotelByReservasActivity;*/


public class DataHabitacionFragment extends Fragment implements DetailsHabitacionContract.View {
    private DetailsHabitacionPresenter presenter;
    private ImageView ivHotel;
    private TextView tvPuntuacionCount,tvFechas, tvDireccion,
            tvReservasCount, tvNumHabitacionsCount, tvDescripcion;

    private ConstraintLayout detailConstraint;
    private ProgressBar pbDetails;
    private LinearLayout linearLayout;
    private Button btnRetry, tvLink, btnSave;
    private MaterialCardView materialCardViewReserva, materialCardViewSaveReserva;

    private MaterialDatePicker.Builder<Pair<Long, Long>> materialDateBuilder;
    private MaterialDatePicker materialDatePicker;

    private Toolbar toolbar;


    // TODO: Rename parameter arguments, choose names that match
    private int idHabitacion;
    private LocalDate checkIn;
    private LocalDate checkOut;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "idHabitacion";
    private static final String ARG_PARAM2 = "nombre";
    private static final String ARG_PARAM3 = "direccion";
    private static final String ARG_PARAM4 = "reservasCount";
    private static final String ARG_PARAM5 = "numHabitaciones";
    private static final String ARG_PARAM6 = "descripcion";
    private static final String ARG_PARAM7 = "ivHotel";
    private static final String ARG_PARAM8 = "puntuacion";
    private static final String ARG_PARAM9 = "link";

    private static final String TAG = "DetailsHabitacionFragment";
    private SharedPreferences sharedPreferences;

    public DataHabitacionFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DetailsHabitacionPresenter(this);
        sharedPreferences = this.getActivity().getSharedPreferences("Preferences", MODE_PRIVATE);

        if (getArguments() != null) {
            idHabitacion = getArguments().getInt(ARG_PARAM1);
        }
    }

    public static DataHabitacionFragment newInstance(int idHabitacion) {
        DataHabitacionFragment fragment = new DataHabitacionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, idHabitacion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.habitacion_item_details, container, false);

        //detailConstraint = view.findViewById(R.id.detail_habitacion_constraint);
        //detailConstraint.setVisibility(View.GONE);
        sharedPreferences = this.getActivity().getSharedPreferences("Preferences", MODE_PRIVATE);

        toolbar = view.findViewById(R.id.toolbarDetailsHabitacion);
        materialCardViewSaveReserva = view.findViewById(R.id.material_cv_btn_guardar_reserva);
        materialCardViewSaveReserva.setVisibility(View.GONE);

        materialCardViewReserva = view.findViewById(R.id.material_cv_btn_reservar);
        materialCardViewReserva.setVisibility(View.VISIBLE);


        /*dateRangePicker = MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Selecciona fechas")
                .setSelection(
                        Pair.create(MaterialDatePicker.thisMonthInUtcMilliseconds(),
                                MaterialDatePicker.todayInUtcMilliseconds())
                ).build();*/

        materialDateBuilder = MaterialDatePicker.Builder.dateRangePicker();
        materialDateBuilder.setTitleText("Selecciona fechas");

        materialDatePicker = materialDateBuilder.build();

        pbDetails = view.findViewById(R.id.pb_habitacion_detail);
        pbDetails.setVisibility(View.VISIBLE);

        linearLayout = view.findViewById(R.id.ll_error);
        linearLayout.setVisibility(View.GONE);

        btnRetry = view.findViewById(R.id.btnRetry);

        tvPuntuacionCount = view.findViewById(R.id.tvPuntuacionCount);
        tvDireccion = view.findViewById(R.id.tvDireccion);
        tvReservasCount = view.findViewById(R.id.tvReservasCount);
        tvNumHabitacionsCount = view.findViewById(R.id.tvNumHabitacionsCount);
        tvDescripcion = view.findViewById(R.id.tvDescripcion);
        ivHotel = view.findViewById(R.id.ivHotel);
        tvLink = view.findViewById(R.id.tvLink);
        btnSave = view.findViewById(R.id.btnSave);
        tvFechas = view.findViewById(R.id.tvFechas);

        presenter.getDetailsHabitacion(idHabitacion);

        return view;
    }

    @Override
    public void onSuccess(Habitacion habitacion) {
        //detailConstraint.setVisibility(View.VISIBLE);
        pbDetails.setVisibility(View.GONE);
        linearLayout.setVisibility(View.GONE);

        tvPuntuacionCount.setText(String.valueOf(habitacion.getNumHabitacion()));
        Glide.with(this).load(BuildConfig.BOOKING_API_PHOTO_HABITACION_URL + habitacion.getFoto() + IMG_FORMAT)
                .centerInside()
                .centerCrop()
                .into(ivHotel);

        tvDireccion.setText(String.valueOf(habitacion.getCamas()));
        tvReservasCount.setText(String.valueOf(habitacion.getPrecio()));
        if (habitacion.isOcupada()) {
            tvNumHabitacionsCount.setText(" No ");
            tvLink.setEnabled(false);
        } else {
            tvNumHabitacionsCount.setText(" Sí ");
            tvLink.setEnabled(true);
        }
        tvDescripcion.setText(habitacion.getDescripcion());
        tvLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.activity_dashboard_fragment_container,
                                ListHabitacionByHotelFragment.newInstance(1))
                        .addToBackStack(null)
                        .commit();*/
                reservar();

            }
        });
    }

    @SuppressLint({"LongLogTag", "SetTextI18n"})
    private void reservar(){
        materialDatePicker.show(getActivity().getSupportFragmentManager(), "MATERIAL_DATE_PICKER");

        DateTimeFormatter format = DateTimeFormatter.ofPattern(EU_DATE_FORMAT);
        materialDatePicker.addOnPositiveButtonClickListener((MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>) selection -> {
            Log.d(TAG, "onPositiveButtonClick: " + materialDatePicker.getHeaderText());
            Long in = selection.first;
            Long out = selection.second;
            Log.d(TAG, "in " + in);
            Log.d(TAG, "out " + out);
            checkIn = Instant.ofEpochMilli(in).atZone(ZoneId.systemDefault()).toLocalDate();
            checkOut = Instant.ofEpochMilli(out).atZone(ZoneId.systemDefault()).toLocalDate();
            Long epoc = checkIn.toEpochDay();
            Log.d(TAG, "in - LocalDate " + checkIn);
            Log.d(TAG, "out - LocalDate " + checkOut);
            Log.d(TAG, "in - epoc " + epoc);
            materialCardViewReserva.setVisibility(View.GONE);
            materialCardViewSaveReserva.setVisibility(View.VISIBLE);
            tvFechas.setText(checkIn.format(format) + " - " + checkOut.format(format));

        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveReserva();
            }
        });
    }

    private void saveReserva(){
        int idUsuario = Util.getUserIntPrefs(sharedPreferences);
        Log.d(TAG, "saveReserva: " + idUsuario);

        Bundle bundle = new Bundle();
        bundle.putString("CHECK_IN", checkIn.toString());
        bundle.putString("CHECK_OUT", checkOut.toString());
        bundle.putInt(ID_HAB, idHabitacion);
        bundle.putInt(ID_USUARIO, idUsuario);


        ReservarFragment reservarFragment = ReservarFragment.newInstance(idUsuario);
        reservarFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_dashboard_fragment_container,
                        reservarFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onFailure(Throwable throwable) {
        showError();
    }

    private void showError() {
        pbDetails.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);

        btnRetry.setClickable(true);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getDetailsHabitacion(idHabitacion);
            }
        });
        Toast.makeText(getActivity(), R.string.internet_error, Toast.LENGTH_LONG).show();
    }


}
