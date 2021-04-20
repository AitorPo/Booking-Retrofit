package com.androidavanzado.bookingaitorretrofit.habitacion.detailsHabitacion.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.BOOKING_API_PHOTO_HOTEL_URL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.HABITACION_ID;


public class DataHabitacionFragment extends Fragment{
    private DetailsHabitacionPresenter presenter;
    private ImageView ivHotel;
    private TextView tvPuntuacionCount, tvLink, tvNumeroHab, tvDireccion,
            tvReservasCount, tvNumHabitacionsCount, tvDescripcion;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "idHotel";
    private static final String ARG_PARAM2 = "nombre";
    private static final String ARG_PARAM3 = "direccion";
    private static final String ARG_PARAM4 = "reservasCount";
    private static final String ARG_PARAM5 = "numHabitaciones";
    private static final String ARG_PARAM6 = "descripcion";
    private static final String ARG_PARAM7 = "ivHotel";
    private static final String ARG_PARAM8 = "puntuacion";
    private static final String ARG_PARAM9 = "link";

    private static final String TAG = "DetailsHabitacionFragment";

    public DataHabitacionFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.habitacion_item_details, container, false);

        tvPuntuacionCount = view.findViewById(R.id.tvPuntuacionCount);
        tvDireccion = view.findViewById(R.id.tvDireccion);
        tvReservasCount = view.findViewById(R.id.tvReservasCount);
        tvNumHabitacionsCount = view.findViewById(R.id.tvNumHabitacionsCount);
        tvDescripcion = view.findViewById(R.id.tvDescripcion);
        ivHotel = view.findViewById(R.id.ivHotel);
        tvLink = view.findViewById(R.id.tvLink);



        /*tvPuntuacionCount.setText(String.valueOf(habitacion.getNumHabitacion()));
        Glide.with(this).load(BOOKING_API_PHOTO_HOTEL_URL + habitacion.getFoto() + ".jpg")
                .centerInside()
                .centerCrop()
                .into(ivHotel);

        tvDireccion.setText(String.valueOf(habitacion.getCamas()));
        tvReservasCount.setText(String.valueOf(habitacion.getPrecio()));
        if(habitacion.isOcupada()){
            tvNumHabitacionsCount.setText(" No ");
            tvLink.setVisibility(View.INVISIBLE);
        } else{
            tvNumHabitacionsCount.setText(" Sí ");
            tvLink.setVisibility(View.VISIBLE);

        }
        tvDescripcion.setText(habitacion.getDescripcion());*/
        return view;
    }
}
