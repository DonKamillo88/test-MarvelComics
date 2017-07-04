package com.donkamillo.test_marvelcomics.data.remote;

import com.donkamillo.test_marvelcomics.data.model.ComicModel;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by DonKamillo on 03.07.2017.
 */

public interface MarvelApi {

    String FORMAT = "format";
    String FORMAT_TYPE = "formatType";
    String API_KEY = "apikey";
    String HASH = "hash";
    String LIMIT = "limit";
    String TIMESTAMP = "ts";

    //https://gateway.marvel.com:443/v1/public/comics?format=comic&formatType=comic&limit=100&apikey=PUBLIC_API_KEY&hash=HASH&ts=TIMESTAMP
    @GET("v1/public/comics")
    Single<ComicModel> getComics(
            @Query(FORMAT) String format,
            @Query(FORMAT_TYPE) String formatType,
            @Query(LIMIT) int limit,
            @Query(TIMESTAMP) long timestamp,
            @Query(API_KEY) String publicKey,
            @Query(HASH) String hash);
}