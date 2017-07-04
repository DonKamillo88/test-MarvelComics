package com.donkamillo.test_marvelcomics.ui.comicsList;

import android.content.Context;

import com.donkamillo.test_marvelcomics.R;
import com.donkamillo.test_marvelcomics.data.DataRepository;
import com.donkamillo.test_marvelcomics.data.DataSource;
import com.donkamillo.test_marvelcomics.data.local.LocalDataSource;
import com.donkamillo.test_marvelcomics.data.model.ComicModel;
import com.donkamillo.test_marvelcomics.data.remote.RemoteDataSource;
import com.donkamillo.test_marvelcomics.util.Utils;


/**
 * Created by DonKamillo on 03.07.2017.
 */

public class ComicsPresenter implements ComicsContract.Presenter {

    private DataRepository dataRepository;
    private ComicsContract.View view;
    private Context context;
    private DataSource dataSource;
    private String format;
    private String formatType;
    private int limit;
    private String publicKey;
    private String privateKey;
    private LocalDataSource localDataSource;

    public ComicsPresenter(Context context, DataRepository dataRepository, LocalDataSource localDataSource, String format, String formatType, int limit, String publicKey, String privateKey) {
        this.dataRepository = dataRepository;
        this.localDataSource = localDataSource;
        this.context = context;
        this.format = format;
        this.formatType = formatType;
        this.limit = limit;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    @Override
    public void getComics() {
        if (view == null) {
            return;
        }

        dataSource = dataRepository.getDataSource();
        callGetComics(dataSource);
    }

    private void callGetComics(final DataSource dataSource) {
        view.setBudgetViewVisible(false);
        view.setProgressBarVisible(true);

        dataSource.getComics(context, new DataSource.GetComicsCallback() {
            @Override
            public void onSuccess(ComicModel model) {
                if (view != null) {
                    view.updateComicsData(model);
                    view.setProgressBarVisible(false);
                    view.setBudgetViewVisible(true);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

                // remote exception -> try get data from cache
                if (dataSource instanceof RemoteDataSource) {
                    callGetComics(localDataSource);
                } else {
                    if (view != null) {
                        view.setProgressBarVisible(false);
                        view.setBudgetViewVisible(false);
                        view.showErrorMessage(context.getString(R.string.error_msg));
                    }
                }

            }

        }, format, formatType, limit, Utils.getCurrentTimestamp(), publicKey, privateKey);
    }

    @Override
    public void unSubscribe() {
        dataSource.unSubscribe();
    }

    @Override
    public void setView(Object view) {
        this.view = (ComicsContract.View) view;
    }

    @Override
    public void onBudgetTextChanged(String s) {

        double currentBudget = Double.parseDouble(s.replaceAll(" ", ".").replaceAll(",", "."));
        view.setMaxComicsNo((int) currentBudget);
        view.setMaxPagesNo((int) currentBudget);
    }
}
