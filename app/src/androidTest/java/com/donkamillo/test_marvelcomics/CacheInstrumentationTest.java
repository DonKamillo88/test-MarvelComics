package com.donkamillo.test_marvelcomics;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.donkamillo.test_marvelcomics.data.local.SharedPreferencesManager;
import com.donkamillo.test_marvelcomics.data.model.ComicModel;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by DonKamillo on 04.07.2017.
 */


@RunWith(AndroidJUnit4.class)
public class CacheInstrumentationTest {

    private SharedPreferencesManager preferencesManager;

    @Before
    public void setUp() {
        Context app = InstrumentationRegistry.getTargetContext();
        this.preferencesManager = new SharedPreferencesManager(PreferenceManager.getDefaultSharedPreferences(app), new Gson());
    }

    @Test
    public void saveLoadCacheDate() throws Exception {
        Date now = new Date();

        preferencesManager.saveCacheDate(now.getTime());
        long loadedDate = preferencesManager.loadCacheDate();

        assertEquals(now.getTime(), loadedDate);
    }

    @Test
    public void saveLoadCache_correct_data() throws Exception {
        ComicModel comicModelMock = getComicModelMock();

        preferencesManager.saveCache(comicModelMock);
        ComicModel loadedCache = preferencesManager.loadCache();

        assertEquals(2, loadedCache.getData().getResults().size());
        assertEquals(2, loadedCache.getData().getResults().get(1).getPageCount());
        assertEquals("title1", loadedCache.getData().getResults().get(0).getTitle());
    }


    @Test
    public void saveLoadCache_empty_data() throws Exception {
        ComicModel comicModelMock = getComicModelMock();
        comicModelMock.setData(null);

        preferencesManager.saveCache(comicModelMock);
        ComicModel loadedCache = preferencesManager.loadCache();

        assertNull(loadedCache.getData());
    }


    private ComicModel getComicModelMock() {
        ComicModel comicModel = new ComicModel();
        ComicModel.Data data = new ComicModel.Data();
        List<ComicModel.Result> results = new ArrayList<>();

        ComicModel.Result r1 = new ComicModel.Result();
        r1.setTitle("title1");
        r1.setPageCount(1);
        results.add(r1);

        ComicModel.Result r2 = new ComicModel.Result();
        r2.setTitle("title2");
        r2.setPageCount(2);
        results.add(r2);

        data.setResults(results);
        comicModel.setData(data);
        return comicModel;
    }
}
