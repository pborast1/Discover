package com.example.pareshboraste.doordash.discover.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pareshboraste.doordash.R;
import com.example.pareshboraste.doordash.discover.model.Restaurant;
import com.example.pareshboraste.doordash.discover.utils.RestaurantDiffUtil;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {
    private List<Restaurant> restaurants = new ArrayList<>();

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_row, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        holder.bind(restaurants.get(position));
    }

    public void updateList(List<Restaurant> list) {
        final RestaurantDiffUtil diffCallback = new RestaurantDiffUtil(this.restaurants, list);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.restaurants.clear();
        this.restaurants.addAll(list);
        diffResult.dispatchUpdatesTo(this);
    }

}
