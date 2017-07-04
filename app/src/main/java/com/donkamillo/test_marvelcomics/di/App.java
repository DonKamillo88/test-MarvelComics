package com.donkamillo.test_marvelcomics.di;

import android.app.Application;

import com.donkamillo.test_marvelcomics.di.module.AndroidModule;

/**
 * Created by DonKamillo on 03.07.2017.
 */

public class App extends Application {
    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = createComponent();
    }

    public static AppComponent getComponent() {
        return component;
    }

    public AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .androidModule(new AndroidModule(this))
                .build();
    }

}
