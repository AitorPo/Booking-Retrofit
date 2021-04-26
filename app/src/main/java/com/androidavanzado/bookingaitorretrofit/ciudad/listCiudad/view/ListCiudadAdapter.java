package com.androidavanzado.bookingaitorretrofit.ciudad.listCiudad.view;

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
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.BOOKING_API_PHOTO_CIUDAD_URL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.IMG_FORMAT;

public class ListCiudadAdapter extends RecyclerView.Adapter<ListCiudadAdapter.ViewHolder> {
    ArrayList<Ciudad> ciudades;
    Context context;
    OnCardClickListener onCardClickListener;

    public ListCiudadAdapter(ArrayList<Ciudad> ciudades, Context context, OnCardClickListener onCardClickListener){
        this.ciudades = ciudades;
        this.context = context;
        this.onCardClickListener = onCardClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ciudad_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ciudad = ciudades.get(position);
        holder.bind(holder.ciudad, onCardClickListener);
        holder.tvCiudadNombre.setText(holder.ciudad.getNombre());

        Glide.with(context).load(BOOKING_API_PHOTO_CIUDAD_URL + ciudades.get(position).getImagen() + IMG_FORMAT)
                .centerCrop()
                .into(holder.ivImagen);
    }

    @Override
    public int getItemCount() {
        return ciudades.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvCiudadNombre;
        public final CardView cardViewCiudad;
        public final ImageView ivImagen;
        public Ciudad ciudad;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCiudadNombre = itemView.findViewById(R.id.tvItemCiudad);
            ivImagen = itemView.findViewById(R.id.ivImagen);
            cardViewCiudad = itemView.findViewById(R.id.cardViewCiudad);
        }

        public void bind (Ciudad ciudad, OnCardClickListener onCardClickListener){
            cardViewCiudad.setOnClickListener(v -> {
                onCardClickListener.onCardClick(ciudad.getIdCiudad());
            });
        }
    }
    public interface OnCardClickListener{
        void onCardClick(int idCiudad);
    }
}
