package com.androidavanzado.bookingaitorretrofit.hotel.listHotel;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.bookingaitorretrofit.R;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioAsc.view.ListHabitacionByPrecioAscFragment;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioDesc.view.ListHabitacionByPrecioDescFragment;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByDestacado.view.ListHotelByDestacadoFragment;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByPuntuacion.view.ListHotelByPuntuacionFragment;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByReservas.view.ListHotelByReservasFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HotelFilterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HotelFilterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "columns";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;
    private List<String> filterList;
    private RecyclerView recyclerviewFilter;
    private HotelFilterAdapter adapter;

    public HotelFilterFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HotelFilterFragment newInstance(int column) {
        HotelFilterFragment fragment = new HotelFilterFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, column);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hotel_filter, container, false);

        filterList = new ArrayList<>();
        filterList.add("Destacados");
        filterList.add("Mejor puntuados");
        filterList.add("Más reservas");
        filterList.add("Más caros");
        filterList.add("Más baratos");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerviewFilter = view.findViewById(R.id.recyclerviewFilter);
        recyclerviewFilter.setLayoutManager(linearLayoutManager);
        adapter = new HotelFilterAdapter(filterList, filter -> {
            switch (filter) {
                case "Destacados":
                    Log.d("FILTER", filter);
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.activity_dashboard_fragment_container, ListHotelByDestacadoFragment.newInstance(1))
                            .addToBackStack(null)
                            .commit();
                    break;
                case "Mejor puntuados":
                    Log.d("FILTER", filter);
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.activity_dashboard_fragment_container, ListHotelByPuntuacionFragment.newInstance(1))
                            .addToBackStack(null)
                            .commit();
                    break;
                case "Más reservas":
                    Log.d("FILTER", filter);
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.activity_dashboard_fragment_container, ListHotelByReservasFragment.newInstance(1))
                            .addToBackStack(null)
                            .commit();
                    break;
                case "Más caros":
                    Log.d("FILTER", filter);
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.activity_dashboard_fragment_container, ListHabitacionByPrecioDescFragment.newInstance(1))
                            .addToBackStack(null)
                            .commit();
                    break;
                case "Más baratos":
                    Log.d("FILTER", filter);
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.activity_dashboard_fragment_container, ListHabitacionByPrecioAscFragment.newInstance(1))
                            .addToBackStack(null)
                            .commit();
                    break;

            }
            //Log.d("FILTER", filter);
        });

        recyclerviewFilter.setAdapter(adapter);

        return view;
    }
}