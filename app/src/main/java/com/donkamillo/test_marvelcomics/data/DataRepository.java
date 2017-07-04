package com.donkamillo.test_marvelcomics.data;

import com.donkamillo.test_marvelcomics.data.local.SharedPreferencesManager;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by DonKamillo on 03.07.2017.
 */

public class DataRepository {

    private DataSource remoteDataSource;
    private DataSource localDataSource;
    private SharedPreferencesManager preferencesManager;

    public DataRepository(DataSource remoteDataSource, DataSource localDataSource, SharedPreferencesManager preferencesManager) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.preferencesManager = preferencesManager;
    }

    public DataSource getDataSource() {
        long today = new Date().getTime();
        long downloadDataDate = preferencesManager.loadCacheDate();
        if (downloadDataDate == 0 || today - downloadDataDate > TimeUnit.HOURS.toMillis(1)) {
            return remoteDataSource;
        } else {
            return localDataSource;
        }
    }

}
