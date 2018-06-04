package com.example.pareshboraste.doordash.discover.di;

import android.support.annotation.NonNull;

import com.example.pareshboraste.doordash.discover.usecase.DiscoverUseCase;
import com.example.pareshboraste.doordash.discover.viewmodel.DiscoverViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DiscoverModule {

    @Provides
    @NonNull
    public DiscoverViewModelFactory providesDiscoverViewModel(@NonNull DiscoverUseCase discoverUseCase) {
        return new DiscoverViewModelFactory(discoverUseCase);
    }

    @Provides
    @NonNull
    @Singleton
    public DiscoverUseCase providesDiscoverUseCase(@NonNull DiscoverService discoverService) {
        return new DiscoverUseCase(discoverService);
    }
}
