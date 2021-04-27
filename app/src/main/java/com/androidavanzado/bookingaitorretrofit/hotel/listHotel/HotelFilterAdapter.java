package com.androidavanzado.bookingaitorretrofit.hotel.listHotel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.bookingaitorretrofit.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class HotelFilterAdapter extends RecyclerView.Adapter<HotelFilterAdapter.ViewHolder> {
    private List<String> filters;
    private OnFilterClickListener listener;

    public HotelFilterAdapter(List<String> filters, OnFilterClickListener listener){
        this.filters = filters;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.filter_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.filter = filters.get(position);
        holder.tvFilterName.setText(holder.filter);
        holder.bind(holder.filter, listener);
    }

    @Override
    public int getItemCount() {
        return filters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvFilterName;
        public MaterialCardView cardViewFIlter;
        public String filter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFilterName = itemView.findViewById(R.id.tvFilterName);
            cardViewFIlter = itemView.findViewById(R.id.cardViewFilter);
        }
        public void bind(final String filter, final OnFilterClickListener listener){
            cardViewFIlter.setOnClickListener(v -> {
                listener.onFilterClick(filter);
            });
        }
    }

    public interface OnFilterClickListener{
        void onFilterClick(String filter);
    }
}
