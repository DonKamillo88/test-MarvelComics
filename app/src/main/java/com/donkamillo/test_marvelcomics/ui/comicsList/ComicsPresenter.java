package com.donkamillo.test_marvelcomics.ui.comicsList;

import android.content.Context;

import com.donkamillo.test_marvelcomics.R;
import com.donkamillo.test_marvelcomics.data.DataRepository;
import com.donkamillo.test_marvelcomics.data.DataSource;
import com.donkamillo.test_marvelcomics.data.model.ComicModel;
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

    public ComicsPresenter(Context context, DataRepository dataRepository, String format, String formatType, int limit, String publicKey, String privateKey) {
        this.dataRepository = dataRepository;
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

        view.setProgressBar(true);

        dataSource = dataRepository.getDataSource();

        dataSource.getComics(context, new DataSource.GetComicsCallback() {
            @Override
            public void onSuccess(ComicModel model) {
                if (view != null) {
                    view.updateComicsData(model);
                    view.setProgressBar(false);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                if (view != null) {
                    view.setProgressBar(false);
                    view.showErrorMessage(context.getString(R.string.error_msg));
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
}
