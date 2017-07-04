package com.donkamillo.test_marvelcomics.data;

/**
 * Created by DonKamillo on 03.07.2017.
 */

public class DataRepository {

    private DataSource remoteDataSource;

    public DataRepository(DataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public DataSource getDataSource() {
        return remoteDataSource;
    }

}
