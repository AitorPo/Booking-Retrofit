package com.androidavanzado.bookingaitorretrofit.habitacion.findByHotel.view;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.bookingaitorretrofit.BuildConfig;
import com.androidavanzado.bookingaitorretrofit.R;
import com.androidavanzado.bookingaitorretrofit.beans.Habitacion;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.COIN;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.IMG_FORMAT;

public class ListHabitacionAdapter extends RecyclerView.Adapter<ListHabitacionAdapter.ViewHolder> {
    ArrayList<Habitacion> habitaciones;
    Context context;
    OnCardClickListener onCardClickListener;

    public ListHabitacionAdapter(ArrayList<Habitacion> habitaciones, Context context, OnCardClickListener onCardClickListener) {
        this.habitaciones = habitaciones;
        this.context = context;
        this.onCardClickListener = onCardClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.habitacion_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.habitacion = habitaciones.get(position);
        holder.bind(holder.habitacion, onCardClickListener);
        holder.tvPrecio.setText(String.valueOf(holder.habitacion.getPrecio()) + COIN);
        holder.tvCamas.setText(String.valueOf(holder.habitacion.getCamas()));
        holder.tvNumHabitacion.setText(String.valueOf(holder.habitacion.getNumHabitacion()));
        if (holder.habitacion.isOcupada()) {
            holder.tvDisponible.setText(" OCUPADA ");

        } else {
            holder.tvDisponible.setText(" DISPONIBLE ");
        }

        Glide.with(context).load(BuildConfig.BOOKING_API_PHOTO_HABITACION_URL + habitaciones.get(position).getFoto() + IMG_FORMAT)
                .clone()
                .centerCrop()
                .into(holder.ivFotoHabitacion);
    }

    @Override
    public int getItemCount() {
        return habitaciones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvPrecio;
        public final TextView tvCamas;
        public final TextView tvNumHabitacion;
        public final TextView tvDisponible;
        public final ImageView ivFotoHabitacion;
        public final CardView cardViewHabitacion;
        public Habitacion habitacion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvCamas = itemView.findViewById(R.id.tvCama);
            tvDisponible = itemView.findViewById(R.id.tvDisponible);
            ivFotoHabitacion = itemView.findViewById(R.id.ivFotoHabitacion);
            cardViewHabitacion = itemView.findViewById(R.id.cardViewHabitacion);
            tvNumHabitacion = itemView.findViewById(R.id.tvNumeroHab);
        }

        public void bind(Habitacion habitacion, final OnCardClickListener onCardClickListener) {
            cardViewHabitacion.setOnClickListener(v -> onCardClickListener.onCardClick(habitacion.getIdHabitacion()));
        }
    }

    public interface OnCardClickListener {
        void onCardClick(int idHabitacion);
    }
}
