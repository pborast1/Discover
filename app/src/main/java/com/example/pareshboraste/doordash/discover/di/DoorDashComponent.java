package com.example.pareshboraste.doordash.discover.di;

import com.example.pareshboraste.doordash.discover.ui.views.DiscoverActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApiServiceModule.class,
                DiscoverModule.class
        }
)

public interface DoorDashComponent {
    void inject(DiscoverActivity discoverActivity);
}
