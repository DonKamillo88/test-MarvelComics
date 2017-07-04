package com.donkamillo.test_marvelcomics.di.module;

import android.content.Context;

import com.donkamillo.test_marvelcomics.R;
import com.donkamillo.test_marvelcomics.data.DataRepository;
import com.donkamillo.test_marvelcomics.data.local.LocalDataSource;
import com.donkamillo.test_marvelcomics.ui.comicsList.ComicsPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DonKamillo on 03.07.2017.
 */


@Module
public class MainModule {
    public static final String FORMAT = "comic";
    public static final String FORMAT_TYPE = "comic";
    public static final int LIMIT = 100;


    @Provides
    @Singleton
    ComicsPresenter provideComicsPresenter(Context context, DataRepository dataRepository, LocalDataSource localDataSource) {
        return new ComicsPresenter(context, dataRepository,localDataSource, FORMAT, FORMAT_TYPE, LIMIT, context.getResources().getString(R.string.public_key), context.getResources().getString(R.string.private_key));
    }

}
