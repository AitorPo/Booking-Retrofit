package com.androidavanzado.bookingaitorretrofit.hotel.detailsHotel.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
import androidx.fragment.app.Fragment;

import com.androidavanzado.bookingaitorretrofit.BuildConfig;
import com.androidavanzado.bookingaitorretrofit.R;
import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByHotel.view.ListHabitacionByHotelFragment;
import com.androidavanzado.bookingaitorretrofit.hotel.detailsHotel.contract.DetailsHotelContract;
import com.androidavanzado.bookingaitorretrofit.hotel.detailsHotel.presenter.DetailsHotelPresenter;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.IMG_FORMAT;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HotelDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HotelDataFragment extends Fragment implements DetailsHotelContract.View, OnMapReadyCallback {

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
    private static final String TAG = "HotelDataFragment";

    private GoogleMap map;


    // TODO: Rename and change types of parameters
    private int idHotel, idCIudad;
    private int puntuacionCount;
    private String nombre;
    private String direccion;
    private int reservasCount;
    private int numHabitaciones;
    private String descripcion;
    private String imgHotel;

    private ImageView ivHotel;
    private TextView tvNombre, tvPuntuacionCount, tvLink, tvDireccion,
            tvReservasCount, tvNumHabitacionsCount, tvDescripcion;
    private MapView mapView;
    private LatLng city;

    private CollapsingToolbarLayout toolbarLayout;


    //private ConstraintLayout detailConstraint;
    private ProgressBar pbDetails;
    private LinearLayout linearLayout;
    private Button btnRetry, btnHabitaciones;
    private Toolbar toolbar;


    private DetailsHotelPresenter presenter;

    public HotelDataFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HotelDataFragment newInstance(int idHotel) {
        HotelDataFragment fragment = new HotelDataFragment();
        Bundle args = new Bundle();
        //Log.d(TAG, hotel.toString());
        args.putInt(ARG_PARAM1, idHotel);
        /*args.putInt(ARG_PARAM1, hotel.getIdHotel());
        args.putString(ARG_PARAM2, hotel.getNombre());
        args.putString(ARG_PARAM3, hotel.getDireccion());
        args.putInt(ARG_PARAM4, hotel.getNumReservas());
        args.putInt(ARG_PARAM5, hotel.getNumHabitaciones());
        args.putString(ARG_PARAM6, hotel.getDescripcion());
        args.putString(ARG_PARAM7, hotel.getFoto());
        args.putInt(ARG_PARAM8, hotel.getPuntuacion());*/
        //Log.d(TAG, args.toString());
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.d(TAG, getArguments().toString());
        presenter = new DetailsHotelPresenter(this);
        if (getArguments() != null) {
            idHotel = getArguments().getInt(ARG_PARAM1);
            //Log.d(TAG, String.valueOf(idHotel));
            /*nombre = getArguments().getString(ARG_PARAM2);
            direccion = getArguments().getString(ARG_PARAM3);
            reservasCount = getArguments().getInt(ARG_PARAM4);
            numHabitaciones = getArguments().getInt(ARG_PARAM5);
            descripcion = getArguments().getString(ARG_PARAM6);
            imgHotel = getArguments().getString(ARG_PARAM7);
            puntuacionCount = getArguments().getInt(ARG_PARAM8);*/
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hotel_item_details, container, false);

        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        //mapView.getMapAsync(this);

        toolbar = view.findViewById(R.id.toolbarDetailsHotel);

        //((AppCompatActivity)getActivity()).getSupportActionBar().show();


        //detailConstraint = view.findViewById(R.id.detail_hotel_constraint);
        //detailConstraint.setVisibility(View.GONE);

        pbDetails = view.findViewById(R.id.pb_hotel_detail);
        pbDetails.setVisibility(View.VISIBLE);

        linearLayout = view.findViewById(R.id.ll_error);
        linearLayout.setVisibility(View.GONE);

        btnRetry = view.findViewById(R.id.btnRetry);

        tvNombre = view.findViewById(R.id.tvNombre);
        tvPuntuacionCount = view.findViewById(R.id.tvPuntuacionCount);
        tvDireccion = view.findViewById(R.id.tvDireccion);
        tvReservasCount = view.findViewById(R.id.tvReservasCount);
        tvNumHabitacionsCount = view.findViewById(R.id.tvNumHabitacionsCount);
        tvDescripcion = view.findViewById(R.id.tvDescripcion);
        ivHotel = view.findViewById(R.id.ivHotel);
        btnHabitaciones = view.findViewById(R.id.btnHabitaciones);

        presenter.getDetailsHotel(idHotel);

        // Seteamos el valor de los elementos del layout con los datos de la API
        /*tvNombre.setText(nombre);
        tvPuntuacionCount.setText(String.valueOf(puntuacionCount));
        Glide.with(this).load(BOOKING_API_PHOTO_HOTEL_URL + imgHotel + IMG_FORMAT)
                .centerInside()
                .centerCrop()
                .into(ivHotel);

        tvDireccion.setText(direccion);
        tvReservasCount.setText(String.valueOf(reservasCount));
        tvNumHabitacionsCount.setText(String.valueOf(numHabitaciones));
        tvDescripcion.setText(descripcion);*/

//        tvLink.setOnClickListener(v -> verTodasHabitaciones(idHotel));

        return view;
    }

    private void verTodasHabitaciones(int idHotel) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_dashboard_fragment_container,
                        ListHabitacionByHotelFragment.newInstance(idHotel))
                .addToBackStack(null)
                .commit();
    }

    private void showError() {
        pbDetails.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);

        btnRetry.setClickable(true);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getDetailsHotel(idHotel);
            }
        });
        Toast.makeText(getActivity(), R.string.internet_error, Toast.LENGTH_LONG).show();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onSuccess(Hotel hotel) {
        //detailConstraint.setVisibility(View.VISIBLE);
        pbDetails.setVisibility(View.GONE);
        linearLayout.setVisibility(View.GONE);

        // Seteamos el valor de los elementos del layout con los datos de la API
        tvNombre.setText(hotel.getNombre());
        tvPuntuacionCount.setText(String.valueOf(hotel.getPuntuacion()));
        Glide.with(this).load(BuildConfig.BOOKING_API_PHOTO_HOTEL_URL + hotel.getFoto() + IMG_FORMAT)
                .centerInside()
                .centerCrop()
                .into(ivHotel);

        tvDireccion.setText(hotel.getDireccion());
        tvReservasCount.setText(String.valueOf(hotel.getNumReservas()));
        tvNumHabitacionsCount.setText(String.valueOf(hotel.getNumHabitaciones()));
        tvDescripcion.setText(hotel.getDescripcion());

        idCIudad = getIdCiudad(hotel);

        mapView.getMapAsync(this);

        btnHabitaciones.setOnClickListener(v -> verTodasHabitaciones(idHotel));
    }

    private int getIdCiudad(Hotel hotel) {
        return hotel.getCiudad();
    }

    private LatLng setMap(int idCiudad) {
        LatLng position;
        switch (idCiudad){
            case 1:
                position = new LatLng(39.47,-0.37);
            break;
            case 2:
                position = new LatLng(41.38, 2.16);
                break;
            case 3:
                position = new LatLng(38.348, -0.48);
                break;
            case 4:
                position = new LatLng(41.65, -0.87);
                break;
            case 5:
                position = new LatLng(39.56, 2.64);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + idCiudad);
        }
        return position;
    }


    @Override
    public void onFailure(Throwable throwable) {
        showError();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng position = setMap(idCIudad);
        map.addMarker(new MarkerOptions().position(position));

        map.moveCamera(CameraUpdateFactory.newLatLng(position));
    }
}