package com.donkamillo.test_marvelcomics.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.donkamillo.test_marvelcomics.data.DataRepository;
import com.donkamillo.test_marvelcomics.data.local.LocalDataSource;
import com.donkamillo.test_marvelcomics.data.local.SharedPreferencesManager;
import com.donkamillo.test_marvelcomics.data.remote.MarvelApi;
import com.donkamillo.test_marvelcomics.data.remote.RemoteDataSource;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DonKamillo on 03.07.2017.
 */


@Module
public class DataModule {
    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    SharedPreferencesManager provideSharedPreferencesManager(SharedPreferences sharedPreferences, Gson gson) {
        return new SharedPreferencesManager(sharedPreferences, gson);
    }

    @Provides
    @Singleton
    DataRepository provideDataRepository(RemoteDataSource remoteDataSource, LocalDataSource localDataSource, SharedPreferencesManager preferencesManager) {
        return new DataRepository(remoteDataSource, localDataSource, preferencesManager);
    }

    @Provides
    @Singleton
    LocalDataSource provideLocalDataSource(SharedPreferencesManager preferencesManager) {
        return new LocalDataSource(preferencesManager);
    }

    @Provides
    @Singleton
    RemoteDataSource provideRemoteDataSource(MarvelApi marvelApi, SharedPreferencesManager preferencesManager) {
        return new RemoteDataSource(marvelApi, preferencesManager);
    }


}
