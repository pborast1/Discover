package com.example.pareshboraste.doordash.discover.viewmodel

import com.example.pareshboraste.doordash.discover.model.Restaurant

class DiscoverDataFactory {
    fun getRestaurantList(count: Int = 5): List<Restaurant> {
        var list: List<Restaurant> = ArrayList()
        for (i in 1..count) {
            list += Restaurant(i.toString(), "A" + i.toString())
        }
        return list
    }

    fun getLat(): String = "37.422740"
    fun getLng(): String = "-122.139956"

}