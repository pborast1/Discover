package com.example.pareshboraste.doordash.discover.usecase;

import com.example.pareshboraste.doordash.discover.di.DiscoverService;
import com.example.pareshboraste.doordash.discover.model.Restaurant;

import java.util.List;

import rx.Observable;

public class DiscoverUseCase {
    private DiscoverService discoverService;

    public DiscoverUseCase(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    public Observable<List<Restaurant>> getListDiscover(String lat, String lng, int start, int end) {
        return discoverService.getList(lat, lng, start, end);
    }
}
