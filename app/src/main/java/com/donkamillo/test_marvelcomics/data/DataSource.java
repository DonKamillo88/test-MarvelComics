package com.donkamillo.test_marvelcomics.data;

import android.content.Context;

import com.donkamillo.test_marvelcomics.data.model.ComicModel;

/**
 * Created by DonKamillo on 03.07.2017.
 */

public abstract class DataSource {

    public interface GetComicsCallback {
        void onSuccess(ComicModel model);

        void onFailure(Throwable throwable);

    }

    public abstract void getComics(Context context, GetComicsCallback callback, String format, String formatType, int limit, long timestamp, String publicKey, String privateKey);

    public abstract void unSubscribe();
}