package com.example.pareshboraste.doordash.discover.di;

import com.example.pareshboraste.doordash.discover.model.Restaurant;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface DiscoverService {
    String URL_PATH_DISCOVER = "/v2/restaurant/?";

    @GET(URL_PATH_DISCOVER)
    Observable<List<Restaurant>> getList(
            @Query("lat") String lat, @Query("lng") String lng,
            @Query("offset") int offset, @Query("limit") int limit
    );
}
