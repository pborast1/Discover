package com.example.pareshboraste.doordash.discover.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class AppModule {
    @Singleton
    @Provides
    Context provideContext(Application application) {
        return application.getApplicationContext();
    }
}
