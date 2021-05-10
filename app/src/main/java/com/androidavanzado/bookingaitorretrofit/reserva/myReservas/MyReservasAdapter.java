package com.androidavanzado.bookingaitorretrofit.reserva.myReservas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.bookingaitorretrofit.App;
import com.androidavanzado.bookingaitorretrofit.DashboardActivity;
import com.androidavanzado.bookingaitorretrofit.R;
import com.androidavanzado.bookingaitorretrofit.beans.Reserva;
import com.androidavanzado.bookingaitorretrofit.reserva.deleteReserva.DeleteReservaFragment;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class MyReservasAdapter extends RecyclerView.Adapter<MyReservasAdapter.ViewHolder> {
    ArrayList<Reserva> reservas;
    Context context;
    OnCardClickListener onCardClickListener;

    public MyReservasAdapter(ArrayList<Reserva> reservas, Context context, OnCardClickListener onCardClickListener){
        this.reservas = reservas;
        this.context = context;
        this.onCardClickListener = onCardClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_my_reservas, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.reserva = reservas.get(position);
        holder.bind(holder.reserva, onCardClickListener);
        holder.tvInValue.setText(holder.reserva.getIn().toString());
        holder.tvOutValue.setText(holder.reserva.getIn().toString());


    }

    @Override
    public int getItemCount() {
        return reservas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView tvInValue;
        public final TextView tvOutValue;
        public final MaterialCardView cardViewReserva;
        public Reserva reserva;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInValue = itemView.findViewById(R.id.tvInValue);
            tvOutValue = itemView.findViewById(R.id.tvOutValue);
            cardViewReserva = itemView.findViewById(R.id.cardViewReserva);
        }
        public void bind(Reserva reserva, final OnCardClickListener onCardClickListener) {
            cardViewReserva.setOnClickListener(v -> onCardClickListener.onCardClick(reserva.getIdHabitacion()));
        }


    }

    public interface OnCardClickListener {
        void onCardClick(int idHabitacion);
    }

}
