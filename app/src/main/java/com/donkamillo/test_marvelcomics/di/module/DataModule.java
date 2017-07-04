package com.donkamillo.test_marvelcomics.di.module;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.donkamillo.test_marvelcomics.data.DataRepository;
import com.donkamillo.test_marvelcomics.data.remote.MarvelApi;
import com.donkamillo.test_marvelcomics.data.remote.RemoteDataSource;
import com.donkamillo.test_marvelcomics.di.App;

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
    DataRepository provideDataRepository(RemoteDataSource remoteDataSource) {
        return new DataRepository(remoteDataSource);
    }


    @Provides
    @Singleton
    RemoteDataSource provideRemoteDataSource(MarvelApi marvelApi) {
        return new RemoteDataSource(marvelApi);
    }


}
