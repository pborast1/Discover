package com.example.pareshboraste.doordash.discover.utils;

import android.support.v7.util.DiffUtil;

import com.example.pareshboraste.doordash.discover.model.Restaurant;

import java.util.List;

public class RestaurantDiffUtil extends DiffUtil.Callback {
    private final List<Restaurant> oldRestaurantList;
    private final List<Restaurant> newRestaurantList;

    public RestaurantDiffUtil(List<Restaurant> oldRestaurantList, List<Restaurant> newRestaurantList) {
        this.oldRestaurantList = oldRestaurantList;
        this.newRestaurantList = newRestaurantList;
    }

    @Override
    public int getOldListSize() {
        return oldRestaurantList.size();
    }

    @Override
    public int getNewListSize() {
        return newRestaurantList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldRestaurantList.get(oldItemPosition).getId().equals(newRestaurantList.get(
                newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Restaurant oldRestaurant = oldRestaurantList.get(oldItemPosition);
        final Restaurant newRestaurant = newRestaurantList.get(newItemPosition);
        return oldRestaurant.getName().equals(newRestaurant.getName());
    }
}
