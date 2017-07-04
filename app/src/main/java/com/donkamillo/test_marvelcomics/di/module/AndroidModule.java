package com.donkamillo.test_marvelcomics.di.module;

import android.content.Context;

import com.donkamillo.test_marvelcomics.di.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DonKamillo on 04.07.2017.
 */

@Module
public class AndroidModule {
    private App application;

    public AndroidModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application.getApplicationContext();
    }

}
