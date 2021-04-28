package com.androidavanzado.bookingaitorretrofit.habitacion.detailsHabitacion.view;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.androidavanzado.bookingaitorretrofit.R;
import com.androidavanzado.bookingaitorretrofit.beans.Habitacion;
//import com.androidavanzado.bookingaitorretrofit.ciudad.listCiudad.findAll.view.ListCiudadActivity;
import com.androidavanzado.bookingaitorretrofit.habitacion.detailsHabitacion.contract.DetailsHabitacionContract;
import com.androidavanzado.bookingaitorretrofit.habitacion.detailsHabitacion.presenter.DetailsHabitacionPresenter;
/*import com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioAsc.view.ListHabitacionByPrecioAscActivity;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioDesc.view.ListHabitacionByPrecioDescActivity;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.view.ListHotelActivity;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByDestacado.view.ListHotelByDestacadoActivity;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByPuntuacion.view.ListHotelByPuntuacionActivity;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByReservas.view.ListHotelByReservasActivity;*/
import com.bumptech.glide.Glide;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.BOOKING_API_PHOTO_HABITACION_URL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.BOOKING_API_PHOTO_HOTEL_URL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.HABITACION_ID;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.IMG_FORMAT;


public class DataHabitacionFragment extends Fragment implements DetailsHabitacionContract.View {
    private DetailsHabitacionPresenter presenter;
    private ImageView ivHotel;
    private TextView tvPuntuacionCount, tvLink, tvNumeroHab, tvDireccion,
            tvReservasCount, tvNumHabitacionsCount, tvDescripcion;

    private ConstraintLayout detailConstraint;
    private ProgressBar pbDetails;
    private LinearLayout linearLayout;
    private Button btnRetry;

    // TODO: Rename parameter arguments, choose names that match
    private int idHabitacion;
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

    public DataHabitacionFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DetailsHabitacionPresenter(this);
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

        detailConstraint = view.findViewById(R.id.detail_htabitacion_constraint);
        detailConstraint.setVisibility(View.GONE);

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

        presenter.getDetailsHabitacion(idHabitacion);

        return view;
    }

    @Override
    public void onSuccess(Habitacion habitacion) {
        detailConstraint.setVisibility(View.VISIBLE);
        pbDetails.setVisibility(View.GONE);
        linearLayout.setVisibility(View.GONE);

        tvPuntuacionCount.setText(String.valueOf(habitacion.getNumHabitacion()));
        Glide.with(this).load(BOOKING_API_PHOTO_HABITACION_URL + habitacion.getFoto() + IMG_FORMAT)
                .centerInside()
                .centerCrop()
                .into(ivHotel);

        tvDireccion.setText(String.valueOf(habitacion.getCamas()));
        tvReservasCount.setText(String.valueOf(habitacion.getPrecio()));
        if (habitacion.isOcupada()) {
            tvNumHabitacionsCount.setText(" No ");
            tvLink.setVisibility(View.INVISIBLE);
        } else {
            tvNumHabitacionsCount.setText(" Sí ");
            tvLink.setVisibility(View.VISIBLE);
            tvLink.setLinksClickable(false);
        }
        tvDescripcion.setText(habitacion.getDescripcion());
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
