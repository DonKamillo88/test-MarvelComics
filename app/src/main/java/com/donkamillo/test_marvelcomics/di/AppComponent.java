package com.donkamillo.test_marvelcomics.di;

import com.donkamillo.test_marvelcomics.ui.MainActivity;
import com.donkamillo.test_marvelcomics.di.module.AndroidModule;
import com.donkamillo.test_marvelcomics.di.module.ApiModule;
import com.donkamillo.test_marvelcomics.di.module.DataModule;
import com.donkamillo.test_marvelcomics.di.module.MainModule;
import com.donkamillo.test_marvelcomics.ui.comicsList.ComicListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by DonKamillo on 03.07.2017.
 */
@Singleton
@Component(modules = {AndroidModule.class, MainModule.class, DataModule.class, ApiModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(ComicListFragment gameListFragment);


}

