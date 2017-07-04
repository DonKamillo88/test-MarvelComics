package com.donkamillo.test_marvelcomics.ui.comicsList;

import com.donkamillo.test_marvelcomics.data.model.ComicModel;

/**
 * Created by DonKamillo on 03.07.2017.
 */

public class ComicsContract {

    interface View {

        void updateComicsData(ComicModel comicModel);

        void setProgressBar(boolean b);

        void showErrorMessage(String message);
    }

    interface Presenter<T> {

        void getComics();

        void unSubscribe();

        void setView(T view);

    }

}