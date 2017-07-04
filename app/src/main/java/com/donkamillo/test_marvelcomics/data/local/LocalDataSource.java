package com.donkamillo.test_marvelcomics.data.local;

import android.content.Context;

import com.donkamillo.test_marvelcomics.data.DataSource;

/**
 * Created by DonKamillo on 04.07.2017.
 */

public class LocalDataSource extends DataSource {

    private SharedPreferencesManager preferencesManager;

    public LocalDataSource(SharedPreferencesManager preferencesManager) {
        this.preferencesManager = preferencesManager;
    }

    @Override
    public void getComics(Context context, GetComicsCallback callback, String format, String formatType, int limit, long timestamp, String publicKey, String privateKey) {
        callback.onSuccess(preferencesManager.loadCache());
    }

    @Override
    public void unSubscribe() {
        // nope
    }
}