package com.example.pareshboraste.doordash.discover.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.pareshboraste.doordash.discover.usecase.DiscoverUseCase;

public class DiscoverViewModelFactory implements ViewModelProvider.Factory {
    private DiscoverUseCase discoverUseCase;

    public DiscoverViewModelFactory(DiscoverUseCase discoverUseCase) {
        this.discoverUseCase = discoverUseCase;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DiscoverViewModel.class)) {
            return (T) new DiscoverViewModel(discoverUseCase);
        }
        throw new IllegalArgumentException("Unknown Class");
    }
}
