package com.donkamillo.test_marvelcomics.data.remote;

import android.content.Context;

import com.donkamillo.test_marvelcomics.data.DataSource;
import com.donkamillo.test_marvelcomics.data.model.ComicModel;
import com.donkamillo.test_marvelcomics.util.HashGenerator;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by DonKamillo on 03.07.2017.
 */

public class RemoteDataSource extends DataSource {

    private CompositeDisposable compositeDisposable;
    private MarvelApi api;

    public RemoteDataSource(MarvelApi api) {
        this.api = api;
    }

    @Override
    public void getComics(final Context context, final GetComicsCallback callback, String format, String formatType, int limit, long timestamp, String publicKey, String privateKey) {
        DisposableSingleObserver<ComicModel> disposableSingleObserver = new DisposableSingleObserver<ComicModel>() {
            @Override
            public void onSuccess(ComicModel model) {
                callback.onSuccess(model);
            }

            @Override
            public void onError(Throwable throwable) {
                callback.onFailure(throwable);
            }
        };

        if (!getCompositeDisposable().isDisposed()) {

            // generate hash using timestamp and API keys
            String hash = HashGenerator.generate(timestamp, privateKey, publicKey);

            Single<ComicModel> newsModelSingle = api.getComics(format, formatType, limit, timestamp, publicKey, hash);
            Disposable disposable = newsModelSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(disposableSingleObserver);
            compositeDisposable.add(disposable);
        }
    }

    @Override
    public void unSubscribe() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
    }

    private CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }
}
