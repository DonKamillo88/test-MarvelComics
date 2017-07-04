package com.donkamillo.test_marvelcomics.data.local;

import android.content.SharedPreferences;

import com.donkamillo.test_marvelcomics.data.model.ComicModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by DonKamillo on 04.07.2017.
 */

public class SharedPreferencesManager {

    private static final String CACHE = "cache";
    private static final String CACHE_DATE = "cache_date";

    private SharedPreferences sharedPrefs;
    private Gson gson;

    public SharedPreferencesManager(SharedPreferences sharedPreferences, Gson gson) {
        this.sharedPrefs = sharedPreferences;
        this.gson = gson;
    }

    public void saveCache(ComicModel comicModel) {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        String json = gson.toJson(comicModel);
        editor.putString(CACHE, json);
        editor.apply();
    }

    public ComicModel loadCache() {
        String json = sharedPrefs.getString(CACHE, null);
        Type type = new TypeToken<ComicModel>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public void saveCacheDate(long date) {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putLong(CACHE_DATE, date);
        editor.apply();
    }

    public Long loadCacheDate() {
        return sharedPrefs.getLong(CACHE_DATE, 0);
    }
}
