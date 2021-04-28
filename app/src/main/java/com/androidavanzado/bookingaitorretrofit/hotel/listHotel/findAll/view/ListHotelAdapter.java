package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.bookingaitorretrofit.R;
import com.androidavanzado.bookingaitorretrofit.beans.Ciudad;
import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.BOOKING_API_PHOTO_HOTEL_URL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.IMG_FORMAT;

public class ListHotelAdapter extends RecyclerView.Adapter<ListHotelAdapter.ViewHolder> {
    ArrayList<Hotel> hotelArrayList;
    Context context;
    OnCardClickListener onCardClickListener;

    public ListHotelAdapter(ArrayList<Hotel> hotelArrayList, Context context, OnCardClickListener onCardClickListener) {
        this.hotelArrayList = hotelArrayList;
        this.context = context;
        this.onCardClickListener = onCardClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hotel_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.hotel = hotelArrayList.get(position);
        holder.bind(holder.hotel, onCardClickListener);
        holder.tvNombre.setText(holder.hotel.getNombre());
        holder.tvPuntuacion.setText(String.valueOf(holder.hotel.getPuntuacion()));

        Glide.with(context).load(BOOKING_API_PHOTO_HOTEL_URL + holder.hotel.getFoto() + IMG_FORMAT)
                .clone()
                .fitCenter()
                .centerCrop()
                .lock()
                .into(holder.ivFoto);
    }

    @Override
    public int getItemCount() {
        if (hotelArrayList != null)
            return hotelArrayList.size();
        else return 0;
    }

    public void setData(List<Hotel> hotelArrayList) {
        this.hotelArrayList = (ArrayList<Hotel>) hotelArrayList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvNombre;
        public final TextView tvPuntuacion;
        public final ImageView ivFoto;
        public final CardView cardViewHotel;
        public Hotel hotel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreHotel);
            tvPuntuacion = itemView.findViewById(R.id.tvNota);
            ivFoto = itemView.findViewById(R.id.ivFotoHabitacion);
            cardViewHotel = itemView.findViewById(R.id.cardViewHabitacion);
        }

        public void bind(Hotel hotel, final OnCardClickListener onCardClickListener) {
            cardViewHotel.setOnClickListener(v -> onCardClickListener.onCardClick(hotel.getIdHotel()));
        }
    }

    public interface OnCardClickListener {
        void onCardClick(int idHotel);
    }
}
