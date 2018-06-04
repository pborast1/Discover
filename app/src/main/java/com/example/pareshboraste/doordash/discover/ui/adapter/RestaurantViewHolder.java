package com.example.pareshboraste.doordash.discover.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pareshboraste.doordash.R;
import com.example.pareshboraste.doordash.discover.model.Restaurant;
import com.squareup.picasso.Picasso;

class RestaurantViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView cuisine;
    private TextView distance;
    private ImageView image;
    private View rowView;

    public RestaurantViewHolder(View itemView) {
        super(itemView);
        rowView = itemView;
        name = itemView.findViewById(R.id.restaurant_name);
        cuisine = itemView.findViewById(R.id.cuisine);
        distance = itemView.findViewById(R.id.distance);
        image = itemView.findViewById(R.id.image);
    }

    public void bind(Restaurant restaurant) {
        this.name.setText(restaurant.getName());
        this.cuisine.setText(restaurant.getDescription());
        this.distance.setText(restaurant.getStatus());
        Picasso.with(rowView.getContext())
                .load(restaurant.getCover_img_url())
                .into(this.image);
    }
}
